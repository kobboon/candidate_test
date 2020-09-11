package com.finnomena.project.candidate.model

class PokemonModel {
    val pokemon_entries: ArrayList<PokemonEntries> = arrayListOf()
}

class PokemonEntries {
    val pokemon_species: PokemonSpecies? = null
    val name :String get() = pokemon_species?.name ?: ""
    val url :String get() = pokemon_species?.url ?: ""
}

class PokemonSpecies {
    val name: String? = null
    val url: String? = null
}