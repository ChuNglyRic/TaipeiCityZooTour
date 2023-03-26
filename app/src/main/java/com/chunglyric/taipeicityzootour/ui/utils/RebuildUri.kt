package com.chunglyric.taipeicityzootour.ui.utils

import android.net.Uri

fun RebuildUri(
    url: String,
    reloadImageUiState: ReloadImageUiState
): String {
    if (url.isEmpty() || reloadImageUiState.timestamp.isEmpty()) return url

    val originUri = Uri.parse(url)
    val builder = Uri.Builder()
    builder.scheme(originUri.scheme)
    builder.authority(originUri.authority)
    builder.appendPath(originUri.path)
    originUri.queryParameterNames.forEach {
        builder.appendQueryParameter(it, originUri.getQueryParameter(it))
    }
    builder.appendQueryParameter("time", reloadImageUiState.timestamp)
    return builder.toString()
}
