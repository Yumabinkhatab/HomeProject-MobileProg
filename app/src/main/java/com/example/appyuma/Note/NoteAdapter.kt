package com.example.appyuma.Note

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appyuma.data.entity.NoteEntity
import com.example.appyuma.databinding.ItemNoteBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NoteAdapter(
    private val notes: List<NoteEntity>,
    private val onEdit: (NoteEntity) -> Unit,
    private val onDelete: (NoteEntity) -> Unit
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val dateFormat = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.forLanguageTag("id-ID"))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.binding.tvTitle.text = note.title
        holder.binding.tvContent.text = note.content
        holder.binding.tvDate.text = dateFormat.format(Date(note.createdAt))

        holder.binding.root.setOnClickListener {
            onEdit(note)
        }

        holder.binding.btnDelete.setOnClickListener {
            MaterialAlertDialogBuilder(holder.itemView.context)
                .setTitle("Hapus Catatan")
                .setMessage("Yakin ingin menghapus catatan ini?")
                .setPositiveButton("Ya") { _, _ -> onDelete(note) }
                .setNegativeButton("Batal", null)
                .show()
        }
    }

    override fun getItemCount(): Int = notes.size
}
