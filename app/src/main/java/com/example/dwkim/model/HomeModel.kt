package com.example.dwkim.model

import com.example.dwkim.repository.result.GetHomeResult

class HomeModel(homeResult: GetHomeResult) {
    val popularCards: List<CardModel>?
    val popularUsers: List<UserModel>?

    init {
        popularCards = homeResult.popularCards?.map {  card ->
            CardModel(card)
        }

        popularUsers = homeResult.popularUsers?.map { user ->
            UserModel(user)
        }
    }
}