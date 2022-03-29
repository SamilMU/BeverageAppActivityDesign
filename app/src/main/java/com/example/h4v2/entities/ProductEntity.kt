package com.example.h4v2.entities

import java.io.Serializable

class ProductEntity(
    var name : String,
    var brand : String,
    var price : Double,
    var category : String,
    var summary : String,
    val pic_name : String
) : Serializable {
}