package com.jlapp.pokermao.api

import com.jlapp.pokermao.model.HealthResponse
import com.jlapp.pokermao.model.PokemonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonService {

    @GET("/api/pokemon/health")
    fun checkHealth() : Call<HealthResponse>

    @GET("/api/pokemon")
   fun getPokemos(
        @Query("size") size: Int,
        @Query("sort") sort: String
    ):Call<PokemonResponse>

}