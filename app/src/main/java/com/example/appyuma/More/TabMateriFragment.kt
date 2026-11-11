package com.example.appyuma.More

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.appyuma.R
import com.example.appyuma.databinding.FragmentTabMateriBinding

class TabMateriFragment : Fragment() {

    private var _binding: FragmentTabMateriBinding? = null
    private val binding get() = _binding!!

    private val dataListWithDesc = listOf(
        mapOf("title" to "Penalaran Umum", "desc" to "Latihan logika deduktif, induktif, dan pemecahan masalah analisis"),
        mapOf("title" to "Pengetahuan Kuantitatif", "desc" to "Konsep dasar aljabar, aritmatika, geometri, dan statistika UTBK"),
        mapOf("title" to "Penalaran Matematika", "desc" to "Penerapan konsep matematika dalam konteks persoalan sehari-hari"),
        mapOf("title" to "Literasi Bahasa Indonesia", "desc" to "Pemahaman bacaan, ide pokok, dan analisis teks ilmiah maupun sastra"),
        mapOf("title" to "Literasi Bahasa Inggris", "desc" to "Reading comprehension, kosakata akademik, dan inferensi teks Inggris"),
        mapOf("title" to "Matematika Lanjut SMA", "desc" to "Persamaan linear, trigonometri, matriks, limit, dan kalkulus dasar"),
        mapOf("title" to "Fisika SMA", "desc" to "Mekanika gerak, hukum Newton, usaha energi, dan listrik magnet"),
        mapOf("title" to "Kimia SMA", "desc" to "Struktur atom, ikatan kimia, stoikiometri, dan laju reaksi"),
        mapOf("title" to "Biologi SMA", "desc" to "Biologi sel, metabolisme, genetika, dan ekosistem lingkungan"),
        mapOf("title" to "Ekonomi & Sosiologi", "desc" to "Prinsip ekonomi makro-mikro, akuntansi dasar, dan interaksi sosial")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabMateriBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = SimpleAdapter(
            requireContext(),
            dataListWithDesc,
            R.layout.item_more,
            arrayOf("title", "desc"),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )

        binding.listViewItems.adapter = adapter

        binding.listViewItems.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = dataListWithDesc[position]
            val title = selectedItem["title"]
            val desc = selectedItem["desc"]
            Toast.makeText(
                requireContext(),
                "Kamu memilih: $title ($desc)",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

