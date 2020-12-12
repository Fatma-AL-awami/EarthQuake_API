package api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import jsondata.Features
import jsondata.ResponseEarth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EarthFetcher {

    private val earthApi: EarthApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://earthquake.usgs.gov/fdsnws/event/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        earthApi = retrofit.create(EarthApi::class.java)
    }

    fun fitch(): LiveData<List<Features>> {
        val responseLiveData: MutableLiveData<List<Features>> = MutableLiveData()
        val quakeRequest: Call<ResponseEarth> = earthApi.fitchdata()
        quakeRequest.enqueue(object : Callback<ResponseEarth> {
            override fun onFailure(call: Call<ResponseEarth>, t: Throwable) {
                Log.e("Failure", "Failed to fetch ", t)
            }

            override fun onResponse(
                call: Call<ResponseEarth>,
                response: Response<ResponseEarth>
            ) {
                Log.e("sucess to fetch", response.code().toString())
                val quakerResponse:ResponseEarth? = response.body()
                var featuresList: List<Features> = quakerResponse?.features
                    ?: mutableListOf()

                responseLiveData.value = featuresList
            }
        })
        return responseLiveData
    }


}