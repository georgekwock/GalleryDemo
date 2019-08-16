package com.eurowings.gallerydemo.views

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.eurowings.gallerydemo.GalleryRequestInfo
import com.eurowings.gallerydemo.Image
import com.eurowings.gallerydemo.data.ImageDataFactory

/**
 * @author  Qing Guo
 */
class ImageViewModel : ViewModel() {
    var itemPagedList: LiveData<PagedList<Image>>
    private var imageDataSourceFactory: ImageDataFactory
    var pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(30)
        .build()

    init {
        imageDataSourceFactory = ImageDataFactory(GalleryRequestInfo())
        itemPagedList = LivePagedListBuilder(imageDataSourceFactory, pagedListConfig)
            .build()
    }

    open fun updatePageList(request: GalleryRequestInfo) {
        imageDataSourceFactory = ImageDataFactory(request)
        itemPagedList = LivePagedListBuilder(imageDataSourceFactory, pagedListConfig)
            .build()
    }

}