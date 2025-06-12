package com.example.tip_time

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.example.tip_time.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalculate.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        val stringInTextField = binding.etCostOfService.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if (cost == null || cost == 0.0) {
            binding.etCostOfService.error = "Please enter a valid amount"
            Toast.makeText(this, "Invalid input. Please enter a number.", Toast.LENGTH_SHORT).show()
            binding.tvTipAmount.text = ""
            return
        }

        val tipPercentage = when (binding.rgTipOptions.checkedRadioButtonId) {
            R.id.rb_amazing -> 0.20
            R.id.rb_good -> 0.18
            else -> 0.15
        }

        var tip = tipPercentage * cost
        if (binding.switchRoundUp.isChecked) {
            tip = ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tvTipAmount.text = getString(R.string.tip_amount, formattedTip)
    }
}