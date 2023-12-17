package com.powersoft.foodiepal_culinarycompanion.models

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe(
    val foodName: String,
    val foodDuration: String,
    @DrawableRes
    val image: Int,
    val userRating: Float,
    val imagePath: String? = null
) : Parcelable