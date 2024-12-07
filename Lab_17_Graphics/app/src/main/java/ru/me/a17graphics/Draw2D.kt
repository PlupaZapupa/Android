package ru.me.a17graphics

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.view.View

class Draw2D(context: Context) : View(context) {

    private val paint = Paint().apply {
        isAntiAlias = true
    }
    private val shrekBitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.shrek1)
    private val scaledShrekBitmap: Bitmap = Bitmap.createScaledBitmap(shrekBitmap,
        (shrekBitmap.width * 0.5).toInt(),
        (shrekBitmap.height * 0.5).toInt(), true)


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Рисуем фон
        canvas.drawColor(Color.CYAN) // Небо

        // Рисуем траву
        paint.color = Color.GREEN
        canvas.drawRect(0f, (height / 2).toFloat(), width.toFloat(), height.toFloat(), paint)

        // Рисуем домик
        paint.color = Color.DKGRAY // Стены
        canvas.drawRect(300f, 800f, 700f, 1200f, paint)

        paint.color = Color.BLACK // Крыша
        canvas.drawPath(createRoofPath(), paint)

        // Рисуем дверь
        paint.color = Color.rgb(139, 69, 19) // Коричневая дверь
        canvas.drawRect(450f, 1000f, 550f, 1200f, paint)

        // Рисуем окно
        paint.color = Color.YELLOW // Свет в окне
        canvas.drawRect(320f, 850f, 380f, 910f, paint)

        // Рисуем дерево рядом с домиком
        paint.color = Color.rgb(139, 69, 19) // Ствол дерева
        canvas.drawRect(800f, 800f, 850f, 1200f, paint)

        paint.color = Color.GREEN // Листва
        canvas.drawCircle(825f, 700f, 100f, paint)

        // Рисуем Шрека
        val shrekX = 100f
        val shrekY = (height / 2 + 100).toFloat()
        canvas.drawBitmap(scaledShrekBitmap, shrekX, shrekY, null)

        // Рисуем текст справа от Шрека
        paint.apply {
            color = Color.BLACK
            textSize = 50f
            style = Paint.Style.FILL
        }
        canvas.drawText("Люблю свое болото", shrekX + scaledShrekBitmap.width + 20f, shrekY + 100f, paint)
    }



    private fun createRoofPath(): Path {
        return Path().apply {
            moveTo(300f, 800f)
            lineTo(500f, 600f)
            lineTo(700f, 800f)
            close()
        }
    }
}
