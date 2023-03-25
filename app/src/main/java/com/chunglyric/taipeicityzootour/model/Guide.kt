package com.chunglyric.taipeicityzootour.model

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

data class Metadata<T>(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<T>,
    val sort: String
)

data class AreaGuide(
    val result: Metadata<Data>
) {
    @Parcelize
    data class Data(
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

class AreaGuideType : NavType<AreaGuide.Data>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): AreaGuide.Data? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, AreaGuide.Data::class.java)
        } else {
            @Suppress("DEPRECATION") bundle.getParcelable(key)
        }
    }

    override fun parseValue(value: String): AreaGuide.Data {
        return Gson().fromJson(value, AreaGuide.Data::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: AreaGuide.Data) {
        bundle.putParcelable(key, value)
    }
}
