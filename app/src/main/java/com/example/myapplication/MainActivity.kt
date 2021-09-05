package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_main)

        componentButtonListener()
    }


//    override fun onStart() {
//        super.onStart()
//        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
//                // Set the content to appear under the system bars so that the
//                // content doesn't resize when the system bars hide and show.
//                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                // Hide the nav bar and status bar
//                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                or View.SYSTEM_UI_FLAG_FULLSCREEN)
//    }

    private fun componentButtonListener(){
        val firstButton = findViewById<Button>(R.id.FirstButton)

        firstButton.setOnClickListener {
            val firstTextView = findViewById<TextView>(R.id.FirstTextView)
            val progressBar = findViewById<ProgressBar>(R.id.progressBar)

            markButtonDisable(firstButton)
            firstTextView.text = getString(R.string.loginSuccessStatus)
            progressBar.visibility = View.VISIBLE
            it.postDelayed({
                startSecondActivity()
                           }, 1000)
        }
    }

    private fun startSecondActivity (){
        val chefIntent = Intent(this, ChiefActivity::class.java)
        startActivity(chefIntent)
    }

    private fun markButtonDisable(button: Button) {
        button.isEnabled = false
        button.setBackgroundColor(Color.parseColor("#C0C0C0"))
    }

}