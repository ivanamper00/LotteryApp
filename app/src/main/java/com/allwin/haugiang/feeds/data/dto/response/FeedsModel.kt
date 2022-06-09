package com.allwin.haugiang.feeds.data.dto.response

import androidx.annotation.Keep
import org.simpleframework.xml.*

@Keep
@Root(name = "rss", strict=false)
data class FeedsModel @JvmOverloads constructor(
    @field:Element(name = "channel", required = false)
    var channel: Channel? = null,
    @field:Element(name = "version", required = false)
    var version: String? = ""
){
    @Keep
    @Root(name = "channel", strict=false)
    data class Channel @JvmOverloads constructor(
        @field:ElementList(
            required = false,
            name = "item",
            entry = "item",
            inline = true,
            empty = true
        )
        var item: ArrayList<Item>? = arrayListOf(),
        @field:Element(name = "lastBuildDate", required = false)
        var lastBuildDate: String? = "",
        @field:Element(name = "link", required = false)
        var link: String? = "",
        @field:Element(name = "description", required = false)
        var description: String? = "",
        @field:Element(name = "generator", required = false)
        var generator: String? = "",
        @field:Element(name = "language", required = false)
        var language: String? = "",
        @field:Element(name = "title", required = false)
        var title: String? = "",
    ){
        @Keep
        @Root(name = "item", strict=false)
        data class Item @JvmOverloads constructor(
            @field:ElementList(
                required = false,
                name = "comments",
                entry = "comments",
                inline = true,
                empty = true
            )
            var comments: ArrayList<String>? = arrayListOf(),
            @field:Element(name = "link", required = false)
            var link: String? = "",
            @field:Element(name = "guid", required = false)
            var guid: Guid? = null,
            @field:Element(name = "description", required = false)
            var description: String? = "",
            @field:Element(name = "title", required = false)
            var title: String? = "",
            @field:ElementList(
                required = false,
                name = "category",
                entry = "category",
                inline = true,
                empty = true
            )
            var category: ArrayList<String>? = arrayListOf(),
            @field:Element(name = "pubDate", required = false)
            var pubDate: String? = "",
            @field:Element(name = "encoded", required = false)
            var contentEncoded: String? = "",
            @field:Element(name = "content", required = false)
            var content: Content? = null,
        ){
            @Keep
            @Root(name = "guid", strict=false)
            data class Guid @JvmOverloads constructor(
                @field:Attribute(name = "isPermaLink", required = false)
                var isPermaLink: String? = "",
                @field:Attribute(name = "text", required = false)
                var content: String? = "",
            )
            @Keep
            @Root(name = "content", strict=false)
            data class Content @JvmOverloads constructor(
                @field:Attribute(name = "url", required = false)
                var url: String? = "",
                @field:Attribute(name = "type", required = false)
                var type: String? = "",
                @field:Attribute(name = "width", required = false)
                var width: String? = "",
                @field:Attribute(name = "height", required = false)
                var height: String? = "",
            )
        }
    }
}
