package com.chunglyric.taipeicityzootour.model

class GuidesCache(
    private val areaGuideList: List<AreaGuide.Metadata.Guide>
) {
    val areaGuides: List<AreaGuide.Metadata.Guide> get() = areaGuideList
}
