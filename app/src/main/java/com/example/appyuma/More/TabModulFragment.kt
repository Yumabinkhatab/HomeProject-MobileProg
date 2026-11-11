package com.example.appyuma.More

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appyuma.databinding.FragmentTabModulBinding

class TabModulFragment : Fragment() {

    private var _binding: FragmentTabModulBinding? = null
    private val binding get() = _binding!!

    private val modulList = listOf(
        ModulModel("Modul Penalaran Umum UTBK", "Gratis · 10 Menit Baca", "https://picsum.photos/seed/oppi1/400/300"),
        ModulModel("Rangkuman Pengetahuan Kuantitatif", "Gratis · 8 Menit Baca", "https://picsum.photos/seed/oppi2/400/300"),
        ModulModel("Panduan Literasi Bahasa Indonesia", "Gratis · 7 Menit Baca", "https://picsum.photos/seed/oppi3/400/300"),
        ModulModel("Reading Comprehension Bahasa Inggris", "Gratis · 10 Menit Baca", "https://picsum.photos/seed/oppi4/400/300"),
        ModulModel("Rumus Cepat Penalaran Matematika", "Gratis · 12 Menit Baca", "https://picsum.photos/seed/oppi5/400/300"),
        ModulModel("Ringkasan Konsep Fisika Dasar SMA", "Gratis · 8 Menit Baca", "https://picsum.photos/seed/oppi6/400/300"),
        ModulModel("Catatan Biologi Sel & Genetika", "Gratis · 6 Menit Baca", "https://picsum.photos/seed/oppi7/400/300"),
        ModulModel("Modul Kimia Stoikiometri Praktis", "Gratis · 9 Menit Baca", "https://picsum.photos/seed/oppi8/400/300"),
        ModulModel("Strategi Manajemen Waktu UTBK", "Gratis · 5 Menit Baca", "https://picsum.photos/seed/oppi9/400/300"),
        ModulModel("Rangkuman Ekonomi & Akuntansi Dasar", "Gratis · 7 Menit Baca", "https://picsum.photos/seed/oppi10/400/300"),
        ModulModel("Catatan Sejarah & Geografi SMA", "Gratis · 6 Menit Baca", "https://picsum.photos/seed/oppi11/400/300"),
        ModulModel("Kumpulan Konsep Sosiologi Modern", "Gratis · 5 Menit Baca", "https://picsum.photos/seed/oppi12/400/300")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabModulBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val modulAdapter = ModulAdapter(modulList) { selectedItem ->
            Toast.makeText(
                requireContext(),
                "Membaca modul: ${selectedItem.name}",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.rvModul.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = modulAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
