package ru.me.toast

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
//            //val toast = Toast.makeText(applicationContext, R.string.cat_food, Toast.LENGTH_LONG)
//            //toast.setGravity(Gravity.CENTER, 0, 0)
//
//            var linearLayout = LinearLayout(applicationContext)
//            var textView = TextView(applicationContext)
//            textView.text = R.string.cat_food.toString()
//
//            val catImage = ImageView(applicationContext)
//            catImage.setImageResource(R.drawable.pinkhellokitty)
//
//            linearLayout.addView(catImage)
//            linearLayout.addView(textView)
//
//            val toast = Toast(applicationContext)
//            toast.setGravity(Gravity.BOTTOM, 0, 0)
//            toast.duration = Toast.LENGTH_LONG
//            toast.setText(R.string.cat_food)
//            toast.view = linearLayout
//
//
//            toast.show()

            val inflater = layoutInflater
            val container = findViewById<ViewGroup>(R.id.custom_toast_container)
            val layout: View = inflater.inflate(R.layout.custom_toast, container)
            val text: TextView = layout.findViewById(R.id.text)
            text.text = "Я ХОЧУ ЖРААААТЬ!"
            with (Toast(applicationContext)) {
                setGravity(Gravity.CENTER_VERTICAL, 0, 0)
                duration = Toast.LENGTH_LONG

                view = layout
                show()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}