package com.example.ui_screens

import retrofit2.Response
import retrofit2.http.GET

interface APIInterface {
    @GET("posts")
   suspend fun getData():Response<DataModel>
}