package ru.arseniy.lab_3_click

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var counterRaven : Int = 0

    private var counterCat : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val textView: TextView = findViewById(R.id.textView)

        val button: Button = findViewById(R.id.button_hello)
        button.setOnClickListener {
            textView.text = "Привет, котик!"
        }

        val buttonRaven : Button = findViewById(R.id.button_raven)
        buttonRaven.setOnClickListener {
            textView.text = "Я насчитал ${++counterRaven} ворон"
        }

        val buttonCat : Button = findViewById(R.id.button_cat)
        buttonCat.setOnClickListener{
            textView.text = "Я насчитал ${++counterCat} котиков"
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}