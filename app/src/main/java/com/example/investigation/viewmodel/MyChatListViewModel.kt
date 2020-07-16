package com.example.investigation.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.investigation.dagger.DaggerApiComponent
import com.example.investigation.model.*

import com.example.investigation.utils.SharedPreferenceHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MyChatListViewModel(application: Application):AndroidViewModel(application) {

    @Inject
    lateinit var apiService:RegisterApiService

     val disposible= CompositeDisposable()

    val users by lazy { MutableLiveData<List<RegResp>>() }
    val loadError by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }

    //for user details
    val userDetails by lazy { MutableLiveData<RegResponse>() }


    //addfrined
    val addFriendResult by lazy { MutableLiveData<Boolean>()}

    //clicking user
    val userclickResponse by lazy { MutableLiveData<RegResponse>() }

   init {
       DaggerApiComponent.create().injectRegisterApiService(this)

   }


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
                            val usersDetails:ArrayList<RegResp>? = res.chatRegisteredUserFriends
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
                .subscribeOn(Schedulers.io())
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

    fun gettingUserDetails(inputModel:Register){

        disposible.add(
            apiService.getUserDetailsService(inputModel)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<RegResponse>(){
                    override fun onSuccess(res: RegResponse) {
                        userDetails.value=res

                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()

                    }

                })
        )

    }
    //
    fun userChatPageDetails(inputModel:Register){
        disposible.add(
            apiService.getUserDetailsService(inputModel)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<RegResponse>(){
                    override fun onSuccess(res: RegResponse) {
                        userclickResponse.value=res

                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()

                    }

                })
        )
    }

  override fun onCleared() {
    super.onCleared()
    disposible.clear()
}

}
