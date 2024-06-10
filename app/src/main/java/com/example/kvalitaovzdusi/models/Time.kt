package com.example.kvalitaovzdusi.models

import com.google.gson.annotations.SerializedName


data class Time (

  @SerializedName("s"  ) var s  : String? = null,
  @SerializedName("tz" ) var tz : String? = null,
  @SerializedName("v"  ) var v  : Int?    = null

)