package com.example.investigation.model

import android.telecom.Call
import io.reactivex.Single
import retrofit2.http.*

interface RegisterApi {
    @Headers("Content-Type: application/json")
    @POST("ChatUserRegistration")
    fun getUserDetails(@Body request:Register): Single<RegResponse>

}