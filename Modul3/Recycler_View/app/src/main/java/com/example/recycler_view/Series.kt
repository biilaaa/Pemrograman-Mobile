package com.example.recycler_view

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Series(
    val title: String,
    val link: String,
    val photo: Int,
    val plot: String,
    val year: String,
    val cast: String
) : Parcelable