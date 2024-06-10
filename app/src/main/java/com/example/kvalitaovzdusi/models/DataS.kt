package com.example.kvalitaovzdusi.models

import com.google.gson.annotations.SerializedName


data class DataS (

  @SerializedName("uid"     ) var uid     : Int?     = null,
  @SerializedName("aqi"     ) var aqi     : String?  = null,
  @SerializedName("time"    ) var time    : TimeS?    = TimeS(),
  @SerializedName("station" ) var station : Station? = Station()

)