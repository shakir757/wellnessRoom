package com.example.wellnessroomv1

class ScanCheckInteractor {

    // t=20201213T194500 & s=54.00 & fn=9289440300637432 & i=17173 & fp=4107152669 & n=1

    fun makeDataDictionary(rawData: String): List<String> {
        val components = rawData.split("&")
        val dictionary: MutableList<String> = arrayListOf()
        for (i in components){
            val keyValue = i.split("=")
            dictionary.add(keyValue[1])
        }

        return dictionary
    }

    fun makeDocDateTime(){

    }
}