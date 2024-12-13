package ru.me.a32alertdialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment


class MyDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setCancelable(true)
                .setTitle("Важное сообщение!")
                .setMessage("Выберите правильный ответ")
                .setIcon(R.drawable.pinkhellokitty)
                .setPositiveButton("Мяу", DialogInterface.OnClickListener { dialogInterface, i ->  (it as MainActivity).okClicked()})
                .setNegativeButton("Гав", DialogInterface.OnClickListener { dialogInterface, i ->  (it as MainActivity).cancelClicked()})

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }


}