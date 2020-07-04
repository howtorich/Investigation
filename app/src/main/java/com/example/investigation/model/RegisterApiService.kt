package com.example.investigation.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RegisterApiService {

    private val BASE_URL:String="http://35.154.5.57/SocialCommunicationsApi/SocialCommunication/"
    private val api=Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(RegisterApi::class.java)

    fun getUserDetailsService(obj:Register):Single<RegResponse>{
        return api.getUserDetails(obj)
    }

    fun addFrinedService(obj:AddingFriend):Single<RegResponse>{
        return api.addFriend(obj)
    }
}

