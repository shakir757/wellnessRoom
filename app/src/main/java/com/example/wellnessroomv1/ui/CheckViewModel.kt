package com.example.wellnessroomv1.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wellnessroomv1.ui.check_api.DataBodyCheck
import com.example.wellnessroomv1.ui.check_api.JsonCheck
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CheckViewModel : ViewModel() {

    private val checkInteractor = CheckInteractor()

    fun fetchNewCheck(
        dataBodyCheck: DataBodyCheck,
        successCallback: (JsonCheck) -> Unit,
        errorCallback: (Throwable) -> Unit
    ) {
        checkInteractor.getNewCheck(dataBodyCheck)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    successCallback(it)
                },
                {
                    errorCallback(it)
                }
            )
    }
}