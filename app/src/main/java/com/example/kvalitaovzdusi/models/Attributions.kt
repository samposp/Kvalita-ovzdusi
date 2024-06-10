package com.example.kvalitaovzdusi.models

import com.google.gson.annotations.SerializedName


data class Attributions (

  @SerializedName("url"  ) var url  : String? = null,
  @SerializedName("name" ) var name : String? = null

)