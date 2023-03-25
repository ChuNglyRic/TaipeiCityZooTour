package com.chunglyric.taipeicityzootour.model

class GuidesCache(
    private val areaDataList: List<AreaGuide.Data>
) {
    val areaData: List<AreaGuide.Data> get() = areaDataList
}
