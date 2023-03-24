package com.chunglyric.taipeicityzootour.model

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

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
        @Parcelize
        data class Guide(
            val _id: Int,
            val e_category: String,
            val e_info: String,
            val e_memo: String,
            val e_name: String,
            val e_pic_url: String,
            val e_url: String
        ) : Parcelable {
            override fun toString(): String {
                return Uri.encode(Gson().toJson(this))
            }
        }
    }
}

class AreaGuideType : NavType<AreaGuide.Metadata.Guide>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): AreaGuide.Metadata.Guide? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, AreaGuide.Metadata.Guide::class.java)
        } else {
            @Suppress("DEPRECATION") bundle.getParcelable(key)
        }
    }

    override fun parseValue(value: String): AreaGuide.Metadata.Guide {
        return Gson().fromJson(value, AreaGuide.Metadata.Guide::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: AreaGuide.Metadata.Guide) {
        bundle.putParcelable(key, value)
    }
}
