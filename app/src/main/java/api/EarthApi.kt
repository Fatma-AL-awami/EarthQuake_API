package api


import jsondata.ResponseEarth
import retrofit2.Call
import retrofit2.http.GET

interface EarthApi {
    @GET("query?format=geojson&limit=10")

    fun fitchdata(): Call<ResponseEarth>
}