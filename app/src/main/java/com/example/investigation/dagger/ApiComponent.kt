package com.example.investigation.dagger

import com.example.investigation.model.RegisterApiService
import com.example.investigation.viewmodel.MyChatListViewModel
import dagger.Component
import dagger.Module

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun injectRegisterApi(service:RegisterApiService)

    fun injectRegisterApiService(apiService: MyChatListViewModel)
}