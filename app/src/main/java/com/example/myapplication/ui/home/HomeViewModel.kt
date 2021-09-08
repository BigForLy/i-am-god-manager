package com.example.myapplication.ui.home

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.myapplication.HeroesManager
import com.example.myapplication.R

class HomeViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    val next: String
        get() {
            return "as"
        }
}