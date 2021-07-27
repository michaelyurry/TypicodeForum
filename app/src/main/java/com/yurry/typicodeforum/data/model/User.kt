package com.yurry.typicodeforum.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id : Int = 0,
    @SerializedName("name")
    val name : String = "",
    @SerializedName("username")
    val username : String = "",
    @SerializedName("email")
    val email : String = "",
    @SerializedName("address")
    val address : Address,
    @SerializedName("phone")
    val phone : String = "",
    @SerializedName("website")
    val website : String = "",
    @SerializedName("company")
    val company : Company
)

data class Address(
    @SerializedName("street")
    val id : String = "",
    @SerializedName("suite")
    val userId : String = "",
    @SerializedName("city")
    val city : String = "",
    @SerializedName("zipcode")
    val title : String = "",
    @SerializedName("geo")
    val geo : Geo
)

data class Geo(
    @SerializedName("lat")
    val lat : String = "",
    @SerializedName("lang")
    val lang : String = ""
)

data class Company(
    @SerializedName("name")
    val name : String = "",
    @SerializedName("catchPhrase")
    val catchPhrase : String = "",
    @SerializedName("bs")
    val bs : String = ""
)