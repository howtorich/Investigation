package com.example.investigation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.investigation.model.RegResp
import com.example.investigation.model.RegResponse
import com.example.investigation.model.Register
import com.example.investigation.model.RegisterApiService
import com.example.investigation.utils.SharedPreferenceHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MyChatListViewModel(application: Application):AndroidViewModel(application) {

    private val apiService= RegisterApiService()
    private val disposible= CompositeDisposable()

    val users by lazy { MutableLiveData<List<RegResp>>() }
    val loadError by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }

    private val prefs= SharedPreferenceHelper(getApplication())
    //fun refresh(){

        fun postRegisterDetails(response: Register){
            loading.value=true
            disposible.add(
                apiService.getUserDetailsService(response)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object: DisposableSingleObserver<RegResponse>(){
                        override fun onSuccess(res: RegResponse) {
                            loading.value=false
                            loadError.value=false
                            var usersDetails:ArrayList<RegResp>? = res.chatRegisteredUserFriends
                            users.value=usersDetails

                        }

                        override fun onError(e: Throwable) {
                            e.printStackTrace()
                            users.value=null
                            loading.value=false
                            loadError.value=true
                        }

                    })
            )


        }
    //}

  override fun onCleared() {
    super.onCleared()
    disposible.clear()
}
}
