package com.example.vcam.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MediaModel (
    var duration: Long = 0,
    var id: Int = 0,
    var isSelected: Boolean = false,
    var mediaName: String? = "",
    var mediaPath: String? = "",
    var title: String? = ""
) : Parcelable