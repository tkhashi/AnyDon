package com.ppgm.anydon.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.File

@Parcelize
data class LocalMedia (
    val file: File,
    val mediaType: String,
) : Parcelable {
    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

}
