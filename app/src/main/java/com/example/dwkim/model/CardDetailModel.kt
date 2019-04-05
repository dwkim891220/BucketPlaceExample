package com.example.dwkim.model

import com.example.dwkim.repository.model.Card
import com.example.dwkim.repository.model.User
import com.example.dwkim.repository.result.GetCardResult

class CardDetailModel(result: GetCardResult){
    var card: Card? = null
    var user: User? = null
    var recommendCards: List<CardModel>? = null

    init {
        this.card = result.card
        this.user = result.user
        this.recommendCards = result.recommendCards.map { card ->
            CardModel(card)
        }
    }
}