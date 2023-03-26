package com.chunglyric.taipeicityzootour.data.guides.impl

import com.chunglyric.taipeicityzootour.model.AnimalGuide
import com.chunglyric.taipeicityzootour.model.AreaGuide

const val INVALID_DATA_ID = -1

val invalidAreaData = AreaGuide.Data(
    _id = INVALID_DATA_ID,
    e_category = "",
    e_info = "請按返回回到上一頁",
    e_memo = "",
    e_name = "找不到此資料",
    e_pic_url = "",
    e_url = ""
)

val areaData1 = AreaGuide.Data(
    _id = 1,
    e_category = "戶外區",
    e_info = "臺灣位於北半球，北迴歸線橫越南部，造成亞熱帶溫和多雨的氣候。又因高山急流、起伏多樣的地形，因而在這蕞爾小島上，形成了多樣性的生態系，孕育了多種不同生態習性的動、植物，豐富的生物物種共存共榮於此，也使臺灣博得美麗之島「福爾摩沙」的美名。臺灣動物區以臺灣原生動物與棲息環境為展示重點，佈置模擬動物原生棲地之生態環境，讓動物表現如野外般自然的生活習性，引導民眾更正確地認識本土野生動物，為園區環境教育的重要據點。藉由提供動物寬廣且具隱蔽的生態環境，讓動物避開人為過度的干擾，並展現如野外般自然的生活習性和行為。展示區以多種臺灣的保育類野生動物貫連成保育廊道，包括臺灣黑熊、穿山甲、歐亞水獺、白鼻心、石虎、山羌等。唯有認識、瞭解本土野生動物，才能愛護、保育牠們，並進而珍惜我們共同生存的這塊土地！",
    e_memo = "",
    e_name = "臺灣動物區",
    e_pic_url = "http://www.zoo.gov.tw/iTAP/05_Exhibit/01_FormosanAnimal.jpg",
    e_url = "https://www.youtube.com/watch?v=QIUbzZ-jX_Y"
)

var invalidAnimalData = AnimalGuide.Data(
    _id = -1,
    a_alsoknown = "",
    a_behavior = "",
    a_crisis = "",
    a_distribution = "",
    a_feature = "",
    a_habitat = "",
    a_location = "",
    a_name_ch = "找不到此資料",
    a_name_en = "請按返回回到上一頁",
    a_pic01_alt = "",
    a_pic01_url = "",
    a_update = ""
)

var animalData1 = AnimalGuide.Data(
    _id = 1,
    a_alsoknown = "華雞、山雞、畦雞、藍鷳、臺灣藍鷳、斯文豪氏鷴、臺灣山雞",
    a_behavior = "為一夫多妻制，雄鳥的領域性很強，所以一個欄舍中只能圈養一隻雄鳥，否則會搏命鬥毆。",
    a_crisis = "",
    a_distribution = "臺灣",
    a_feature = "1、 為臺灣特有種鳥類。\\r\\n 2、 最大的特徵就是雄鳥羽冠和背部是鮮明的白色，尾部也有兩根漂亮的白色長羽，為原住民頭上常用的飾羽。",
    a_habitat = "原始林及成熟的次生林，出現於海拔300-2000公尺間",
    a_location = "鳥園區;臺灣動物區",
    a_name_ch = "藍腹鷴",
    a_name_en = "Swinhoe's Pheasant",
    a_pic01_alt = "藍腹鷴",
    a_pic01_url = "http://www.zoo.gov.tw/iTAP/03_Animals/BirdWorld/Pheasant-S/Pheasant-S_Pic01.jpg",
    a_update = ""
)
