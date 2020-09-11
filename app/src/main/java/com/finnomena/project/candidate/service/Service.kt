package com.finnomena.project.candidate.service

import io.reactivex.Observable
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface Service {

    @GET("pokedex/2")
    fun getPokemon(): Call<ResponseBody>

    @GET("pokemon/{name}")
    fun getSearchName(
        @Path("name") name: String
    ): Call<ResponseBody>

//    @Multipart
//    @POST("pokemon/{name}")
//    fun getSearchName(
//        @Part("name") name: String
//    ): Call<ResponseBody>
}