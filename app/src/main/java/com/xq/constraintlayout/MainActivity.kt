package com.xq.constraintlayout

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val linearLayout = findViewById<LinearLayout>(R.id.root)

        packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES).activities
            .filterNot { it.name == this::class.java.name }
            .map { Class.forName(it.name) }
            .forEach { clazz ->
                linearLayout.addView(AppCompatButton(this).apply {
                    isAllCaps = false
                    text = clazz.simpleName
                    setOnClickListener {
                        startActivity(Intent(this@MainActivity, clazz))
                    }
                })
            }
     }
    }

