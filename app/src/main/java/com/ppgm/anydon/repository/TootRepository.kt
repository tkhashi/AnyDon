package com.ppgm.anydon.repository

import com.ppgm.anydon.MastodonApi
import com.ppgm.anydon.entity.Media
import com.ppgm.anydon.entity.Toot
import com.ppgm.anydon.entity.UserCredential
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File

class TootRepository(
    private val userCredential: UserCredential
) {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    private val retrofit = Retrofit.Builder()
        .baseUrl(userCredential.instanceUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private val api = retrofit.create(MastodonApi::class.java)

    suspend fun fetchPublicTimeline(
        maxId: String?,
        onlyMedia: Boolean
    ) = withContext(Dispatchers.IO){
        api.fetchPublicTimeline(
            maxId = maxId,
            onlyMedia = onlyMedia
        )
    }

    suspend fun fetchHomeTimeline(
        maxId: String?
    ) = withContext(Dispatchers.IO){
        api.fetchHomeTimeline(
            accessToken = "Bearer ${userCredential.accessToken}",
            maxId = maxId
        )
    }

    suspend fun postToot(
        status: String,
        mediaIds: List<String>? = null
    ): Toot = withContext(Dispatchers.IO){
        return@withContext api.postToot(
            "Bearer ${userCredential.accessToken}",
            status,
            mediaIds,
        )
    }

    suspend fun postMedia(
        file: File,
        mediaType: String
    ) : Media = withContext(Dispatchers.IO){
        val part = MultipartBody.Part.createFormData(
            "file",
            file.name,
            RequestBody.create(MediaType.parse(mediaType), file)
        )

        return@withContext api.postMedia(
            "Bearer ${userCredential.accessToken}",
            part
        )

    }

    suspend fun delete(id: String) = withContext(Dispatchers.IO){
        api.deleteToot(
            "Bearer ${userCredential.accessToken}",
            id
        )
    }
}