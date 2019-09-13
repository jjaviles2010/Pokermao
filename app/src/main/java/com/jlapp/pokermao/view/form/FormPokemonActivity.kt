package com.jlapp.pokermao.view.form

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.jlapp.pokermao.R
import com.jlapp.pokermao.model.Pokemon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_form_pokemon.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class FormPokemonActivity : AppCompatActivity() {

    val formPokemonViewModel: FormPokemonViewModel by viewModel()
    val baseUrl : String by inject(named("baseURL"))
    val picasso: Picasso by inject()

    lateinit var pokemon : Pokemon

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_pokemon)

        setValues()

        formPokemonViewModel.messageResponse.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

        btSaveForm.setOnClickListener {
            pokemon.attack = sbAttack.progress
            pokemon.defense = sbDefense.progress
            pokemon.velocity = sbVelocity.progress
            pokemon.ps = sbPS.progress

            formPokemonViewModel.updatePokemon(pokemon)
        }
    }

    private fun setValues() {
        pokemon = intent.getParcelableExtra("POKEMON")
        tvPokemonNameForm.text = pokemon.name
        picasso.load("$baseUrl${pokemon.imageURL}").into(ivPokemonForm)

        /*sbAttack.progress = pokemon.attack
        sbDefense.progress = pokemon.defense
        sbPS.progress = pokemon.ps
        sbVelocity.progress = pokemon.velocity*/

        setSeekbar(sbAttack, tvAttackValue, pokemon.attack)
        setSeekbar(sbDefense, tvDefenseValue, pokemon.defense)
        setSeekbar(sbPS, tvPSValue, pokemon.ps)
        setSeekbar(sbVelocity, tvVelocityValue, pokemon.velocity)
    }

    private fun setSeekbar(seekBar: SeekBar, textView: TextView, value: Int) {
        seekBar.progress = value
        textView.text = value.toString()

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                textView.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }
}
