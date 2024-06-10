package com.example.kvalitaovzdusi.models

import com.google.gson.annotations.SerializedName


data class Station (

  @SerializedName("name"    ) var name    : String?           = null,
  @SerializedName("geo"     ) var geo     : ArrayList<Double> = arrayListOf(),
  @SerializedName("url"     ) var url     : String?           = null,
  @SerializedName("country" ) var country : String?           = null

)