package com.jlapp.pokermao.view.form

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jlapp.pokermao.R
import com.jlapp.pokermao.model.Pokemon
import com.jlapp.pokermao.utils.URLProvider
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_form_pokemon.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class FormPokemonActivity : AppCompatActivity() {

    val formPokemonViewModel: FormPokemonViewModel by viewModel()
    val baseUrl : String by inject()
    val picasso: Picasso by inject()

    lateinit var pokemon : Pokemon

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_pokemon)

        setValues()
    }

    private fun setValues() {
        pokemon = intent.getParcelableExtra("POKEMON")
        tvPokemonNameForm.text = pokemon.name
        picasso.load("$baseUrl${pokemon.imageURL}")

    }
}
