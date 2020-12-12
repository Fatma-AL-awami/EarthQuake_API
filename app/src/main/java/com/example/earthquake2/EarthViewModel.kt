package com.example.earthquake2

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import api.EarthFetcher
import jsondata.Features

class EarthViewModel: ViewModel() {
    val quakesLiveData: LiveData<List<Features>> = EarthFetcher().fitch()

}
