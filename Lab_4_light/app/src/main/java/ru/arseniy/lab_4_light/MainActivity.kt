package ru.arseniy.lab_4_light

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val rootLayout : ConstraintLayout = findViewById(R.id.root_layout)
        val textView : TextView = findViewById(R.id.textView)
        val redButton : Button = findViewById(R.id.red_button)
        val yellowButton : Button = findViewById(R.id.yellow_button)
        val greenButton : Button = findViewById(R.id.green_button)

        redButton.setOnClickListener{
            textView.setText(R.string.red)
            rootLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.red))
        }
        yellowButton.setOnClickListener{
            textView.setText(R.string.yellow)
            rootLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.yellow))
        }
        greenButton.setOnClickListener{
            textView.setText(R.string.green)
            rootLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
        }




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}