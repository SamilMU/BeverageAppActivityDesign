package com.example.h4v2.sharedObj

import androidx.lifecycle.MutableLiveData

object SharedObj {
    var livePriceData : MutableLiveData<Double> = MutableLiveData(0.0)
    var loggedIn : Boolean = false
}