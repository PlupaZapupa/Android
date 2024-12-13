package ru.me.a28broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    companion object {
        val WHERE_MY_CAT_ACTION: String = "ru.me.a28broadcast.action.CAT"
        val ALARM_MESSAGE: String = "Срочно пришлите кота!"

        private fun getCurrentTimeStamp(): String {
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US)
            val now = Date()
            return simpleDateFormat.format(now)
        }
    }
    private lateinit var button : Button
    private lateinit var button2 : Button
    private lateinit var receiver : MessageReceiver
    private lateinit var textView: TextView
    private val timeBroadcastReceiver = TimeBroadcastReceiver()

    private val tickReceiver by lazy { makeBroadcastReceiver() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)
        button2 = findViewById(R.id.button2)
        textView = findViewById(R.id.textView)

        receiver = MessageReceiver()
        IntentFilter(WHERE_MY_CAT_ACTION).also {
            registerReceiver(receiver, it, RECEIVER_EXPORTED)
        }


        button.setOnClickListener {
            val intent = Intent()
            intent.action = WHERE_MY_CAT_ACTION
            intent.putExtra("ru.me.a28broadcast.Message", ALARM_MESSAGE)
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
            sendBroadcast(intent)
        }

        button2.setOnClickListener {
            registerReceiver(
                timeBroadcastReceiver, IntentFilter(
                    "android.intent.action.TIME_TICK"
                )
            )
            Toast.makeText(
                getApplicationContext(), "Приёмник включен",
                Toast.LENGTH_SHORT
            ).show();
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onStop(){
        super.onStop()
        unregisterReceiver(receiver)
        unregisterReceiver(tickReceiver)
    }

    override fun onResume() {
        super.onResume()

        textView.text = getCurrentTimeStamp()
        registerReceiver(tickReceiver, IntentFilter(Intent.ACTION_TIME_TICK))
    }

    override fun onDestroy() {
        super.onDestroy()

        unregisterReceiver(timeBroadcastReceiver)
        Toast.makeText(
            getApplicationContext(),
            "Приёмник выключён", Toast.LENGTH_SHORT
        )
            .show();
    }

    private fun makeBroadcastReceiver(): BroadcastReceiver {
        return object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent?) {
                if (intent?.action == Intent.ACTION_TIME_TICK) {
                    textView.text = getCurrentTimeStamp()
                }
            }
        }
    }
}