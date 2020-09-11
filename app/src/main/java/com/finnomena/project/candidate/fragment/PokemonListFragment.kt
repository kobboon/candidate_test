package com.finnomena.project.candidate.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.finnomena.project.candidate.R
import com.finnomena.project.candidate.adapter.PokemonListAdapter
import com.finnomena.project.candidate.util.ManageFragment
import com.finnomena.project.candidate.util.OnItemClickListener
import com.finnomena.project.candidate.util.ViewModelProvider
import com.finnomena.project.candidate.viewModel.PokemonViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_pokemon_list.view.*

class PokemonListFragment : Fragment(), OnItemClickListener {

    private val pokemonViewModel by lazy {
        ViewModelProvider.getType(
            PokemonViewModel::class.java
        ) as PokemonViewModel
    }

    private lateinit var adapter: PokemonListAdapter
    private val compositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pokemon_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemDecorator = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(ContextCompat.getDrawable(activity!!, R.drawable.divider)!!)
        view.recyclerView.addItemDecoration(itemDecorator)
        view.recyclerView.layoutManager = LinearLayoutManager(activity)

        adapter = PokemonListAdapter(activity!!, this)
        view.recyclerView.adapter = adapter
        getPokemon()
        searchPokemon()
        view.edtSearch.addTextChangedListener(textWatcher)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("<S","======>onDestroyView")
        this.view!!.edtSearch.removeTextChangedListener(textWatcher)
        compositeDisposable.clear()
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
            pokemonViewModel.searchPokemon(s.toString())
        }
    }

    private fun getPokemon() {
        pokemonViewModel.getPokemonSingle()
            .subscribeBy(
                onSuccess = {
                    adapter.setDataList(it)
                }
                , onError = {
                    it.printStackTrace()
                }
            )
            .addTo(compositeDisposable)
    }

    override fun onItemClick(position: Int, name: String) {
        val fragment = PokemonDetailFragment()
        fragment.arguments = Bundle().apply {
            putString("name", name)
        }
        ManageFragment.addFragment(
            fragmentManager!!,
            fragment,
            R.id.layout_content
        )
    }

    private fun searchPokemon() {
        pokemonViewModel.getSearchPokemon()
            .subscribeBy(
                onNext = {
                    adapter.setDataList(it)
                }
                , onError = {
                    it.printStackTrace()
                }
            )
            .addTo(compositeDisposable)
    }

}