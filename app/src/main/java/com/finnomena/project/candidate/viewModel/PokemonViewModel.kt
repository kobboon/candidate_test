package com.finnomena.project.candidate.viewModel

import android.text.TextUtils
import androidx.lifecycle.ViewModel
import com.finnomena.project.candidate.model.PokemonDetailModel
import com.finnomena.project.candidate.model.PokemonEntries
import com.finnomena.project.candidate.model.Types
import com.finnomena.project.candidate.service.PokemonService
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject


class PokemonViewModel : ViewModel() {

    private val service: PokemonService = PokemonService()
    private var pokemonList: ArrayList<PokemonEntries> = arrayListOf()
    private var pokemonSearch: BehaviorSubject<ArrayList<PokemonEntries>> =
        BehaviorSubject.create()

//    fun getPokemonSingle(isFetch: Boolean = true): Single<ArrayList<PokemonEntries>> {
//        return if (pokemonList == null || isFetch) {
//            service.getPokemonSingle().doOnSuccess {
//                pokemonList = it.pokemon_entries
//            }.map { it.pokemon_entries }
//        } else {
//            Single.just(pokemonList!!)
//        }
//    }


    fun getPokemonSingle(): Single<ArrayList<PokemonEntries>> {
        return service.getPokemonSingle().doOnSuccess {
            pokemonList = it.pokemon_entries
        }.map { it.pokemon_entries }
    }

    fun getPokemonNameSingle(name: String): Single<PokemonDetailModel> {
        return service.getPokemonNameSingle(name)
    }

    fun searchPokemon(srt: String) {
        val list = ArrayList(pokemonList.filter { it.name.contains(srt) })
        pokemonSearch.onNext(list)
    }

    fun getSearchPokemon(): BehaviorSubject<ArrayList<PokemonEntries>> {
        return pokemonSearch
    }


    fun getTypePokemon(types: ArrayList<Types>): String {
        val listName = types.map { it.type!!.name }
        return TextUtils.join(", ", listName)
    }

}