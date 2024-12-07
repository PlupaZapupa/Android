package ru.arseniy.lab18

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import ru.arseniy.lab18.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pulseAnimation = AnimationUtils.loadAnimation(this, R.anim.pulse)
        binding.star.startAnimation(pulseAnimation)

        // Устанавливаем анимацию планеты
        setupPlanetAnimation()


    }

    // Функция для установки анимации вращения планеты по орбите
    private fun setupPlanetAnimation() {
        // Получаем размеры экрана
        val screenWidth = resources.displayMetrics.widthPixels.toFloat()
        val screenHeight = resources.displayMetrics.heightPixels.toFloat()

        // Координаты центра экрана
        val centerX = screenWidth / 2
        val centerY = screenHeight / 2

        // Радиус орбиты планеты (увеличьте это значение, чтобы отдалить планету от Солнца)
        val radius = 360f

        // Устанавливаем начальное положение планеты на орбите
        binding.planet.x = centerX + radius - binding.planet.width / 2
        binding.planet.y = centerY - binding.planet.height / 2

        // Создаем анимацию для вращения планеты по орбите
        val animator = ValueAnimator.ofFloat(0f, 360f)
        animator.duration = 5000 // Продолжительность анимации (в миллисекундах)
        animator.repeatCount = ValueAnimator.INFINITE // Бесконечное повторение анимации

        // Применяем обновление координат планеты
        animator.addUpdateListener { animation ->
            // Получаем угол на текущий момент (в градусах)
            val angle = Math.toRadians((animation.animatedValue as Float).toDouble())

            // Вычисляем новые координаты планеты на основе угла
            val x = (centerX + radius * Math.cos(angle)).toFloat() - binding.planet.width / 2
            val y = (centerY + radius * Math.sin(angle)).toFloat() - binding.planet.height / 2

            // Обновляем координаты планеты
            binding.planet.x = x
            binding.planet.y = y
        }

        // Запускаем анимацию
        animator.start()
    }
}
