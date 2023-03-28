package com.chunglyric.taipeicityzootour.data.guides.impl

import com.chunglyric.taipeicityzootour.data.ResponseStatus
import com.chunglyric.taipeicityzootour.model.AnimalGuide
import com.chunglyric.taipeicityzootour.model.AreaGuide
import com.chunglyric.taipeicityzootour.service.ApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.*
import org.junit.Assert.*
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

const val AREA_GUIDE_RESULT_RAW =
    "{\"result\":{\"limit\":1,\"offset\":0,\"count\":16,\"sort\":\"\",\"results\":[{\"_id\":1,\"_importdate\":{\"date\":\"2022-05-09 14:47:38.971190\",\"timezone_type\":3,\"timezone\":\"Asia/Taipei\"},\"e_no\":\"1\",\"e_category\":\"戶外區\",\"e_name\":\"臺灣動物區\",\"e_pic_url\":\"http://www.zoo.gov.tw/iTAP/05_Exhibit/01_FormosanAnimal.jpg\",\"e_info\":\"臺灣位於北半球，北迴歸線橫越南部，造成亞熱帶溫和多雨的氣候。又因高山急流、起伏多樣的地形，因而在這蕞爾小島上，形成了多樣性的生態系，孕育了多種不同生態習性的動、植物，豐富的生物物種共存共榮於此，也使臺灣博得美麗之島「福爾摩沙」的美名。臺灣動物區以臺灣原生動物與棲息環境為展示重點，佈置模擬動物原生棲地之生態環境，讓動物表現如野外般自然的生活習性，引導民眾更正確地認識本土野生動物，為園區環境教育的重要據點。藉由提供動物寬廣且具隱蔽的生態環境，讓動物避開人為過度的干擾，並展現如野外般自然的生活習性和行為。展示區以多種臺灣的保育類野生動物貫連成保育廊道，包括臺灣黑熊、穿山甲、歐亞水獺、白鼻心、石虎、山羌等。唯有認識、瞭解本土野生動物，才能愛護、保育牠們，並進而珍惜我們共同生存的這塊土地！\",\"e_memo\":\"\",\"e_geo\":\"MULTIPOINT ((121.5805931 24.9985962))\",\"e_url\":\"https://youtu.be/QIUbzZ-jX_Y\"}]}}"
