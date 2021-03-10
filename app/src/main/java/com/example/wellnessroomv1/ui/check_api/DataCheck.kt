package com.example.wellnessroomv1.ui.check_api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class DataCheck(
    @SerializedName("data")
    val json: JsonCheck,
    @SerializedName("html")
    val html: String
) : Parcelable