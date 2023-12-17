package com.powersoft.foodiepal_culinarycompanion.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BlogPost(
    val title: String,
    val content: String
) : Parcelable
