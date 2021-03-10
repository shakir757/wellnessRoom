package com.example.wellnessroomv1.ui.check_api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BaseCheck(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val data: DataCheck
) : Parcelable