package com.example.wellnessroomv1.ui.check_api

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface CheckApi {
    @POST ("api/v1/check/get")
    fun getCheckInfo(@Body dataBodyCheck: DataBodyCheck): Observable<BaseCheck>
}