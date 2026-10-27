package com.example.appyuma.Message.tutorial

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.appyuma.LoginActivity
import com.example.appyuma.databinding.ActivityTutorialMessageBinding

class TutorialMessageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTutorialMessageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityTutorialMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val fragmentsList = listOf(
            Tutorial1Fragment(),
            Tutorial2Fragment(),
            Tutorial3Fragment()
        )
        val adapter = TutorialFragmentAdapter(this, fragmentsList)
        binding.tutorialMessageViewPager.adapter = adapter
        binding.dotIndicator.attachTo(binding.tutorialMessageViewPager)

        binding.tutorialMessageViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == fragmentsList.size - 1) {
                    binding.btnNext.text = "Mulai Belajar"
                } else {
                    binding.btnNext.text = "Lanjut"
                }
            }
        })

        binding.btnNext.setOnClickListener {
            val current = binding.tutorialMessageViewPager.currentItem
            if (current < fragmentsList.size - 1) {
                binding.tutorialMessageViewPager.currentItem = current + 1
            } else {
                finishTutorial()
            }
        }

        binding.btnSkip.setOnClickListener {
            finishTutorial()
        }
    }

    private fun finishTutorial() {
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        sharedPref.edit().putBoolean("tutorial_seen", true).apply()

        val isLogin = sharedPref.getBoolean("isLogin", false)
        if (!isLogin) {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        finish()
    }
}

