package com.eurowings.gallerydemo.api

import com.eurowings.gallerydemo.ResponseImage
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ServerApi {

    @Headers("Authorization: Client-ID $AUTH")
    @GET("gallery/{section}/{sort}/{window}/{page}/")
    fun getGallery(
        @Path("section") section: String, @Path("sort") sort: String, @Path("window") window: String, @Path("page") page: Int, @Query(
            "showViral"
        ) viral: Boolean
    ): Call<ResponseImage>


    companion object {
        const val AUTH = "fecfbc47e85d660"
    }

}