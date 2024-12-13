package ru.me.a32alertdialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class MyDialogFragment3 : DialogFragment() {
    private val catNames = arrayOf("Васька", "Рыжик", "Мурзик")
    private val checkedItems = booleanArrayOf(false, true, false)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Выберите котов")
                .setMultiChoiceItems(
                    catNames, checkedItems
                ) { _, which, isChecked ->
                    checkedItems[which] = isChecked
                    // val name = catNames[which] // Get the clicked item
                    // println(name)
                }
                .setPositiveButton(
                    "Готово"
                ) { _, _ ->
                    for (i in catNames.indices) {
                        val checked = checkedItems[i]
                        if (checked) {
                            println(catNames[i])
                        }
                    }
                }
                .setNegativeButton(
                    "Отмена"
                ) { dialog, _ ->
                    dialog.cancel()
                }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}