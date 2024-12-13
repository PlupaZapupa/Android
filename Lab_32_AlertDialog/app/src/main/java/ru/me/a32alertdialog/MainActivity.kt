package ru.me.a32alertdialog

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            val myDialogFragment = MyDialogFragment()
            val manager = supportFragmentManager
            //myDialogFragment.show(manager, "dialog")
            val transaction: FragmentTransaction = manager.beginTransaction()
            myDialogFragment.show(transaction, "dialog")
        }

        val button2: Button = findViewById(R.id.button2)
        button2.setOnClickListener {
            val myDialogFragment = MyDialogFragment2()
            val manager = supportFragmentManager
            //myDialogFragment.show(manager, "dialog")
            val transaction: FragmentTransaction = manager.beginTransaction()
            myDialogFragment.show(transaction, "dialog2")
        }

        val button3: Button = findViewById(R.id.button3)
        button3.setOnClickListener {
            val myDialogFragment = MyDialogFragment3()
            val manager = supportFragmentManager
            //myDialogFragment.show(manager, "dialog")
            val transaction: FragmentTransaction = manager.beginTransaction()
            myDialogFragment.show(transaction, "dialog3")
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun okClicked() {
        Toast.makeText(
            applicationContext, "Вы выбрали кнопку Мяу!",
            Toast.LENGTH_SHORT
        ).show()
    }

    fun cancelClicked() {
        Toast.makeText(
            applicationContext, "Вы выбрали кнопку Гав!",
            Toast.LENGTH_SHORT
        ).show()
    }
}