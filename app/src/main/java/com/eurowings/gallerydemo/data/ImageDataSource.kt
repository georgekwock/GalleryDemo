package com.eurowings.gallerydemo.data

import androidx.paging.PageKeyedDataSource
import com.eurowings.gallerydemo.GalleryRequestInfo
import com.eurowings.gallerydemo.Image
import com.eurowings.gallerydemo.ResponseImage
import com.eurowings.gallerydemo.api.RetrofitClientInstance
import com.eurowings.gallerydemo.api.ServerApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author  Qing Guo
 */
class ImageDataSource(private var requestInfo: GalleryRequestInfo) : PageKeyedDataSource<Int, Image>() {
    val FIRST_PAGE = 0
    private var api: ServerApi = RetrofitClientInstance.retrofitInstatce!!.create(ServerApi::class.java)

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Image>) {
        requestInfo
        api.getGallery(
            requestInfo.section,
            requestInfo.sort,
            requestInfo.window,
            requestInfo.page,
            requestInfo.showViral
        ).enqueue(object : Callback<ResponseImage> {
            override fun onFailure(call: Call<ResponseImage>, t: Throwable) {

            }

            override fun onResponse(call: Call<ResponseImage>, response: Response<ResponseImage>) {
                callback.onResult(
                    response.body()!!.getData()!!.filter { element -> element.type != null },
                    null,
                    FIRST_PAGE + 1
                )
            }

        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Image>) {
        api.getGallery(
            requestInfo.section,
            requestInfo.sort,
            requestInfo.window,
            params.key,
            requestInfo.showViral
        ).enqueue(object : Callback<ResponseImage> {
            override fun onFailure(call: Call<ResponseImage>, t: Throwable) {

            }

            override fun onResponse(call: Call<ResponseImage>, response: Response<ResponseImage>) {
                callback.onResult(
                    response.body()!!.getData()!!.filter { element -> element.type != null },
                    params.key + 1
                )
            }

        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Image>) {
        api.getGallery(
            requestInfo.section,
            requestInfo.sort,
            requestInfo.window,
            params.key,
            requestInfo.showViral
        ).enqueue(object : Callback<ResponseImage> {
            override fun onFailure(call: Call<ResponseImage>, t: Throwable) {

            }

            override fun onResponse(call: Call<ResponseImage>, response: Response<ResponseImage>) {
                val key = if (params.key > 1) params.key - 1 else null
                callback.onResult(
                    response.body()!!.getData()!!.filter { element -> element.type != null }, key
                )
            }

        })
    }
}