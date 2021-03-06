package github.com.rhacco.dota2androidapp.sources.remote

import github.com.rhacco.dota2androidapp.api.HeroDataModelResponse
import github.com.rhacco.dota2androidapp.utilities.URLStrings
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Dota2OfficialAPIService {
    @GET("IEconDOTA2_570/GetHeroes/v1")
    fun fetchLocalizedHeroData(@Query("key") steamApiKey: String,
                               @Query("language") language: String): Observable<HeroDataModelResponse.Result>

    companion object {
        fun create(): Dota2OfficialAPIService {
            val retrofit = Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(URLStrings.STEAM_API)
                    .build()
            return retrofit.create(Dota2OfficialAPIService::class.java)
        }
    }
}

val sDota2OfficialAPIService by lazy { Dota2OfficialAPIService.create() }
