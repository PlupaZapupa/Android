package ru.me.a10sounds

import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var soundPool: SoundPool
    private lateinit var assetManager: AssetManager

    private var sadTomSound: Int = 0
    private var indeanTomSound: Int = 0
    private var screamTomSound: Int = 0
    private var scareTomSound: Int = 0

    private var streamID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val indeanTom: ImageView = findViewById(R.id.indean_tom)
        val scareTom: ImageView = findViewById(R.id.scare_tom)
        val sadTom: ImageView = findViewById(R.id.sad_tom)
        val screamTom: ImageView = findViewById(R.id.scream_tom)

        val attributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        soundPool = SoundPool.Builder()
            .setAudioAttributes(attributes)
            .build()

        assetManager = assets
        indeanTomSound = loadSound("tom_indean.ogg")
        scareTomSound = loadSound("tom_scare.ogg")
        sadTomSound = loadSound("tom_sad.ogg")
        screamTomSound = loadSound("tom_scream.ogg")


        indeanTom.setOnClickListener { playSound(indeanTomSound) }
        scareTom.setOnClickListener { playSound(scareTomSound) }
        sadTom.setOnClickListener { playSound(sadTomSound) }
        screamTom.setOnClickListener { playSound(screamTomSound) }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onPause() {
        super.onPause()

        soundPool.release()
    }


    private fun playSound(sound: Int): Int {
        if (sound > 0) {
            streamID = soundPool.play(sound, 1F, 1F, 1, 0, 1F)
        }
        return streamID
    }

    private fun loadSound(fileName: String): Int {
        val afd: AssetFileDescriptor = try {
            application.assets.openFd(fileName)
        } catch (e: IOException) {
            e.printStackTrace()
            Log.d("Meow", "Не могу загрузить файл $fileName")

            return -1
        }
        return soundPool.load(afd, 1)
    }
}