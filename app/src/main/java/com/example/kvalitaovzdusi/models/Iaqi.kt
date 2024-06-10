package com.example.kvalitaovzdusi.models

import com.google.gson.annotations.SerializedName


data class Iaqi (

  @SerializedName("h"    ) var h    : H?    = H(),
  @SerializedName("no2"  ) var no2  : No2?  = No2(),
  @SerializedName("o3"   ) var o3   : O3?   = O3(),
  @SerializedName("p"    ) var p    : P?    = P(),
  @SerializedName("pm10" ) var pm10 : Pm10? = Pm10(),
  @SerializedName("pm25" ) var pm25 : Pm25? = Pm25(),
  @SerializedName("r"    ) var r    : R?    = R(),
  @SerializedName("so2"  ) var so2  : So2?  = So2(),
  @SerializedName("t"    ) var t    : T?    = T(),
  @SerializedName("w"    ) var w    : W?    = W()

)