package com.example.barvolumek

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var edtWidth:EditText
    private lateinit var edtHeight:EditText
    private lateinit var edtLength:EditText
    private lateinit var btnCalculate:Button
    private lateinit var tvResult:TextView

    companion object{
        private const val STATE_RESULT = "state_result"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtWidth = findViewById(R.id.edt_width)
        edtHeight = findViewById(R.id.edt_height)
        edtLength = findViewById(R.id.edt_length)
        btnCalculate = findViewById(R.id.btn_calculate)
        tvResult = findViewById(R.id.tv_result)

        btnCalculate.setOnClickListener(this)

        if(savedInstanceState!=null){
            val result = savedInstanceState.getString(STATE_RESULT) as String
            tvResult.text = result
        }
    }

    override fun onClick(v: View) {
        if(v.id == R.id.btn_calculate){
            val inputLength = edtLength.text.toString().trim()
            val inputWidth = edtWidth.text.toString().trim()
            val inputHeight = edtHeight.text.toString().trim()

            var isEmptyFields = false

            //jika input kosong maka akan muncul pernyataan error "Field Ini Tidak Boleh Kosong".
            if (inputLength.isEmpty()){
                isEmptyFields = true
                edtLength.error = "Field Ini Tidak Boleh Kosong"
            }

            if (inputWidth.isEmpty()){
                isEmptyFields = true
                edtWidth.error = "Field Ini Tidak Boleh Kosong"
            }

            if (inputHeight.isEmpty()){
                isEmptyFields = true
                edtHeight.error = "Field Ini Tidak Boleh Kosong"
            }

            var length : Double? = toDouble(inputLength)
            var width : Double? = toDouble(inputWidth)
            var height : Double? = toDouble(inputHeight)

            //jika input tidak kosong maka hasil akan ditampilkan.
            if(!isEmptyFields){
                val volume = length!! * width!! * height!!
                tvResult.text = volume.toString()
            }
        }
    }
    private fun toDouble(str: String): Double? {
        return try {
            str.toDouble()
        } catch (e: NumberFormatException) {
            null
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT,tvResult.text.toString())
    }

}