package com.eurowings.gallerydemo.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.eurowings.gallerydemo.GalleryRequestInfo
import com.eurowings.gallerydemo.Image

/**
 * @author  Qing Guo
 */
class ImageDataFactory(request: GalleryRequestInfo) : DataSource.Factory<Int, Image>() {
    private val imageLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, Image>>()
    private val request = request
    override fun create(): DataSource<Int, Image> {
        val imageDataSource = ImageDataSource(request)
        imageLiveDataSource.postValue(imageDataSource)
        return imageDataSource
    }

    fun getImageLiveDataSource(): MutableLiveData<PageKeyedDataSource<Int, Image>> {
        return imageLiveDataSource
    }
}