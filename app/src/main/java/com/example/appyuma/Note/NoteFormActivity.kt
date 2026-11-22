package com.example.appyuma.Note

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.appyuma.data.AppDatabase
import com.example.appyuma.data.entity.NoteEntity
import com.example.appyuma.databinding.ActivityNoteFormBinding
import kotlinx.coroutines.launch

class NoteFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteFormBinding
    private lateinit var db: AppDatabase
    private var noteId: Int = -1
    private var noteCreatedAt: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityNoteFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        db = AppDatabase.getInstance(this)
        noteId = intent.getIntExtra(EXTRA_NOTE_ID, -1)
        noteCreatedAt = intent.getLongExtra(EXTRA_NOTE_CREATED_AT, System.currentTimeMillis())

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = if (noteId != -1) "Edit Catatan" else "Tambah Catatan"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        if (noteId != -1) {
            binding.etTitle.setText(intent.getStringExtra(EXTRA_NOTE_TITLE) ?: "")
            binding.etContent.setText(intent.getStringExtra(EXTRA_NOTE_CONTENT) ?: "")
            binding.btnSaveNote.text = "Simpan Perubahan"
        }

        binding.btnSaveNote.setOnClickListener {
            val title = binding.etTitle.text.toString().trim()
            val content = binding.etContent.text.toString().trim()

            if (title.isBlank() || content.isBlank()) {
                Toast.makeText(
                    this, "Isi semua kolom!", Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            binding.btnSaveNote.isEnabled = false
            lifecycleScope.launch {
                try {
                    if (noteId != -1) {
                        db.noteDao().update(
                            NoteEntity(
                                id = noteId,
                                title = title,
                                content = content,
                                createdAt = noteCreatedAt
                            )
                        )
                    } else {
                        db.noteDao().insert(
                            NoteEntity(title = title, content = content)
                        )
                    }
                    finish()
                } catch (e: Exception) {
                    binding.btnSaveNote.isEnabled = true
                    Toast.makeText(
                        this@NoteFormActivity,
                        "Gagal menyimpan catatan",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
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
        const val EXTRA_NOTE_ID = "extra_note_id"
        const val EXTRA_NOTE_TITLE = "extra_note_title"
        const val EXTRA_NOTE_CONTENT = "extra_note_content"
        const val EXTRA_NOTE_CREATED_AT = "extra_note_created_at"
    }
}
