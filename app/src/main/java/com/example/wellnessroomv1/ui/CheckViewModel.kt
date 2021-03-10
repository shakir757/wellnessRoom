package com.example.wellnessroomv1.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wellnessroomv1.ui.check_api.DataBodyCheck
import com.example.wellnessroomv1.ui.check_api.JsonCheck
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CheckViewModel : ViewModel() {
    private val checkInteractor = CheckInteractor()
    val checkLiveData = MutableLiveData<JsonCheck?>(null)

    fun getNewCheck(dataBodyCheck: DataBodyCheck){
        checkInteractor.getNewCheck(dataBodyCheck)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    checkLiveData.postValue(it)
                },
                {
                    it.printStackTrace()
                }
            )
    }
}