package ru.me.a26preferencesframework

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    private lateinit var settingCheckBox: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button)
        val button2: Button = findViewById(R.id.button2)
        settingCheckBox = findViewById(R.id.textView)

        button.setOnClickListener {
            showSettings()
        }

        button2.setOnClickListener {
            showSettings2()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun showSettings() {
        val intent = Intent(
            this@MainActivity,
            MyPreferenceActivity::class.java
        )
        startActivity(intent)
    }

    fun showSettings2() {
        val intent = Intent(
            this@MainActivity,
            RingtonePreferenceActivity::class.java
        )
        startActivity(intent)
    }

    // получаем результат
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val sharedPreferences = PreferenceManager
            .getDefaultSharedPreferences(this)

        val catHeight = sharedPreferences.getInt("height", 20)
        // Добавим TextView, в котором будем выводить значение настройки
        settingCheckBox.setText(
            "Высота кота = "
                    + catHeight
        )
    }
}