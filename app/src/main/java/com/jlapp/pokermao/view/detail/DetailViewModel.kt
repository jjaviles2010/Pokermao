package com.jlapp.pokermao.view.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jlapp.pokermao.model.Pokemon
import com.jlapp.pokermao.repository.PokemonRepository

class DetailViewModel (
    val pokemonRepository: PokemonRepository
) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val pokemon = MutableLiveData<Pokemon>()
    val messageError = MutableLiveData<String>()

    fun getPokemon(number: String) {
        isLoading.value = true

        pokemonRepository.getPokemon(
            number,
            onComplete = {
                isLoading.value = false
                pokemon.value = it
            },
            onError = {
                isLoading.value = false
                messageError.value = it?.message
            }
        )
    }

}