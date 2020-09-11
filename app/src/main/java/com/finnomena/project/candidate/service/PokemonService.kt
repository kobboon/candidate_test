package com.finnomena.project.candidate.service

import android.util.Log
import com.finnomena.project.candidate.model.PokemonDetailModel
import com.finnomena.project.candidate.model.PokemonModel
import com.google.gson.Gson
import io.reactivex.Single
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonService {

    fun getPokemonSingle(): Single<PokemonModel> {
        return Single.create {
            val service = BuildServiceRetrofit.create().getPokemon()
            service.enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.e("<S", "GetPokemonSingle => onFailure : ${t.printStackTrace()}")
                    it.onError(t)
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        val res = response.body()?.string() ?: "{}"
//                        Log.d("<S", "GetPokemonSingle response =>  : $res")
                        val model = Gson().fromJson(res, PokemonModel::class.java)
                        it.onSuccess(model)
                    } else {
                        it.onError(Exception(response.errorBody()?.string()))
                    }


                }
            })
        }
    }

    fun getPokemonNameSingle(name: String): Single<PokemonDetailModel> {
        return Single.create {
            val service = BuildServiceRetrofit.create().getSearchName(name)
            Log.d("<S", "GetPokemonNameSingle url =>  : ${service.request().url()}")
            service.enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.e("<S", "GetPokemonNameSingle => onFailure : ${t.printStackTrace()}")
                    it.onError(t)
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        val res = response.body()?.string() ?: "{}"
                        Log.d("<S", "GetPokemonNameSingle response =>  : $res")
                        val model = Gson().fromJson(res, PokemonDetailModel::class.java)
                        it.onSuccess(model)
                    } else {
                        it.onError(Exception(response.errorBody()?.string()))
                    }
                }

            })
        }
    }

}