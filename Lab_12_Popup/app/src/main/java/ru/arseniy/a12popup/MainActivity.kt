package ru.arseniy.a12popup

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val imageView: ImageView = findViewById(R.id.imageView)
        val textView: TextView = findViewById(R.id.textView)
        val button: Button = findViewById(R.id.button)

        val popupMenu2 = PopupMenu(this, button)
        popupMenu2.inflate(R.menu.popup_menu)
        popupMenu2.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.shrek -> {
                    textView.background = ColorDrawable(Color.GREEN)
                    textView.text = "Вы выбрали Шрека"
                }
                R.id.donkey -> {
                    textView.background = ColorDrawable(Color.GRAY)
                    textView.text = "Вы выбрали Осла"
                }
                R.id.witch -> {
                    textView.background = ColorDrawable(Color.MAGENTA)
                    textView.text = "Вы выбрали Ведьму"
                }
            }
            false
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            popupMenu2.setForceShowIcon(true)
        }

        button.setOnClickListener {
            popupMenu2.show()
        }
        imageView.setOnClickListener {
            popupMenu2.show()
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}