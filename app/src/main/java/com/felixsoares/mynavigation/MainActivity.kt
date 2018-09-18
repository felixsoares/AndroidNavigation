package com.felixsoares.mynavigation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.felixsoares.mybottomnavy.BottomNavy
import com.felixsoares.mybottomnavy.ItemNavy
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation
                .addItem(ItemNavy(android.R.drawable.ic_menu_report_image, android.R.drawable.ic_menu_report_image, hasBadge = true))
                .addItem(ItemNavy(android.R.drawable.ic_btn_speak_now, android.R.drawable.ic_btn_speak_now))
                .addItem(ItemNavy(android.R.drawable.ic_delete, android.R.drawable.ic_delete, hasAlert = true))
                .addItem(ItemNavy(android.R.drawable.ic_input_add, android.R.drawable.ic_input_add))
                .build(object : BottomNavy.OnTabSelectedListener {
                    override fun onTabSelected(position: Int) {
                        Log.i("applog", "${position}º item clicked")
                    }
                })

        btn1.setOnClickListener {
            navigation.showAlert()
        }

        btn2.setOnClickListener {
            navigation.hideAlert()
        }

        btn3.setOnClickListener {
            navigation.addBadge(Random().nextInt(200))
        }
    }
}
