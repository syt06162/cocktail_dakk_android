package com.umcapplunching.cocktail_dakk.ui.menu_detail.detailService

import retrofit2.Call
import retrofit2.http.*

interface DetailRetrofitInterface {

    @GET("/cocktaildakk/v1/cocktails/details")
    fun detail(@Header("auth")accesstoken : String,
               @Query("id",encoded = true) id : Int): Call<detailResponse>

    @POST("/cocktaildakk/v1/cocktails/rating")
    fun rating(@Header("auth")accesstoken : String,
               @Body detailrequest : DetailRequest): Call<DetailRatingResponse>

}
