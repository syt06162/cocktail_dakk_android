package com.umcapplunching.cocktail_dakk.ui.main.mainrecommand.MainrecService

import com.google.gson.annotations.SerializedName

data class MainrecommandResponse(
    @SerializedName("code")val code: Int,
    @SerializedName("isSuccess")val isSuccess: Boolean,
    @SerializedName("message")val message: String,
    @SerializedName("result")val mainrecList: Mainrec
)

data class Mainrec(
    @SerializedName("nickname")val nickname: String,
    @SerializedName("userRecommendationLists")val userRecommendationLists: List<UserRecommendationLists>
)

data class UserRecommendationLists(
    @SerializedName("cocktailInfoId")val cocktailInfoId: Int,
    @SerializedName("koreanName")val koreanName: String,
    @SerializedName("cocktailImageURL")val cocktailImageURL: String,

)
