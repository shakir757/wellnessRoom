package com.example.wellnessroomv1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.CaptureActivity

class ScannerActivity : AppCompatActivity() {

    var checkDictionary: List<String> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner)

        scanQRCode()
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
                Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
                Log.d("Check", "Check result: " + result.contents)

                val rawData = result.contents
                val scanInteractor = ScanCheckInteractor()
                val dictionary = scanInteractor.makeDataDictionary(rawData)

                Log.d("Check", "Dictionary: $dictionary")
                checkDictionary = dictionary

                /*val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)*/
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}