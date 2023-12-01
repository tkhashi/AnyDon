package com.ppgm.anydon.entity

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Toot(
    val id: String,
    @Json(name="created_at") val createdAt: String,
    val sensitive: Boolean,
    val url: String,
    @Json(name = "media_attachments") val mediaAttachments: List<Media>,
    val content: String,
    val account: Account
) : Parcelable{
    val topMedia: Media?
        get() = mediaAttachments.firstOrNull()

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

}
