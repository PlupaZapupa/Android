package ru.me.a13screen

import android.content.Intent
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.DisplayMetrics
import android.util.Log
import android.view.Display
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var button: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        textView = findViewById(R.id.textView)


        //Показать окно Экран из системного приложения Настройки
        button.setOnClickListener {
            val intent = Intent(Settings.ACTION_DISPLAY_SETTINGS)
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }
        //Получить размер экрана
        button2.setOnClickListener {
            val display: Display = windowManager.defaultDisplay
            val point = Point()
            display.getSize(point)
            val screenWidth: Int = point.x
            val screenHeight: Int = point.y

            // Теперь получим необходимую информацию
            val width = Integer.toString(screenWidth)
            val height = Integer.toString(screenHeight)

            val info = "Ширина: $width; Высота: $height"

            textView.text = info
        }

        //Получить текущее значение яркости экрана
        button3.setOnClickListener {
            try {
                val curBrightnessValue = Settings.System.getInt(
                    contentResolver,
                    Settings.System.SCREEN_BRIGHTNESS
                )
                textView.text = "Текущая яркость экрана: $curBrightnessValue"
            } catch (e: Settings.SettingNotFoundException) {
                e.printStackTrace()
            }
        }

        //Получить расширенную информацию об экране
        button4.setOnClickListener {
            var screen = ""
            val metrics = DisplayMetrics()

            if (Build.VERSION.SDK_INT >= 30){
                display?.apply {
                    getRealMetrics(metrics)
                    screen = """
                Width: ${metrics.widthPixels} pixels
                Height: ${metrics.heightPixels} pixels 
                The Logical Density: ${metrics.density}  
                X Dimension: ${metrics.xdpi} dot/inch
                Y Dimension: ${metrics.ydpi} dot/inch
                The screen density expressed as dots-per-inch: ${metrics.densityDpi}
                A scaling factor for fonts displayed on the display: ${metrics.scaledDensity}
            """
                }
            }else{
                // getMetrics() method was deprecated in api level 30
                windowManager.defaultDisplay.getMetrics(metrics)
                screen = """
        Width: ${metrics.widthPixels} pixels
        Height: ${metrics.heightPixels} pixels 
        The Logical Density: ${metrics.density}  
        X Dimension: ${metrics.xdpi} dot/inch
        Y Dimension: ${metrics.ydpi} dot/inch
        The screen density expressed as dots-per-inch: ${metrics.densityDpi}
        A scaling factor for fonts displayed on the display: ${metrics.scaledDensity}"""
            }

            val infoTextView = findViewById<TextView>(R.id.textView)
            infoTextView.text = screen
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}