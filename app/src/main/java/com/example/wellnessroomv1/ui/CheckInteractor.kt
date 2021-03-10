package com.example.wellnessroomv1.ui

import com.example.wellnessroomv1.ui.check_api.CheckApi
import com.example.wellnessroomv1.ui.check_api.DataBodyCheck
import com.example.wellnessroomv1.ui.check_api.JsonCheck
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://proverkacheka.com/"

class CheckInteractor {
    private var checkApi: CheckApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(CheckApi::class.java)

    fun getNewCheck(dataBodyCheck: DataBodyCheck): Single<JsonCheck> {
        return Single.fromObservable(checkApi.getCheckInfo(dataBodyCheck)).map {
            it.data.json
        }
    }
}