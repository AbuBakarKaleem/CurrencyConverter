package com.app.currencyconverter.data.repository

import com.app.currencyconverter.data.DataState
import com.app.currencyconverter.model.responsemodel.CurrenciesModel
import kotlinx.coroutines.flow.Flow


/**
 * Repository is an interface data layer to handle communication with any data source such as Server or local database.
 * @see [RepositoryImpl] for implementation of this class to utilize Unsplash API.
 */
interface Repository {
    /*suspend fun loadPosts(start: Int, limit: Int): Flow<DataState<List<PostModel>>>
    suspend fun getUserInfo(userId: Int): Flow<DataState<List<UserModel>>>
    suspend fun getComments(postId: Int): Flow<DataState<List<CommentModel>>>*/
    suspend fun getAllCurrencies(): Flow<DataState<CurrenciesModel>>

}
