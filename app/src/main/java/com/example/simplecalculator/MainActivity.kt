package com.example.simplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var calculateButton: Button
    private lateinit var operation: String
    private lateinit var input1: EditText
    private lateinit var input2: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var resultDisplay: TextView

    private var result: Float? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calculateButton = findViewById(R.id.calculate)
        input1 = findViewById(R.id.input1)
        input2 = findViewById(R.id.input2)
        radioGroup = findViewById(R.id.group)
        resultDisplay = findViewById(R.id.result)

        calculateButton.setOnClickListener { view: View ->
            val firstNum = input1.text.toString()
            val secondNum = input2.text.toString()

            if (firstNum.isNotEmpty() && secondNum.isNotEmpty()) {
                val firstNumFloat = firstNum.toFloatOrNull()
                val secondNumFloat = secondNum.toFloatOrNull()

                if (firstNumFloat != null && secondNumFloat != null) {
                    val selectedRadioButtonId = radioGroup.checkedRadioButtonId
                    if (selectedRadioButtonId != -1) {
                        val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId)
                        operation = selectedRadioButton.text.toString()

                        when (operation) {
                            "Addition" -> result = firstNumFloat + secondNumFloat
                            "Subtraction" -> result = firstNumFloat - secondNumFloat
                            "Multiplication" -> result = firstNumFloat * secondNumFloat
                            "Division" -> {
                                if (secondNumFloat != 0f) {
                                    result = firstNumFloat / secondNumFloat
                                } else {
                                    resultDisplay.text = ""
                                    Snackbar.make(
                                        view,
                                        R.string.zeroAlarm,
                                        Snackbar.LENGTH_LONG
                                    ).show()
                                    return@setOnClickListener
                                }
                            }
                            "Modulus" -> {
                                if (secondNumFloat != 0f) {
                                    result = firstNumFloat % secondNumFloat
                                } else {
                                    resultDisplay.text = ""
                                    Snackbar.make(
                                        view,
                                        R.string.zeroAlarm,
                                        Snackbar.LENGTH_LONG
                                    ).show()
                                    return@setOnClickListener
                                }
                            }
                        }

                        resultDisplay.text = result?.toString() ?: ""
                    } else {
                        resultDisplay.text = ""
                        Snackbar.make(
                            view,
                            R.string.emptyOperation,
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                } else {
                    resultDisplay.text = ""
                    Snackbar.make(
                        view,
                        R.string.emptyNum,
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            } else {
                resultDisplay.text = ""
                Snackbar.make(
                    view,
                    R.string.emptyNum,
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }
}