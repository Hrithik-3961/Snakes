package com.hrithik.snakes

import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dm = DisplayMetrics()
        this.display?.getMetrics(dm)
        Constants.SCREEN_HEIGHT = dm.heightPixels
        Constants.SCREEN_WIDTH = dm.widthPixels
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        }
        setContentView(R.layout.activity_main)
    }
}