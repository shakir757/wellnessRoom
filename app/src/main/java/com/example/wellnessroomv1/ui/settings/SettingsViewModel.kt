package com.example.wellnessroomv1.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Версия приложения 0.0.1"
    }
    val text: LiveData<String> = _text
}