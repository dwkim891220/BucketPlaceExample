package com.example.dwkim.model

import com.example.dwkim.repository.model.Card

class CardModel(card: Card) {
    val userId: Int?
    val imgUrl: String?
    val description: String?
    val id: Int?

    init {
        this.id = card.id
        this.userId = card.user_id
        this.imgUrl = card.img_url
        this.description = card.description
    }
}