package com.grifalion.starwars.data.network

import com.grifalion.starwars.data.network.dto.people.PeopleDto
import com.grifalion.starwars.data.network.dto.starship.StarshipDto
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("people/")
    suspend fun getCharacterByName (
        @Query("search") search: String): Response<PeopleDto>

    @GET("starships/")
    suspend fun getStarshipByName (
        @Query("search") search: String): Response<StarshipDto>

    companion object{
        private const val BASE_URL = "https://swapi.dev/api/"
        var peopleRetrofit: ApiService? = null

        fun getInstance(): ApiService{
            if(peopleRetrofit == null) {
                val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                    .build()
                val retrofit = Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                peopleRetrofit = retrofit.create(ApiService::class.java)
            }
            return peopleRetrofit!!
        }
    }
}
