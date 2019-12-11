package com.oneago.paradoxofmontyhall

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import java.lang.NumberFormatException
import java.util.ArrayList

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var x1: ImageView
    private lateinit var x2: ImageView
    private lateinit var x3: ImageView
    private lateinit var statistics: ImageView
    private lateinit var restart: ImageView

    private val GOAT:Int = 0
    private val CAR:Int = 1

    private var nowData: MutableList<Int> = ArrayList()
    private var carDoor: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        x1 = findViewById(R.id.X1)
        x2 = findViewById(R.id.X2)
        x3 = findViewById(R.id.X3)
        statistics = findViewById(R.id.statistics)
        restart = findViewById(R.id.restart);

        x1.setOnClickListener(this)
        x2.setOnClickListener(this)
        x3.setOnClickListener(this)
        statistics.setOnClickListener(this)
        restart.setOnClickListener(this)

        doorAsign()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.X1 -> {
                x1.setImageResource(imageResource(nowData[0]))
                openDoor()
            }
            R.id.X2 -> {
                x2.setImageResource(imageResource(nowData[1]))
                openDoor()
            }
            R.id.X3 -> {
                x3.setImageResource(imageResource(nowData[2]))
                openDoor()
            }
            R.id.statistics -> {
                startActivity(Intent(this, Stadistics::class.java))
            }
            R.id.restart -> {
                doorAsign()
                x1.setImageResource(R.drawable.ic_puerta)
                x2.setImageResource(R.drawable.ic_puerta)
                x3.setImageResource(R.drawable.ic_puerta)
            }
        }
    }

    @Throws(NumberFormatException::class)
    fun imageResource(number: Int): Int {
        if (number == GOAT) {
            return R.drawable.ic_goat
        } else if (number == CAR) {
            return R.drawable.ic_car
        }
        throw NumberFormatException("""Out of range, only binary numbers. ${number}no is valid number""")
    }

    private fun doorAsign() {
        for (i in 0..2) {
            nowData.add(GOAT)
        }
        carDoor = (0..2).random()
        nowData[carDoor] = CAR
    }

    private fun openDoor() {
        val random = Math.random()
        var availableDoors = nowData
        availableDoors.remove(carDoor)
        if (random >= .6) {
            print("puerta 1")
        } else {
            print("puerta 2")
        }
    }
}
