package com.example.wellnessroomv1.ui.check_api

import io.reactivex.Observable
import retrofit2.http.*

interface CheckApi {

    @FormUrlEncoded
    @POST ("api/v1/check/get")
    fun getCheckInfo(
        @Field("fn") fn: String,
        @Field("fd") fd: String,
        @Field("fp") fp: String,
        @Field("n") n: String,
        @Field("s") s: String,
        @Field("t") t: String,
        @Field("qr") qr: String,
        @Field("token") token: String,
    ) : Observable<BaseCheck>
}