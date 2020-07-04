package com.example.investigation.model

import android.telecom.Call
import io.reactivex.Single
import retrofit2.http.*

interface RegisterApi {
    @Headers("Content-Type: application/json", "ApiKeyHeader: MyKey")
    @POST("ChatUserRegistration")
    fun getUserDetails(@Body request:Register): Single<RegResponse>

    @Headers("Content-Type: application/json", "ApiKeyHeader: MyKey")
    @POST("ChatUserRegistration")
    fun addFriend(@Body request: AddingFriend): Single<RegResponse>

}