package com.jlapp.pokermao.view.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.jlapp.pokermao.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.include_loading.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    val detailViewModel: DetailViewModel by viewModel()
    val picasso: Picasso by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        detailViewModel.getPokemon(intent.getStringExtra("POKEMON_NUMBER"))

        detailViewModel.pokemon.observe(this, Observer {
            picasso.load("https://pokedexdx.herokuapp.com${it.imageURL}")
                .into(ivPokemon)
            tvPokemonName.text = "${it.number}${it.name}"
        })

        detailViewModel.messageError.observe(this, Observer {
            if(it != "")
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

        detailViewModel.isLoading.observe(this, Observer {
            if(it == true) {
                containerLoading.visibility = View.VISIBLE
            }else{
                containerLoading.visibility = View.GONE
            }
        } )
    }
}
