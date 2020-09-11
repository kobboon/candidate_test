package com.finnomena.project.candidate.model

class PokemonDetailModel {
    private val name: String? = null
    private val sprites: Sprites? = null

    val types: ArrayList<Types> = arrayListOf()
    val height: Int = 0
    val weight: Int = 0

    val namePokemon: String get() = this.name ?: ""
    val imgDefault: String
        get() {
            return this.sprites?.let { it.front_default ?: "" } ?: ""
        }
}

class Types {
    val type: Type? = null
}

class Type {
    val name: String? = null
}

class Sprites {
    val front_default: String? = null
}