const val ANIMAL_GUIDE_RAW =
    "{\"result\":{\"limit\":1,\"offset\":0,\"count\":272,\"sort\":\"\",\"results\":[{\"_id\":1,\"_importdate\":{\"date\":\"2022-12-23 14:05:14.668579\",\"timezone_type\":3,\"timezone\":\"Asia\\/Taipei\"},\"a_name_ch\":\"大貓熊\",\"a_summary\":\"\",\"a_keywords\":\"\",\"a_alsoknown\":\"貓熊、熊貓\",\"a_geo\":\"MULTIPOINT ((121.5831587 24.9971109))\",\"a_location\":\"新光特展館（大貓熊館）\",\"a_poigroup\":\"\",\"a_name_en\":\"Giant Panda\",\"a_name_latin\":\"Ailuropoda melanoleuca\",\"a_phylum\":\"脊索動物門\",\"a_class\":\"哺乳綱\",\"a_order\":\"食肉目\",\"a_family\":\"熊科\",\"a_conservation\":\"易危(VU)\",\"a_distribution\":\"目前僅存於中國四川、甘肅和陜西三省境內。\",\"a_habitat\":\"海拔2600-3,000公尺的高山中，會因季節的變化而改變其居住的海拔高度，一般在乾淨的活水源和竹林發育良好的地區活動。\",\"a_feature\":\"1、 成熊身長約為120-180公分，體重約80-120公斤，幼熊出生時體長約10公分，體重約僅有150~200公克。\\n 2、 具有強壯有力的四肢：後腳內八字撇，有利於在密林中走動時撥開竹子。\\n 3、 腕骨特化成的偽拇指(不具備關節)，使得牠們能俐落地取食竹子。\\n 4、 掌心覆有毛，使得大貓熊能夠在寒冷的雪地上行走。\\n 5、大貓熊身體顏色主要為黑白兩色。耳朵、眼斑、鼻頭、肩背部和四肢為黑色，其餘部位為白色。相對比例較小的黑色耳朵有減少熱量散失的功能。\",\"a_behavior\":\"1、獨居的動物，除了交配季節或雌性的育幼時期，牠們都是獨自居住的。\\r\\n2、最活躍的時間在早晨和黃昏，但竹子所含熱量低，為減少能量的消耗，牠們每天的睡眠時間約10小時，剩餘時間則大多在覓食和進食。\",\"a_diet\":\"大貓熊以竹為主食(佔日糧中大約99%)\",\"a_crisis\":\"過去大貓熊棲息地裡的竹林竹種較為單一，1983年曾發生棲地內箭竹週期性大規模開花枯死而有大貓熊餓死的情形，竹林一旦開花，須再經多年後才能恢復舊觀。另外大貓熊棲息地的破碎化，使得牠們無法遷徙至其他有竹子的地方，加劇了這個原是林地自然演替的危害。也因棲地破碎化，部分種群因數量規模小，基因的窄化以及無法延續也成為另一嚴重問題。\",\"a_interpretation\":\"\",\"a_theme_name\":\"YA!大貓熊-臺北大貓熊保育網\",\"a_theme_url\":\"http:\\/\\/newweb.zoo.gov.tw\\/panda\\/\",\"a_adopt\":\"\",\"a_code\":\"Panda\",\"a_pic01_alt\":\"大貓熊「團團」和「圓圓」\",\"a_pic01_url\":\"http:\\/\\/www.zoo.gov.tw\\/iTAP\\/03_Animals\\/PandaHouse\\/Panda_Pic01.jpg\",\"a_pic02_alt\":\"「圓仔」跟媽媽互動\",\"a_pic02_url\":\"http:\\/\\/www.zoo.gov.tw\\/iTAP\\/03_Animals\\/PandaHouse\\/Panda_Pic02.jpg\",\"a_pic03_alt\":\"大貓熊「圓仔」\",\"a_pic03_url\":\"http:\\/\\/www.zoo.gov.tw\\/iTAP\\/03_Animals\\/PandaHouse\\/Panda_Pic03.jpg\",\"a_pic04_alt\":\"大貓熊「團團」和「圓圓」\",\"a_pic04_url\":\"http:\\/\\/www.zoo.gov.tw\\/iTAP\\/03_Animals\\/PandaHouse\\/Panda_Pic04.jpg\",\"a_pdf01_alt\":\"「遇見大貓熊」學習單(4.2MB)\",\"a_pdf01_url\":\"http:\\/\\/www.zoo.gov.tw\\/iTAP\\/03_Animals\\/PandaHouse\\/Panda_PDF01.pdf\",\"a_pdf02_alt\":\"「遇見大貓熊」解答(1.18MB)\",\"a_pdf02_url\":\"http:\\/\\/www.zoo.gov.tw\\/iTAP\\/03_Animals\\/PandaHouse\\/Panda_PDF02.pdf\",\"a_voice01_alt\":\"雌大貓熊咩叫聲\",\"a_voice01_url\":\"http:\\/\\/mediasys.taipei.gov.tw\\/tcg\\/service\\/KMStorage\\/355\\/894E598B-8A9F-BAA8-206D-8DF52A8C5763\\/Panda_Voice01.mp3\",\"a_voice02_alt\":\"雌雄大貓熊交配時的叫聲\",\"a_voice02_url\":\"http:\\/\\/mediasys.taipei.gov.tw\\/tcg\\/service\\/KMStorage\\/355\\/3FAC21EE-A863-6E2C-BF12-4E6FEF67BDE\\/Panda_Voice02.mp3\",\"a_voice03_alt\":\"山豬老大ABC第13集Bears\",\"a_voice03_url\":\"http:\\/\\/mediasys.taipei.gov.tw\\/tcg\\/service\\/KMStorage\\/355\\/ADB04074-6156-5C7C-1630-B4E88BAD5147\\/Panda_Voice03.mp3\",\"a_vedio_url\":\"http:\\/\\/www.youtube.com\\/playlist?list=PLWYak5Af5-DvboTzxQYeg7aKYA9UHYwSf\",\"a_update\":\"########\",\"a_cid\":\"1\"}]}}"

class FakeInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var responseString: String = when (chain.request().url().url().path) {
            "/api/v1/dataset/5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a" -> AREA_GUIDE_RESULT_RAW
            "/api/v1/dataset/a3e2b221-75e0-45c1-8f97-75acbd43d613" -> ANIMAL_GUIDE_RAW
            else -> ""
        }

        assertFalse(responseString.isEmpty())

        return Response.Builder()
            .code(200)
            .message(responseString)
            .request(chain.request())
            .protocol(Protocol.HTTP_1_0)
            .body(ResponseBody.create(MediaType.parse("application/json"), responseString.toByteArray()))
            .addHeader("content-type", "application/json")
            .build()
    }
}

class FakeApiService {
    companion object {
        var apiService: ApiService? = null
        fun getInstance(): ApiService {
            if (apiService == null) {
                val client = OkHttpClient.Builder().addInterceptor(FakeInterceptor()).build()
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://data.taipei/api/v1/dataset/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                apiService = retrofit.create(ApiService::class.java)
            }
            return apiService!!
        }
    }
}

