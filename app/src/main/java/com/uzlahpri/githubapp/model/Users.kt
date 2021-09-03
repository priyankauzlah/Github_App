package com.uzlahpri.githubapp.model

//base datanya, kalo datanya kosong mengikuti ini
data class Users(
    var username : String? = null,
    var name : String? = " ",
    var location : String? = " ",
    var followers : Int? = 0,
    var following : Int? = 0,
    var avatar : String? = null
)