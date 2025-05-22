package com.example.dice_roll

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val rollBtn: Button = findViewById(R.id.button)
        val diceImgFirst: ImageView = findViewById(R.id.dice1)
        val diceImgSecond: ImageView = findViewById(R.id.dice2)

        diceImgFirst.setImageResource(R.drawable.empty_dice)
        diceImgSecond.setImageResource(R.drawable.empty_dice)

        rollBtn.setOnClickListener {
            rollDice()
        }
    }

    class Dice(private val jumlahSisi: Int) {
        fun roll(): Int {
            return (1..jumlahSisi).random()
        }
    }

    private fun rollDice() {
        val dice1 = Dice(6)
        val dice2 = Dice(6)
        val diceRoll1 = dice1.roll()
        val diceRoll2 = dice2.roll()

        val diceImgFirst: ImageView = findViewById(R.id.dice1)
        val diceImgSecond: ImageView = findViewById(R.id.dice2)

        val drawableResourceFirst = when (diceRoll1) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        val drawableResourceSecond = when (diceRoll2) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        diceImgFirst.setImageResource(drawableResourceFirst)
        diceImgSecond.setImageResource(drawableResourceSecond)

        diceImgFirst.contentDescription = diceRoll1.toString()
        diceImgSecond.contentDescription = diceRoll2.toString()

        if (diceRoll1 != diceRoll2) {
            Toast.makeText(this, "Anda belum beruntung", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Selamat anda dapat dadu double", Toast.LENGTH_SHORT).show()
        }
    }
}