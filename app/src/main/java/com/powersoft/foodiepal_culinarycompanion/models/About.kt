package com.powersoft.foodiepal_culinarycompanion.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class About(
    val culinaryJourney: String,
    val favoriteRecipe: String,
    val foodPhilosophy: String
) : Parcelable