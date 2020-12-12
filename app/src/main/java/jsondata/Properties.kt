package jsondata

import com.google.gson.annotations.SerializedName

data class Propertiess
     (

    @SerializedName("mag") var mag: Double,
    @SerializedName("place") var place: String,
    @SerializedName("time") var time: Long,
   @SerializedName("updated") var updated : Int)