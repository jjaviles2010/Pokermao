package com.jlapp.pokermao.view.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jlapp.pokermao.model.Pokemon
import com.jlapp.pokermao.repository.PokemonRepository

class ListPokemonsViewModel (val pokemonRepository: PokemonRepository) : ViewModel() {

    val messageError: MutableLiveData<String> = MutableLiveData()
    val pokemons: MutableLiveData<List<Pokemon>> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun getPokemons() {
        isLoading.value = true
        pokemonRepository.getPokemons(
            150, "number,asc", {
                isLoading.value = false
                pokemons.value = it
                messageError.value = ""
            }, {
                pokemons.value = emptyList()
                messageError.value = it?.message
                isLoading.value = false
            }
        )
    }
}
