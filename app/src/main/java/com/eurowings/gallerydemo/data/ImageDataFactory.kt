package com.eurowings.gallerydemo.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.eurowings.gallerydemo.GalleryRequestInfo
import com.eurowings.gallerydemo.Image

/**
 * @author  Qing Guo
 */
class ImageDataFactory : DataSource.Factory<Int, Image>() {
    private val imageLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, Image>>()
    private lateinit var imageDataSource: ImageDataSource
    private var requestInfo = GalleryRequestInfo()

    override fun create(): DataSource<Int, Image> {
        getRequest()
        imageDataSource = ImageDataSource(requestInfo)
        imageLiveDataSource.postValue(imageDataSource)
        return imageDataSource
    }

    fun setRequest(request: GalleryRequestInfo) {
        requestInfo = request
    }

    fun getRequest(): GalleryRequestInfo {
        return requestInfo
    }

    fun getImageLiveDataSource(): MutableLiveData<PageKeyedDataSource<Int, Image>> {
        return imageLiveDataSource
    }
}