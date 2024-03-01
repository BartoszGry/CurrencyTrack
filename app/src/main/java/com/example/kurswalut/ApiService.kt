package com.example.kurswalut

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.net.HttpURLConnection
import java.net.URL

private val retrofit=Retrofit.Builder().baseUrl("https://api.nbp.pl/api/exchangerates/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val  currencyService = retrofit.create(ApiService::class.java)


interface ApiService {
    @GET("tables/a")
    suspend fun getCurrencies():List<CurrencyTable>
}