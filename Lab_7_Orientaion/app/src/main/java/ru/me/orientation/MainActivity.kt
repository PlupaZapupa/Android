package ru.me.orientation

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var orientationView: TextView
    private lateinit var textView: TextView
    private lateinit var buttonRaven: Button
    private var mCount: Int = 0

    companion object {
        const val KEY_COUNT = "COUNT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        orientationView = findViewById(R.id.orientationView)
        textView = findViewById(R.id.textView)
        buttonRaven = findViewById(R.id.buttonRaven)

        orientationView.text = getScreenOrientation()

        buttonRaven.setOnClickListener {
            textView.text = "Я насчитал ${++mCount} ворон"
        }

        if (savedInstanceState != null) { //savedInstanceState содержит сохраненное значение переменной (то же самое, что outState)
            mCount = savedInstanceState.getInt(KEY_COUNT, 0)
            textView.text = "Я насчитал $mCount ворон"
        }
    }

    override fun onSaveInstanceState(outState: Bundle) { //Для сохранения количества числа ворон
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_COUNT, mCount)
    }

    private fun getScreenOrientation(): String {
        return when (resources.configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> "Портретная ориентация"
            Configuration.ORIENTATION_LANDSCAPE -> "Альбомная ориентация"
            else -> "Неопределённая ориентация"
        }
    }

}