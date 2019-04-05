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

    override fun join(
        nickname: String,
        introduction: String,
        pwd: String
    ): Completable = api.join(nickname, introduction, pwd)

    override fun signIn(
        nickname: String,
        pwd: String
    ): Completable = api.signIn(nickname, pwd)

    override fun getUser(id: Int): Single<UserModel> =
        api.getUser(id).map { response ->
            if(response.ok){
            UserModel(response.user)
            }else{
                parse(response)
                null
            }
        }

    override fun getHome(): Single<HomeModel> =
        api.getHome().map { response ->
            if(response.ok){
                HomeModel(response)
            }else{
                parse(response)
                null
            }
        }

    override fun getCard(id: Int): Single<CardDetailModel> =
        api.getCard(id).map { response ->
            if (response.ok) {
                CardDetailModel(response)
            } else {
                parse(response)
                null
            }
        }

    override fun getCards(page: Int, per: Int): Single<List<CardModel>> =
        api.getCards(page, per).map {response ->
            if (response.ok) {
                response.cards.map{ card ->
                    CardModel(card)
                }
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