package com.example.Mech_ke.models

class CarWash {

    var name:String = ""
    var contact:String = ""
    var county:String = ""
    var town:String = ""
    var imageUrl:String = ""
    var id:String = ""

    constructor(name: String, contact: String, county: String,town: String, imageUrl: String, id: String) {
        this.name = name
        this.contact = contact
        this.county = county
        this.town = town
        this.imageUrl = imageUrl
        this.id = id
    }

    constructor()
}