class ApiGuidesRepositoryTest {
    private val testAreaGuideResults = listOf(
        AreaGuide.Data(
            _id = 1,
            e_category = "戶外區",
            e_info = "臺灣位於北半球，北迴歸線橫越南部，造成亞熱帶溫和多雨的氣候。又因高山急流、起伏多樣的地形，因而在這蕞爾小島上，形成了多樣性的生態系，孕育了多種不同生態習性的動、植物，豐富的生物物種共存共榮於此，也使臺灣博得美麗之島「福爾摩沙」的美名。臺灣動物區以臺灣原生動物與棲息環境為展示重點，佈置模擬動物原生棲地之生態環境，讓動物表現如野外般自然的生活習性，引導民眾更正確地認識本土野生動物，為園區環境教育的重要據點。藉由提供動物寬廣且具隱蔽的生態環境，讓動物避開人為過度的干擾，並展現如野外般自然的生活習性和行為。展示區以多種臺灣的保育類野生動物貫連成保育廊道，包括臺灣黑熊、穿山甲、歐亞水獺、白鼻心、石虎、山羌等。唯有認識、瞭解本土野生動物，才能愛護、保育牠們，並進而珍惜我們共同生存的這塊土地！",
            e_memo = "",
            e_name = "臺灣動物區",
            e_pic_url = "http://www.zoo.gov.tw/iTAP/05_Exhibit/01_FormosanAnimal.jpg",
            e_url = "https://youtu.be/QIUbzZ-jX_Y"
        )
    )

    private val testAreaGuideData = AreaGuide(
        result = com.chunglyric.taipeicityzootour.model.Metadata(
            count = 16,
            limit = 1,
            offset = 0,
            results = testAreaGuideResults,
            sort = ""
        )
    )

    private suspend fun fetchingAreaGuide(): AreaGuide? {
        val apiGuidesRepository = ApiGuidesRepository(FakeApiService.getInstance())
        return when (val areaGuideResponseStatus = apiGuidesRepository.getAreaGuide()) {
            is ResponseStatus.Success -> {
                areaGuideResponseStatus.data
            }
            is ResponseStatus.Error -> {
                null
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getAreaGuide() = runTest {
        val areaGuide = fetchingAreaGuide()
        assertEquals(testAreaGuideData, areaGuide)
    }

    private val testAnimalGuideCount = 272

    private val testAnimalGuideResults = listOf(
        AnimalGuide.Data(
            _id = 1,
            a_alsoknown = "貓熊、熊貓",
            a_behavior = "1、獨居的動物，除了交配季節或雌性的育幼時期，牠們都是獨自居住的。\r\n2、最活躍的時間在早晨和黃昏，但竹子所含熱量低，為減少能量的消耗，牠們每天的睡眠時間約10小時，剩餘時間則大多在覓食和進食。",
            a_crisis = "過去大貓熊棲息地裡的竹林竹種較為單一，1983年曾發生棲地內箭竹週期性大規模開花枯死而有大貓熊餓死的情形，竹林一旦開花，須再經多年後才能恢復舊觀。另外大貓熊棲息地的破碎化，使得牠們無法遷徙至其他有竹子的地方，加劇了這個原是林地自然演替的危害。也因棲地破碎化，部分種群因數量規模小，基因的窄化以及無法延續也成為另一嚴重問題。",
            a_distribution = "目前僅存於中國四川、甘肅和陜西三省境內。",
            a_feature = "1、 成熊身長約為120-180公分，體重約80-120公斤，幼熊出生時體長約10公分，體重約僅有150~200公克。\n 2、 具有強壯有力的四肢：後腳內八字撇，有利於在密林中走動時撥開竹子。\n 3、 腕骨特化成的偽拇指(不具備關節)，使得牠們能俐落地取食竹子。\n 4、 掌心覆有毛，使得大貓熊能夠在寒冷的雪地上行走。\n 5、大貓熊身體顏色主要為黑白兩色。耳朵、眼斑、鼻頭、肩背部和四肢為黑色，其餘部位為白色。相對比例較小的黑色耳朵有減少熱量散失的功能。",
            a_habitat = "海拔2600-3,000公尺的高山中，會因季節的變化而改變其居住的海拔高度，一般在乾淨的活水源和竹林發育良好的地區活動。",
            a_location = "新光特展館（大貓熊館）",
            a_name_ch = "大貓熊",
            a_name_en = "Giant Panda",
            a_pic01_alt = "大貓熊「團團」和「圓圓」",
            a_pic01_url = "http://www.zoo.gov.tw/iTAP/03_Animals/PandaHouse/Panda_Pic01.jpg",
            a_update = "########"
        )
    )

    private val testAnimalGuideData = AnimalGuide(
        result = com.chunglyric.taipeicityzootour.model.Metadata(
            count = testAnimalGuideCount,
            limit = 1,
            offset = 0,
            results = testAnimalGuideResults,
            sort = ""
        )
    )

    private suspend fun fetchAnimalGuide(): AnimalGuide? {
        val apiGuidesRepository = ApiGuidesRepository(FakeApiService.getInstance())
        return when (val animalGuideResponseStatus = apiGuidesRepository.getAnimalGuide()) {
            is ResponseStatus.Success -> {
                animalGuideResponseStatus.data
            }
            is ResponseStatus.Error -> {
                null
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getAnimalGuideCount() = runTest {
        val animalGuide = fetchAnimalGuide()
        assertEquals(testAnimalGuideCount, animalGuide?.result?.count)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getAnimalGuide() = runTest {
        val animalGuide = fetchAnimalGuide()
        assertEquals(testAnimalGuideData, animalGuide)
    }
}
