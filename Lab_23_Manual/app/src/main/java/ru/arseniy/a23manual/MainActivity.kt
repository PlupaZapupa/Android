package ru.arseniy.a23manual

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // Список мифических существ
    private val titles = arrayOf(
        "01. Грифон",
        "02. Кетцалькоатль",
        "03. Дракон",
        "04. Феникс",
        "05. Цербер",
        "06. Кракен",
        "07. Чупакабра"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Инициализация списка
        val listView: ListView = findViewById(R.id.listView)
        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, titles)

        // Обработка кликов по элементам списка
        listView.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("title", position)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}