package com.eurowings.gallerydemo

open class ResponseImage {
    var success: Boolean = false
    var status: Int = 0
    private var data: List<Image>? = null

    override fun toString(): String {
        return "ImageResponse{" +
                "success=" + success +
                ", status=" + status +
                ", data=" + data!![0] +
                '}'.toString()
    }

    fun getData(): List<Image>? {
        return data
    }


}