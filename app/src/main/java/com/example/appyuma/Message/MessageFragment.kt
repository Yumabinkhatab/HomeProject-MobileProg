package com.example.appyuma.Message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.appyuma.databinding.FragmentMessageBinding

class MessageFragment : Fragment() {

    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!

    private val messageList = listOf(
        MessageModel("Alya", "Udah coba latihan Persamaan Linear nomor 5?", "https://avatar.iran.liara.run/public/1", "09:15"),
        MessageModel("Budi", "Yuk Studi Bareng Fisika jam 7 malam!", "https://avatar.iran.liara.run/public/2", "09:30"),
        MessageModel("Citra", "Jangan lupa kumpulkan tugas Kimia besok ya!", "https://avatar.iran.liara.run/public/3", "10:02"),
        MessageModel("Dika", "Aku share rangkuman Biologi bab 3", "https://avatar.iran.liara.run/public/4", "10:45"),
        MessageModel("Eka", "Streak kamu keren, udah 3 hari!", "https://avatar.iran.liara.run/public/5", "11:20"),
        MessageModel("Fajar", "Ada yang paham soal trigonometri ini?", "https://avatar.iran.liara.run/public/6", "12:00"),
        MessageModel("Gita", "Tryout UTBK minggu depan, siap?", "https://avatar.iran.liara.run/public/7", "13:10"),
        MessageModel("Hana", "Makasih penjelasan Bahasa Inggrisnya!", "https://avatar.iran.liara.run/public/8", "14:15"),
        MessageModel("Irfan", "Kuis harian sudah aku kerjakan", "https://avatar.iran.liara.run/public/9", "15:00"),
        MessageModel("Joko", "Leaderboard minggu ini ketat banget", "https://avatar.iran.liara.run/public/10", "15:45")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Pesan"
            subtitle = null
            setDisplayHomeAsUpEnabled(false)
            setDisplayShowHomeEnabled(false)
        }

        val adapter = MessageAdapter(requireContext(), messageList)
        binding.listMessageItems.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
