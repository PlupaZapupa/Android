package ru.arseniy.a23manual

import android.content.Context
import android.os.Bundle
import android.webkit.WebView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)

        // Инициализация WebView
        val webView: WebView = findViewById(R.id.webView)

        // Получение позиции из Intent
        val position = intent.getIntExtra("title", -1)
        val resName = "n$position"

        // Загрузка содержимого из raw-ресурсов
        val text = readRawTextFile(this, resources.getIdentifier(resName, "raw", packageName))
        if (text.isNotEmpty()) {
            webView.loadDataWithBaseURL(null, text, "text/html", "UTF-8", null)
        } else {
            Toast.makeText(this, "Не удалось загрузить данные.", Toast.LENGTH_SHORT).show()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Метод чтения файла из raw-ресурсов
    private fun readRawTextFile(context: Context, resId: Int): String {
        return try {
            val inputStream: InputStream = context.resources.openRawResource(resId)
            val reader = BufferedReader(InputStreamReader(inputStream))
            reader.use { it.readText() }
        } catch (e: Exception) {
            ""
        }
    }

}