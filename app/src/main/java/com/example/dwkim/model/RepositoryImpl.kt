package com.example.dwkim.model

import android.content.Context
import com.example.dwkim.repository.IRepository
import com.example.dwkim.repository.NetworkRepository
import com.example.dwkim.repository.model.Card
import com.example.dwkim.repository.model.User
import com.example.dwkim.repository.result.BaseResult
import com.example.dwkim.repository.result.GetCardResult
import com.example.dwkim.util.showDialog
import io.reactivex.Completable
import io.reactivex.Single

class RepositoryImpl(
    private val context: Context,
    private val api: NetworkRepository
): IRepository {
    override fun join(user: User): Completable? = api.join(user)
    override fun signIn(user: User): Completable? = api.signIn(user)

    override fun getUser(id: Int): Single<User>? =
        api.getUser(id).map { response ->
            if(response.ok){
            response.user
            }else{
                parse(response)
                null
            }
        }

    override fun getHome(): Single<HomeModel>? =
        api.getHome().map { response ->
            if(response.ok){
                HomeModel(response)
            }else{
                parse(response)
                null
            }
        }

    override fun getCard(id: Int): Single<GetCardResult>? =
        api.getCard(id).map { response ->
            if (response.ok) {
                response
            } else {
                parse(response)
                null
            }
        }

    override fun getCards(page: Int, per: Int): Single<List<Card>>? =
        api.getCards(page, per).map {response ->
            if (response.ok) {
                response.cards
            } else {
                parse(response)
                null
            }
        }

    private fun parse(result: BaseResult): Boolean{
        result.run {
            if(ok){
                return true
            }else{
                context.showDialog(msg)
            }
        }

        return false
    }
}