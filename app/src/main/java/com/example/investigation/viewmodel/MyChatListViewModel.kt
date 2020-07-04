package com.example.investigation.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.investigation.model.*

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

    //addfrined
    val addFriendResult by lazy { MutableLiveData<Boolean>()}

   // private val prefs= SharedPreferenceHelper(getApplication())

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
                            //usersDetails?.add(0,RegResp("XXXXXX",1))
                            users.value=usersDetails

                        }

                        override fun onError(e: Throwable) {
                            e.printStackTrace()
                            //users.value=null
                            loading.value=false
                            loadError.value=true
                        }

                    })
            )


        }
    fun postAddFriendDetails(addFrined: AddingFriend){
        disposible.add(
            apiService.addFrinedService(addFrined)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<RegResponse>(){
                    override fun onSuccess(t: RegResponse) {
                            addFriendResult.value=true
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        addFriendResult.value=false
                    }

                }
                ))

    }


  override fun onCleared() {
    super.onCleared()
    disposible.clear()
}
}
