package jsondata

import com.google.gson.annotations.SerializedName

class ResponseEarth {
       @SerializedName("features")
    lateinit var features: List<Features>
}