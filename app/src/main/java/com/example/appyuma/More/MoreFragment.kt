package com.example.appyuma.More

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.appyuma.R
import com.example.appyuma.databinding.FragmentMoreBinding

class MoreFragment : Fragment() {

    private var _binding: FragmentMoreBinding? = null
    private val binding get() = _binding!!

    private val dataListWithDesc = listOf(
        mapOf("title" to "Kotlin", "desc" to "Bahasa utama untuk Android modern yang ringkas dan ekspresif"),
        mapOf("title" to "Java", "desc" to "Bahasa pemrograman OOP populer dan lintas platform yang tangguh"),
        mapOf("title" to "Python", "desc" to "Bahasa ramah pemula dengan sintaks bersih untuk AI dan web"),
        mapOf("title" to "JavaScript", "desc" to "Bahasa standar industri untuk pengembangan web interaktif"),
        mapOf("title" to "TypeScript", "desc" to "Superset JavaScript dengan sistem pengetikan statis yang kuat"),
        mapOf("title" to "Dart", "desc" to "Bahasa yang dioptimalkan untuk UI aplikasi cepat bersama Flutter"),
        mapOf("title" to "Swift", "desc" to "Bahasa bertenaga dan intuitif untuk ekosistem Apple"),
        mapOf("title" to "C++", "desc" to "Bahasa berkinerja tinggi untuk sistem operasi dan mesin game"),
        mapOf("title" to "Go", "desc" to "Bahasa sederhana dan efisien untuk layanan backend cloud berkecepatan tinggi"),
        mapOf("title" to "Rust", "desc" to "Bahasa fokus pada performa serta keamanan memori tanpa garbage collector")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Lainnya"
            subtitle = null
            setDisplayHomeAsUpEnabled(false)
            setDisplayShowHomeEnabled(false)
        }

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
