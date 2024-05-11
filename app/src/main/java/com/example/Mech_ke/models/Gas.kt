package com.example.Mech_ke.models

class Gas {
    var name:String = ""
    var county:String = ""
    var town:String = ""
    var imageUrl:String = ""
    var id:String = ""

    constructor(name: String, county: String,town: String, imageUrl: String, id: String) {
        this.name = name
        this.county = county
        this.town = town
        this.imageUrl = imageUrl
        this.id = id
    }

    constructor()

}