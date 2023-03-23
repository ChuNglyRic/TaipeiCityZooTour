package com.chunglyric.taipeicityzootour.model

data class AreaGuide(
    val result: Metadata
) {
    data class Metadata(
        val count: Int,
        val limit: Int,
        val offset: Int,
        val results: List<Guide>,
        val sort: String
    ) {
        data class Guide(
            val _id: Int,
            val e_info: String,
            val e_memo: String,
            val e_name: String,
            val e_pic_url: String,
        )
    }
}
