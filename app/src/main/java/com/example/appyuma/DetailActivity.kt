package com.example.appyuma

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appyuma.Note.NoteFormActivity
import com.example.appyuma.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val isLesson = intent.getBooleanExtra(EXTRA_IS_LESSON, false)
        val detailTitle = intent.getStringExtra(EXTRA_TITLE) ?: "Oppi: Teman Belajar Adaptif"
        val detailSubtitle = intent.getStringExtra(EXTRA_SUBTITLE) ?: "Untuk pelajar SMA/SMK & persiapan UTBK"
        val detailCategory = intent.getStringExtra(EXTRA_CATEGORY) ?: "Pendidikan Digital (EdTech)"
        val detailDescription = intent.getStringExtra(EXTRA_DESCRIPTION)
            ?: "Oppi adalah teman belajar adaptif: tes awal membentuk Peta Belajar personal, materi micro-lesson 5 menit, kuis dengan streak, dan Studi Bareng untuk belajar bersama teman. Hemat kuota dengan mode offline."
        val detailPoints = intent.getStringExtra(EXTRA_POINTS)
            ?: "Fitur Inti:\n• Tes diagnostik & Peta Belajar adaptif\n• Micro-lesson 5 menit (teks, video, infografis)\n• Kuis, XP, streak & lencana\n• Studi Bareng & Catatan Belajar lokal"

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = if (isLesson) "Materi Belajar" else "Tentang Oppi"
            subtitle = detailTitle
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        binding.tvCategory.text = detailCategory
        binding.tvDetailTitle.text = detailTitle
        binding.tvDetailSubtitle.text = detailSubtitle
        binding.tvDetailDescription.text = detailDescription
        binding.tvFeature.text = detailPoints

        binding.btnCreateNote.visibility = if (isLesson) View.VISIBLE else View.GONE
        binding.btnCreateNote.setOnClickListener {
            val noteIntent = Intent(this, NoteFormActivity::class.java).apply {
                putExtra(NoteFormActivity.EXTRA_NOTE_TITLE, "Catatan: $detailTitle")
            }
            startActivity(noteIntent)
        }

        binding.btnBackBottom.setOnClickListener {
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_SUBTITLE = "extra_subtitle"
        const val EXTRA_CATEGORY = "extra_category"
        const val EXTRA_DESCRIPTION = "extra_description"
        const val EXTRA_POINTS = "extra_points"
        const val EXTRA_IS_LESSON = "extra_is_lesson"

        fun createIntent(
            context: Context,
            title: String,
            subtitle: String,
            category: String,
            description: String,
            points: String,
            isLesson: Boolean = true
        ): Intent {
            return Intent(context, DetailActivity::class.java).apply {
                putExtra(EXTRA_TITLE, title)
                putExtra(EXTRA_SUBTITLE, subtitle)
                putExtra(EXTRA_CATEGORY, category)
                putExtra(EXTRA_DESCRIPTION, description)
                putExtra(EXTRA_POINTS, points)
                putExtra(EXTRA_IS_LESSON, isLesson)
            }
        }
    }
}
