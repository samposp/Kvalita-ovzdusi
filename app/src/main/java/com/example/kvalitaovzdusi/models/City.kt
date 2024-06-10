package com.example.kvalitaovzdusi.models

import com.google.gson.annotations.SerializedName


data class City (

  @SerializedName("name" ) var name : String? = null,
  @SerializedName("url"  ) var url  : String? = null

)