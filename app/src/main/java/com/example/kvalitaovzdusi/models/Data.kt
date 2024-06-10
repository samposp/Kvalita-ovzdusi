package com.example.kvalitaovzdusi.models

import com.google.gson.annotations.SerializedName


data class Data (

  @SerializedName("aqi"          ) var aqi          : String?                 = null,
  @SerializedName("idx"          ) var idx          : Int?                    = null,
  @SerializedName("attributions" ) var attributions : ArrayList<Attributions> = arrayListOf(),
  @SerializedName("city"         ) var city         : City?                   = City(),
  @SerializedName("dominentpol"  ) var dominentpol  : String?                 = null,
  @SerializedName("iaqi"         ) var iaqi         : Iaqi?                   = Iaqi(),
  @SerializedName("time"         ) var time         : Time?                   = Time()

)