package com.example.appyuma.More

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appyuma.DetailActivity
import com.example.appyuma.databinding.FragmentTabModulBinding

class TabModulFragment : Fragment() {

    private var _binding: FragmentTabModulBinding? = null
    private val binding get() = _binding!!

    private val modulList = listOf(
        ModulModel(
            "Modul Penalaran Umum UTBK",
            "Gratis · 10 Menit Baca",
            "https://picsum.photos/seed/oppi1/400/300",
            "Membahas cara menarik kesimpulan logis secara deduktif, induktif, dan membaca pola bilangan maupun argumen dalam soal TPS UTBK.",
            "Ringkasan Modul:\n• Silogisme, modus ponens, dan modus tollens\n• Identifikasi kesimpulan yang paling sesuai teks\n• Pola deret aritmatika & perbandingan kuantitatif"
        ),
        ModulModel(
            "Rangkuman Pengetahuan Kuantitatif",
            "Gratis · 8 Menit Baca",
            "https://picsum.photos/seed/oppi2/400/300",
            "Ringkasan materi hitung cepat aljabar, sifat bilangan, persamaan garis, serta tipe soal kecukupan data (P vs Q).",
            "Ringkasan Modul:\n• Operasi pecahan, pangkat, dan akar\n• Persamaan dan pertidaksamaan kuadrat\n• Strategi menjawab soal kecukupan pernyataan (1) dan (2)"
        ),
        ModulModel(
            "Panduan Literasi Bahasa Indonesia",
            "Gratis · 7 Menit Baca",
            "https://picsum.photos/seed/oppi3/400/300",
            "Teknik membaca cepat paragraf panjang untuk menemukan gagasan utama, makna tersirat, serta kalimat efektif sesuai PUEBI/EYD V.",
            "Ringkasan Modul:\n• Cara cepat mencari ide pokok tiap paragraf\n• Membedakan fakta dan opini penulis\n• Koreksi ejaan, tanda baca, dan kata baku"
        ),
        ModulModel(
            "Reading Comprehension Bahasa Inggris",
            "Gratis · 10 Menit Baca",
            "https://picsum.photos/seed/oppi4/400/300",
            "Panduan memahami teks akademik berbahasa Inggris untuk menentukan main idea, tone penulis, serta vocabulary in context.",
            "Ringkasan Modul:\n• Skimming dan scanning untuk soal UTBK\n• Menentukan sikap & tujuan penulis (author's tone)\n• Menjawab soal inference tanpa menerjemahkan kata per kata"
        ),
        ModulModel(
            "Rumus Cepat Penalaran Matematika",
            "Gratis · 12 Menit Baca",
            "https://picsum.photos/seed/oppi5/400/300",
            "Penerapan konsep matematika ke dalam masalah kontekstual sehari-hari seperti bunga bank, kecepatan rata-rata, dan peluang.",
            "Ringkasan Modul:\n• Aritmatika sosial: diskon, pajak, dan bunga majemuk\n• Perbandingan senilai dan berbalik nilai\n• Membaca tabel data, grafik, dan diagram"
        ),
        ModulModel(
            "Ringkasan Konsep Fisika Dasar SMA",
            "Gratis · 8 Menit Baca",
            "https://picsum.photos/seed/oppi6/400/300",
            "Merangkum konsep inti kinematika, dinamika hukum Newton, usaha-energi, serta rangkaian listrik searah.",
            "Ringkasan Modul:\n• GLB dan GLBB serta gerak jatuh bebas\n• Hukum I, II, dan III Newton pada bidang datar & miring\n• Hukum Ohm dan rangkaian seri-paralel"
        ),
        ModulModel(
            "Catatan Biologi Sel & Genetika",
            "Gratis · 6 Menit Baca",
            "https://picsum.photos/seed/oppi7/400/300",
            "Pengenalan organel sel prokariotik vs eukariotik, pembelahan sel mitosis-meiosis, serta pewarisan sifat hukum Mendel.",
            "Ringkasan Modul:\n• Fungsi mitokondria, ribosom, dan kloroplas\n• Tahapan profase, metafase, anafase, telofase\n• Persilangan monohibrid dan dihibrid"
        ),
        ModulModel(
            "Modul Kimia Stoikiometri Praktis",
            "Gratis · 9 Menit Baca",
            "https://picsum.photos/seed/oppi8/400/300",
            "Langkah praktis menghitung mol, massa molar, penyetaraan reaksi kimia, dan menentukan pereaksi pembatas.",
            "Ringkasan Modul:\n• Hubungan mol, massa (gram), dan Mr/Ar\n• Volume gas pada kondisi STP dan RTP\n• Menentukan pereaksi pembatas dalam reaksi"
        ),
        ModulModel(
            "Strategi Manajemen Waktu UTBK",
            "Gratis · 5 Menit Baca",
            "https://picsum.photos/seed/oppi9/400/300",
            "Panduan membagi waktu pengerjaan subtes UTBK-SNBT agar tidak kehabisan waktu di soal sulit.",
            "Ringkasan Modul:\n• Metode dua putaran: kerjakan soal mudah dahulu\n• Batasi maksimal 60–75 detik per soal\n• Evaluasi hasil tryout untuk memetakan subtes terlemah"
        ),
        ModulModel(
            "Rangkuman Ekonomi & Akuntansi Dasar",
            "Gratis · 7 Menit Baca",
            "https://picsum.photos/seed/oppi10/400/300",
            "Membahas hukum permintaan-penawaran, keseimbangan pasar, inflasi, dan persamaan dasar akuntansi.",
            "Ringkasan Modul:\n• Pergeseran kurva permintaan dan penawaran\n• Kebijakan moneter dan fiskal pemerintah\n• Persamaan akuntansi: Harta = Utang + Modal"
        ),
        ModulModel(
            "Catatan Sejarah & Geografi SMA",
            "Gratis · 6 Menit Baca",
            "https://picsum.photos/seed/oppi11/400/300",
            "Ringkasan periodisasi sejarah pergerakan nasional Indonesia serta konsep dasar litosfer, atmosfer, dan peta geografi.",
            "Ringkasan Modul:\n• Kronologi peristiwa sekitar Proklamasi 1945\n• Jenis peta, skala, dan penginderaan jauh (SIG)\n• Lapisan atmosfer dan dinamika kependudukan"
        ),
        ModulModel(
            "Kumpulan Konsep Sosiologi Modern",
            "Gratis · 5 Menit Baca",
            "https://picsum.photos/seed/oppi12/400/300",
            "Mempelajari nilai dan norma sosial, bentuk interaksi, konflik sosial, serta stratifikasi dan mobilitas masyarakat.",
            "Ringkasan Modul:\n• Syarat terjadinya interaksi sosial\n• Agen sosialisasi dan pengendalian sosial\n• Mobilitas sosial vertikal dan horizontal"
        )
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
            val intent = DetailActivity.createIntent(
                context = requireContext(),
                title = selectedItem.name,
                subtitle = selectedItem.info,
                category = "Modul Baca Gratis",
                description = selectedItem.summary,
                points = selectedItem.points
            )
            startActivity(intent)
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
