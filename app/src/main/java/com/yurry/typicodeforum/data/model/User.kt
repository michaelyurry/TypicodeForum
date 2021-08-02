package com.yurry.typicodeforum.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
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

@Keep
data class Address(
    @SerializedName("street")
    val street : String = "",
    @SerializedName("suite")
    val suite : String = "",
    @SerializedName("city")
    val city : String = "",
    @SerializedName("zipcode")
    val zipcode : String = "",
    @SerializedName("geo")
    val geo : Geo
)

@Keep
data class Geo(
    @SerializedName("lat")
    val lat : String = "",
    @SerializedName("lang")
    val lang : String = ""
)

@Keep
data class Company(
    @SerializedName("name")
    val name : String = "",
    @SerializedName("catchPhrase")
    val catchPhrase : String = "",
    @SerializedName("bs")
    val bs : String = ""
)
