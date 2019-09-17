package com.jlapp.pokermao.di

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.jlapp.pokermao.api.AuthInterceptor
import com.jlapp.pokermao.api.PokemonService
import com.jlapp.pokermao.repository.PokemonRepository
import com.jlapp.pokermao.repository.PokemonRepositoryImpl
import com.jlapp.pokermao.utils.URLProvider
import com.jlapp.pokermao.view.detail.DetailViewModel
import com.jlapp.pokermao.view.form.FormPokemonViewModel
import com.jlapp.pokermao.view.list.ListPokemonsViewModel
import com.jlapp.pokermao.view.splash.SplashViewModel
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { ListPokemonsViewModel(get()) }
    viewModel { FormPokemonViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}

val repositoryModule = module {
    single<PokemonRepository> { PokemonRepositoryImpl(get()) }
}

val networkModule = module {
    single<Interceptor> { AuthInterceptor() }
    single { createOkhttpClientAuth(get()) }
    single { createNetworkClient(get(),
        get(named("baseURL"))
    ).create(PokemonService::class.java) }
    single { createPicassoAuth(get(), get()) }
    single(named("baseURL")) { URLProvider.baseURL }
}

private fun createPicassoAuth(context: Context, client: OkHttpClient) : Picasso {
    return Picasso.Builder(context)
        .downloader(OkHttp3Downloader(client))
        .build()
}

private fun createOkhttpClientAuth(authInterceptor: Interceptor): OkHttpClient {
    val builder = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addNetworkInterceptor(StethoInterceptor())
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
    return builder.build()
}

private fun createNetworkClient(okHttpClient: OkHttpClient, baseURL: String): Retrofit {
    return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

