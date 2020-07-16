package com.example.investigation.model


import com.example.investigation.dagger.DaggerApiComponent
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RegisterApiService {

    @Inject
    lateinit var api:RegisterApi

    init {
        DaggerApiComponent.create().injectRegisterApi(this)
    }

    fun getUserDetailsService(obj:Register):Single<RegResponse>{
        return api.getUserDetails(obj)
    }

    fun addFrinedService(obj:AddingFriend):Single<RegResponse>{
        return api.addFriend(obj)
    }
}

