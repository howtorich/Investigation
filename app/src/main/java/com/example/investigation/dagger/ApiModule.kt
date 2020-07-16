package com.example.investigation.dagger
import com.example.investigation.model.RegisterApi
import com.example.investigation.model.RegisterApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {
    private val BASE_URL: String = "http://35.154.5.57/SocialCommunicationsApi/SocialCommunication/"

    @Provides
    fun proviesRegisterApi(): RegisterApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(RegisterApi::class.java)
    }

    @Provides
    fun providesRegisterApiService():RegisterApiService{
        return RegisterApiService()
    }
}