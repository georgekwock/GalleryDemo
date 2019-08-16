package com.eurowings.gallerydemo

open class Image {
    var id: String? = null
    var title: String? = null
    var description: String? = null
    var type: String? = null
    var animated: Boolean = false
    var width: Int = 0
    var height: Int = 0
    var size: Int = 0
    var views: Int = 0
    var bandwidth: Long = 0.toLong()
    var vote: String? = null
    var favorite: Boolean = false
    var account_url: String? = null
    var deletehash: String? = null
    var name: String? = null
    var link: String? = null

    override fun toString(): String {
        return "Image{" +
                "id='" + id + '\''.toString() +
                ", title='" + title + '\''.toString() +
                ", description='" + description + '\''.toString() +
                ", type='" + type + '\''.toString() +
                ", animated=" + animated +
                ", width=" + width +
                ", height=" + height +
                ", size=" + size +
                ", views=" + views +
                ", bandwidth=" + bandwidth +
                ", vote='" + vote + '\''.toString() +

                ", favorite=" + favorite +
                ", account_url='" + account_url + '\''.toString() +
                ", deletehash='" + deletehash + '\''.toString() +
                ", name='" + name + '\''.toString() +
                ", link='" + link + '\''.toString() +
                '}'.toString()
    }
}

data class GalleryRequestInfo(
    var section: String = Section.TOP.value,
    var sort: String = Sort.TOP.value,
    var window: String = Window.ALL.value,
    var page: Int = 0,
    var showViral: Boolean = true
)

enum class Section(val value: String) {
    TOP("top"), HOT("hot"), USER("user")
}

enum class Sort(val value: String) {
    TOP("top"), VIRAL("viral"), TIME("time"), RISING("rising")
}

enum class Window(val value: String) {
    DAY("day"), WEEK("week"), MONTH("month"), YEAR("year"), ALL("all")
}