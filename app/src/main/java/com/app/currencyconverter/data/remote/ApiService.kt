package com.app.currencyconverter.data.remote
import com.app.currencyconverter.model.responsemodel.CurrenciesModel
import com.app.currencyconverter.utils.AppConstants
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("list")
    suspend fun getAllCurrencies(
        @Query("access_key") access_key: String = AppConstants.Network.API_ACCESS_KEY,
    ): ApiResponse<CurrenciesModel>

  /*  @GET("users")
    suspend fun getUserInfo(
        @Query("id") page: Int = 0,
    ): ApiResponse<List<UserModel>>

    @GET("comments")
    suspend fun getComments(
        @Query("postId") page: Int = 0,
    ): ApiResponse<List<CommentModel>>*/

    companion object {
        const val BASE_API_URL = "http://api.currencylayer.com/"
    }
}
