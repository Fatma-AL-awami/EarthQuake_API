package jsondata

import com.google.gson.annotations.SerializedName

data class Features (

    @SerializedName("properties") var properties: Properties,
    @SerializedName("geometry") var geometry: Geometry,
    @SerializedName("id") var id: String
)

data class Properties
    (

    @SerializedName("mag") var mag: Double,
    @SerializedName("place") var place: String,
    @SerializedName("time") var time: Long,
    @SerializedName("updated") var updated : Int)
