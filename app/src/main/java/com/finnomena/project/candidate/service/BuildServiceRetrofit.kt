package com.finnomena.project.candidate.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BuildServiceRetrofit {

    fun create(): Service {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create<Service>(Service::class.java)
    }
}