package com.example.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        componentListener()
    }

    private fun componentListener(){
        val firstButton = findViewById<Button>(R.id.FirstButton)

        firstButton.setOnClickListener {
            val firstTextView = findViewById<TextView>(R.id.FirstTextView)
            val progressBar = findViewById<ProgressBar>(R.id.progressBar)

            firstTextView.text = getString(R.string.loginSuccessStatus)
            progressBar.visibility = View.VISIBLE
            it.postDelayed({
                startSecondActivity()
                           }, 500)
        }
    }

    private fun startSecondActivity (){
        val chefIntent = Intent(this, ChiefActivity::class.java)
        startActivity(chefIntent)
    }

}