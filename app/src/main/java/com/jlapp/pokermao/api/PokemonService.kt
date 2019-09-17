package com.jlapp.pokermao.api

import com.jlapp.pokermao.model.HealthResponse
import com.jlapp.pokermao.model.Pokemon
import com.jlapp.pokermao.model.PokemonResponse
import retrofit2.Call
import retrofit2.http.*

interface PokemonService {

    @GET("/api/pokemon/health")
    fun checkHealth() : Call<HealthResponse>

    @GET("/api/pokemon/{number}")
    fun getPokemon(
        @Path("number")number: String
    ): Call<Pokemon>

    @GET("/api/pokemon")
   fun getPokemos(
        @Query("size") size: Int,
        @Query("sort") sort: String
    ):Call<PokemonResponse>

    @PUT("/api/pokemon")
    fun updatePokemon(@Body pokemon: Pokemon) : Call<Pokemon>
}