package com.jlapp.pokermao.view.form

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jlapp.pokermao.model.Pokemon
import com.jlapp.pokermao.repository.PokemonRepository

class FormPokemonViewModel(val pokemonRepository: PokemonRepository) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val messageResponse = MutableLiveData<String>()

    fun updatePokemon(pokemon: Pokemon) {
        isLoading.value = true

        pokemonRepository.updatePokemon(pokemon, {
            isLoading.value = false
            messageResponse.value = "Dados gravados com sucesso"
        }, {
            isLoading.value = false
            messageResponse.value = it?.message
        })
    }
}