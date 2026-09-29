package com.sanad.app040374

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.sanad.app040374.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isDarkMode = false
    private var tasbeehCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupDateTime()
        setupListeners()
        updateTasbeehUI()
    }

    private fun setupDateTime() {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("EEEE, d MMMM yyyy", Locale("ar"))
        val currentDate = dateFormat.format(calendar.time)
        
        binding.tvDate.text = currentDate
        binding.tvNextPrayerTime.text = "متبقي 01:24:35"
        binding.tvProgressPercent.text = "78%"
        binding.progressBar.progress = 78
    }

    private fun setupListeners() {
        binding.btnThemeToggle.setOnClickListener {
            isDarkMode = !isDarkMode
            if (isDarkMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.btnThemeToggle.text = "الوضع الفاتح"
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.btnThemeToggle.text = "الوضع الداكن"
            }
        }

        binding.btnTasbeehIncrement.setOnClickListener {
            tasbeehCount++
            updateTasbeehUI()
        }

        binding.navHome.setOnClickListener {
            showToast("الرئيسية")
            binding.scrollViewHome.visibility = View.VISIBLE
            binding.layoutTasbeeh.visibility = View.GONE
        }

        binding.navQuran.setOnClickListener {
            showToast("القرآن الكريم - قريباً")
        }

        binding.navPrayer.setOnClickListener {
            showToast("مواقيت الصلاة")
        }

        binding.navTasbeeh.setOnClickListener {
            binding.scrollViewHome.visibility = View.GONE
            binding.layoutTasbeeh.visibility = View.VISIBLE
        }

        binding.navMore.setOnClickListener {
            showToast("المزيد من الخيارات")
        }
    }

    private fun updateTasbeehUI() {
        binding.tvTasbeehCount.text = tasbeehCount.toString()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}