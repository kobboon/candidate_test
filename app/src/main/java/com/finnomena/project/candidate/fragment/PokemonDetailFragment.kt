package com.finnomena.project.candidate.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.finnomena.project.candidate.R
import com.finnomena.project.candidate.util.ViewModelProvider
import com.finnomena.project.candidate.viewModel.PokemonViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_pokemon_detail.view.*

class PokemonDetailFragment : Fragment() {


    private val pokemonViewModel by lazy {
        ViewModelProvider.getType(
            PokemonViewModel::class.java
        ) as PokemonViewModel
    }

    private val compositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pokemon_detail, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val name = it.getString("name")
            getPokemonName(name!!)
        }
    }

    private fun getPokemonName(name: String) {
        pokemonViewModel.getPokemonNameSingle(name)
            .subscribeBy(
                onSuccess = { data ->
                    this.view!!.let {
                        Glide.with(it)
                            .load(data.imgDefault)
                            .error(android.R.drawable.stat_notify_error)
                            .into(it.image)
                        it.txtName.text = data.namePokemon
                        it.txtWeight.text = data.weight.toString()
                        it.txtHeight.text = data.height.toString()
                        it.txtTypes.text = pokemonViewModel.getTypePokemon(data.types)
                    }
                }
                , onError = {
                    it.printStackTrace()
                }
            )
            .addTo(compositeDisposable)
    }
}