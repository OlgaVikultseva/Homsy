package com.example.homsy

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.homsy.databinding.ActivityMainBinding
import com.google.android.material.color.MaterialColors

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        transparentStatusAndNavigationBar()
    }

    private fun AppCompatActivity.transparentStatusAndNavigationBar() {

        val statusAndNavigationBarColor = MaterialColors.getColor(this, R.attr.statusAndNavigationBarColor, Color.BLACK)

        val winParams = window.attributes
        winParams.flags =
            winParams.flags and (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION).inv()

        val systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        window.decorView.systemUiVisibility = systemUiVisibility

        window.statusBarColor = statusAndNavigationBarColor
        window.navigationBarColor = statusAndNavigationBarColor
        window.attributes = winParams
    }
}