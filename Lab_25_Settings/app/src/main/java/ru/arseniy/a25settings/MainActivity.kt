package ru.arseniy.a25settings

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var counter: Int = 0
    private lateinit var prefs: SharedPreferences //переменная для работы с настройками
    private val APP_PREFERENCES_COUNTER = "counter" // ключ, который идентифицирует конкретное значение в SharedPreferences
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        prefs =
            getSharedPreferences("settings", Context.MODE_PRIVATE) // 1-й параметр - имя файла, 2-й - разрешение (только приложение имеет дступ к настройкам)

        val button : Button = findViewById(R.id.button_raven)
        textView = findViewById(R.id.textView)

        button.setOnClickListener{
            textView.text = "Я насчитал ${++counter} ворон"
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.button_raven)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onPause() {
        super.onPause()

        // Запоминаем данные
        val editor = prefs.edit() // получаем editor для редактирования настроек
        editor.putInt(APP_PREFERENCES_COUNTER, counter).apply() //кладем объект и применяем изменения
    }

    override fun onResume() {
        super.onResume()

        if(prefs.contains(APP_PREFERENCES_COUNTER)){
            // Получаем число из настроек
            counter = prefs.getInt(APP_PREFERENCES_COUNTER, 0) // 0 - значение по умолчанию, если ключ не найден
            // Выводим на экран данные из настроек
            textView.text = "Я насчитал $counter ворон"
        }
    }

}