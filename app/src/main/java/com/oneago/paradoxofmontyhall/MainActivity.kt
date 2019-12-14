package com.oneago.paradoxofmontyhall

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var x1: ImageView
    private lateinit var x2: ImageView
    private lateinit var x3: ImageView
    private lateinit var statistics: ImageView
    private lateinit var restart: ImageView

    private val GOAT: Int = 0
    private val CAR: Int = 1

    private lateinit var nowData: MutableList<Int>
    private var selectedDoor by Delegates.notNull<Int>()
    private var toOpenDoor: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        x1 = findViewById(R.id.X1)
        x2 = findViewById(R.id.X2)
        x3 = findViewById(R.id.X3)
        statistics = findViewById(R.id.statistics)
        restart = findViewById(R.id.restart)

        x1.setOnClickListener(this)
        x2.setOnClickListener(this)
        x3.setOnClickListener(this)
        statistics.setOnClickListener(this)
        restart.setOnClickListener(this)

        doorAssign()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.X1 -> {
                selectedDoor = 0
                if (!toOpenDoor) {
                    x1.setBackgroundColor(resources.getColor(R.color.selectedColor))
                    openGoatDoor()
                    toOpenDoor = true
                } else {
                    openDoor(selectedDoor)
                }
            }
            R.id.X2 -> {
                selectedDoor = 1
                if (!toOpenDoor) {
                    x2.setBackgroundColor(resources.getColor(R.color.selectedColor))
                    openGoatDoor()
                    toOpenDoor = true
                } else {
                    openDoor(selectedDoor)
                }
            }
            R.id.X3 -> {
                selectedDoor = 2
                if (!toOpenDoor) {
                    x3.setBackgroundColor(resources.getColor(R.color.selectedColor))
                    openGoatDoor()
                    toOpenDoor = true
                } else {
                    openDoor(selectedDoor)
                }
            }
            R.id.statistics -> {
                startActivity(Intent(this, Stadistics::class.java))
            }
            R.id.restart -> {
                x1.setBackgroundColor(0x00000000)
                x2.setBackgroundColor(0x00000000)
                x3.setBackgroundColor(0x00000000)
                doorAssign()
                x1.setImageResource(R.drawable.ic_puerta)
                x2.setImageResource(R.drawable.ic_puerta)
                x3.setImageResource(R.drawable.ic_puerta)
                toOpenDoor = false
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

    private fun doorAssign() {
        nowData = ArrayList()
        for (i in 0..2) {
            nowData.add(GOAT)
        }
        val carDoor = (0..2).random()
        Log.d("Car door", carDoor.toString())
        nowData[carDoor] = CAR
        Log.d("Door", nowData.toString())
    }

    private fun openGoatDoor() {
        val random = Math.random()
        val goatDoors: MutableList<Int> = ArrayList()
        var i = 0

        nowData.forEach {
            if (it == GOAT) {
                goatDoors.add(i)
            }
            i++
        }

        if (random >= .5) {
            openDoor(
                if (goatDoors[0] != selectedDoor) {
                    goatDoors[0]
                } else {
                    goatDoors[1]
                }
            )
        } else {
            openDoor(
                if (goatDoors[1] != selectedDoor) {
                    goatDoors[1]
                } else {
                    goatDoors[0]
                }
            )
        }
    }

    private fun openDoor(door: Int) {
        when (door) {
            0 -> {
                x1.setBackgroundColor(0x00000000)
                x1.setImageResource(imageResource(nowData[0]))
            }
            1 -> {
                x2.setBackgroundColor(0x00000000)
                x2.setImageResource(imageResource(nowData[1]))
            }
            2 -> {
                x3.setBackgroundColor(0x00000000)
                x3.setImageResource(imageResource(nowData[2]))
            }
        }
    }
}
