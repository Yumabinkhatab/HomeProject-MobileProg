package com.example.appyuma.Note

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appyuma.data.AppDatabase
import com.example.appyuma.data.entity.NoteEntity
import com.example.appyuma.databinding.FragmentNoteBinding
import kotlinx.coroutines.launch

class FragmentNote : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: AppDatabase
    private lateinit var adapter: NoteAdapter
    private val notes = mutableListOf<NoteEntity>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = AppDatabase.getInstance(requireContext())
        adapter = NoteAdapter(
            notes = notes,
            onEdit = { note ->
                val intent = Intent(requireContext(), NoteFormActivity::class.java).apply {
                    putExtra(NoteFormActivity.EXTRA_NOTE_ID, note.id)
                    putExtra(NoteFormActivity.EXTRA_NOTE_TITLE, note.title)
                    putExtra(NoteFormActivity.EXTRA_NOTE_CONTENT, note.content)
                    putExtra(NoteFormActivity.EXTRA_NOTE_CREATED_AT, note.createdAt)
                }
                startActivity(intent)
            },
            onDelete = { note -> deleteNote(note) }
        )

        binding.rvNotes.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNotes.adapter = adapter

        binding.fabAddNote.setOnClickListener {
            startActivity(Intent(requireContext(), NoteFormActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        if (_binding != null) fetchNotes()
    }

    private fun fetchNotes() {
        viewLifecycleOwner.lifecycleScope.launch {
            val result = db.noteDao().getAll()
            if (_binding == null) return@launch
            notes.clear()
            notes.addAll(result)
            adapter.notifyDataSetChanged()
            binding.tvEmptyNotes.visibility = if (notes.isEmpty()) View.VISIBLE else View.GONE
        }
    }

    private fun deleteNote(note: NoteEntity) {
        viewLifecycleOwner.lifecycleScope.launch {
            db.noteDao().delete(note)
            fetchNotes()
        }
    }

    override fun onDestroyView() {
        binding.rvNotes.adapter = null
        _binding = null
        super.onDestroyView()
    }
}
