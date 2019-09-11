package com.jlapp.pokermao.repository

import com.jlapp.pokermao.api.PokemonService
import com.jlapp.pokermao.model.HealthResponse
import com.jlapp.pokermao.model.Pokemon
import com.jlapp.pokermao.model.PokemonResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonRepositoryImpl(val pokemonService: PokemonService) : PokemonRepository {

    override fun updatePokemon(
        pokemon: Pokemon,
        onComplete: (Pokemon?) -> Unit,
        onError: (Throwable?) -> Unit
    ) {
        pokemonService.updatePokemon(pokemon)
            .enqueue(object : Callback<Pokemon> {
                override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                    if(response.isSuccessful) {
                        onComplete(response.body())
                    } else {
                        onError(Throwable("Erro na requisição"))
                    }
                }
            })
    }

    override fun getPokemons(
        size: Int,
        sort: String,
        onComplete: (List<Pokemon>?) -> Unit,
        onError: (Throwable?) -> Unit
    ) {
        pokemonService.getPokemos(size, sort)
            .enqueue(object : Callback<PokemonResponse>{
                override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(
                    call: Call<PokemonResponse>,
                    response: Response<PokemonResponse>
                ) {
                    if(response.isSuccessful)
                        onComplete(response.body()?.pokemons)
                    else
                        onError(Throwable("Erro ao carregar a lista"))
                }

            })
    }

    override fun checkHealth(
        onComplete: () -> Unit,
        onError: (Throwable?) -> Unit
    ) {
        pokemonService.checkHealth()
            .enqueue(object : Callback<HealthResponse>{
                override fun onFailure(call: Call<HealthResponse>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(
                    call: Call<HealthResponse>,
                    response: Response<HealthResponse>
                ) {
                   if(response.isSuccessful){
                       onComplete()
                   } else {
                       onError(Throwable("Nao foi possivel realizar a chamada"))
                   }
                }

            })


    }

}