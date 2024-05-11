package com.example.Mech_ke.models

class Autopart {
    var name:String = ""
    var price:String = ""
    var condition:String = ""
    var location:String = ""
    var description:String = ""
    var imageUrl:String = ""
    var id:String = ""

    constructor(name: String, price: String, condition: String,location:String,description:String, imageUrl: String, id: String) {
        this.name = name
        this.price = price
        this.condition = condition
        this.location = location
        this.description = description
        this.imageUrl = imageUrl
        this.id = id
    }

    constructor()
}