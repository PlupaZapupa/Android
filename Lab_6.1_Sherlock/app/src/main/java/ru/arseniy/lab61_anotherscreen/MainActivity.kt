package ru.arseniy.lab61_anotherscreen

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var startForResult: ActivityResultLauncher<Intent>
    private lateinit var textViewResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        textViewResult = findViewById(R.id.answer)
        val buttonChoose: Button = findViewById(R.id.button_choose)

        // Регистрация обработчика результата
        startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                val answer = data?.getStringExtra(SecondActivity.THIEF) // Получение данных из SecondActivity
                textViewResult.text = answer // Установка результата в TextView
            }
        }

        buttonChoose.setOnClickListener {
            // Переход на SecondActivity
            val questionIntent = Intent(this@MainActivity, SecondActivity::class.java)
            startForResult.launch(questionIntent)
        }

        // Обработка insets для корректного отображения
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
