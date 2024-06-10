package com.example.kvalitaovzdusi.models

import com.google.gson.annotations.SerializedName


data class TimeS (

  @SerializedName("tz"    ) var tz    : String? = null,
  @SerializedName("stime" ) var stime : String? = null,
  @SerializedName("vtime" ) var vtime : Int?    = null

)