package com.jlapp.pokermao.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pokemon(
    val number: String,
    val name: String,
    var ps: Int,
    var attack: Int,
    var defense: Int,
    var velocity: Int,
    val description: String,
    val imageURL: String,
    val generation: Int
) : Parcelable