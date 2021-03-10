package com.example.wellnessroomv1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.wellnessroomv1.ui.CheckViewModel
import com.example.wellnessroomv1.ui.check_api.DataBodyCheck
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.CaptureActivity

class ScannerActivity : AppCompatActivity() {

    private var checkDictionary: List<String> = listOf()
    private val checkViewModel = CheckViewModel()
    private val scanInteractor = ScanCheckInteractor()
    private val TOKEN = "1393.UgJKe7UUCJCf7ARal"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner)

        scanQRCode()
        observeCheckLiveData()
    }

    private fun scanQRCode() {
        val integrator = IntentIntegrator(this).apply {
            captureActivity = CaptureActivity::class.java
            setOrientationLocked(false)
            setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
            setPrompt("Наведите камеру на QR-code")
        }
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
            } else {
                Log.d("Check", "Check result: " + result.contents)

                val rawData = result.contents
                checkDictionary = scanInteractor.makeDataDictionary(rawData)

                Log.d("Check", "Dictionary: $checkDictionary")
                Toast.makeText(this, "Check: $checkDictionary", Toast.LENGTH_SHORT).show()
                checkViewModel.getNewCheck(
                    DataBodyCheck(
                        "9289440300637432", "17173", "4107152669", "1", "5400", "20201213T1945", "0", TOKEN
                    )
                )

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun observeCheckLiveData(){
        /*checkViewModel.getNewCheck(DataBodyCheck(
            checkDictionary[2],
            checkDictionary[3],
            checkDictionary[4],
            checkDictionary[5].toInt().toString(),
            checkDictionary[1].toDouble().toString(),
            scanInteractor.makeDocDateTime(),
            "0",
            TOKEN
        ))*/

        checkViewModel.checkLiveData.observe(this, Observer {
            it?.let {
                Log.d("Check", it.code.toString())
                Toast.makeText(this, "parse! $it", Toast.LENGTH_LONG).show()
            }
        })
    }
}