package com.example.investigation.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.example.investigation.model.RegResponse
import com.example.investigation.model.Register
import com.example.investigation.model.RegisterApiService
import com.example.investigation.utils.SharedPreferenceHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class RegisterViewModel(application: Application):AndroidViewModel(application) {
    private val apiService= RegisterApiService()
    private val disposible= CompositeDisposable()
    var result:Boolean = false

    private  val prefs= SharedPreferenceHelper(getApplication())

   fun postRegisterDetails(response:Register):Boolean{
       disposible.add(
           apiService.getUserDetailsService(response)
               .subscribeOn(Schedulers.newThread())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribeWith(object:DisposableSingleObserver<RegResponse>(){
                   override fun onSuccess(response: RegResponse) {
                       var v1=response.chatRegisterUserOutput?.userName.toString()
                       var v2=response.chatRegisterUserOutput?.userId.toString()

                       prefs.saveUname(v1)
                       prefs.saveUID(v2)
                        result=true
                   }

                   override fun onError(e: Throwable) {
                       e.printStackTrace()
                       Log.e("myTag", e.message.toString());
                       Toast.makeText(getApplication(),"data failed to save",Toast.LENGTH_SHORT).show()

                   }


               })
       )

        return result
    }


    override fun onCleared() {
        super.onCleared()
        disposible.clear()
    }
}
