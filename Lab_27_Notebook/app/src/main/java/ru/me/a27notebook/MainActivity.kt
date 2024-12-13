package ru.me.a27notebook

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File

class MainActivity : AppCompatActivity() {
    private val FILENAME = "sample.txt" // имя файла

    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_open -> {
                openFile(FILENAME)
                true
            }
            R.id.action_save -> {
                saveFile(FILENAME)
                true
            }
            R.id.action_settings -> {
                val intent = Intent()
                intent.setClass(this, SettingsActivity::class.java)
                startActivity(intent)
                true
            }
            else -> true
        }
    }

    // Метод для открытия файла
    private fun openFile(fileName: String) {

        val textFromFile =
            File(applicationContext.filesDir, fileName)
                .bufferedReader()
                .use { it.readText(); }
        editText.setText(textFromFile)
    }

    // Метод для сохранения файла
    private fun saveFile(fileName: String) {
        applicationContext.openFileOutput(fileName, Context.MODE_PRIVATE).use {
            it.write(editText.text.toString().toByteArray())
        }
        Toast.makeText(applicationContext, "Файл сохранён", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()

        val prefs = PreferenceManager
            .getDefaultSharedPreferences(this)
        // читаем установленное значение из CheckBoxPreference
        if (prefs.getBoolean(getString(R.string.pref_openmode), false)) {
            openFile(FILENAME)
        }

        // читаем размер шрифта из EditTextPreference
        val fontSize = prefs.getString(
            getString(R.string.pref_size), "20"
        )!!.toFloat()
        // применяем настройки в текстовом поле
        editText.textSize = fontSize

        // читаем стили текста из ListPreference
        val regular = prefs.getString(getString(R.string.pref_style), "")
        var typeface = Typeface.NORMAL

        if (regular!!.contains("Полужирный")) typeface += Typeface.BOLD

        if (regular.contains("Курсив")) typeface += Typeface.ITALIC

        // меняем настройки в EditText
        editText.setTypeface(null, typeface)
    }
}