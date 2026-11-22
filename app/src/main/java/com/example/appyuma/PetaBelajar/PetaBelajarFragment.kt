package com.example.appyuma.PetaBelajar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.appyuma.DetailActivity
import com.example.appyuma.R
import com.example.appyuma.databinding.FragmentPetaBelajarBinding
import com.google.android.material.card.MaterialCardView

class PetaBelajarFragment : Fragment() {

    private var _binding: FragmentPetaBelajarBinding? = null
    private val binding get() = _binding!!

    private val topikMatematika = listOf(
        mapOf("icon" to "🔢", "judul" to "Bilangan", "status" to "Selesai", "desc" to "Mempelajari bilangan bulat, pecahan, persen, FPB/KPK, serta sifat operasi hitung dasar."),
        mapOf("icon" to "✖️", "judul" to "Eksponen", "status" to "Selesai", "desc" to "Memahami aturan pangkat positif, negatif, nol, dan penyederhanaan bentuk akar."),
        mapOf("icon" to "📈", "judul" to "Persamaan Linear", "status" to "Sedang belajar", "desc" to "Menyelesaikan persamaan linear satu dan dua variabel beserta soal cerita kontekstual."),
        mapOf("icon" to "⚖️", "judul" to "Pertidaksamaan", "status" to "Terkunci", "desc" to "Menentukan himpunan penyelesaian pertidaksamaan linear dan kuadrat."),
        mapOf("icon" to "📊", "judul" to "Fungsi", "status" to "Terkunci", "desc" to "Relasi, domain, kodomain, fungsi komposisi, dan fungsi invers."),
        mapOf("icon" to "📐", "judul" to "Trigonometri", "status" to "Terkunci", "desc" to "Perbandingan sinus, kosinus, tangen pada segitiga siku-siku dan sudut istimewa.")
    )

    private val topikLiterasi = listOf(
        mapOf("icon" to "💡", "judul" to "Ide Pokok", "status" to "Selesai", "desc" to "Menentukan kalimat utama dan gagasan pokok paragraf deduktif maupun induktif."),
        mapOf("icon" to "✏️", "judul" to "Kalimat Efektif", "status" to "Sedang belajar", "desc" to "Mengidentifikasi syarat kehematan, kepaduan, dan ketepatan kata sesuai EYD V."),
        mapOf("icon" to "🗣️", "judul" to "Teks Argumentasi", "status" to "Terkunci", "desc" to "Menganalisis klaim, bukti pendukung, dan kekuatan argumen dalam paragraf."),
        mapOf("icon" to "📰", "judul" to "Teks Eksposisi", "status" to "Terkunci", "desc" to "Memahami struktur tesis, rangkaian argumen, dan penegasan ulang."),
        mapOf("icon" to "🧠", "judul" to "Penalaran Umum", "status" to "Terkunci", "desc" to "Menarik simpulan logis berdasarkan premis dan data bacaan."),
        mapOf("icon" to "📖", "judul" to "Reading Comprehension", "status" to "Terkunci", "desc" to "Menentukan main idea, reference, dan inference dalam teks bahasa Inggris.")
    )

    private val topikSains = listOf(
        mapOf("icon" to "📏", "judul" to "Besaran & Satuan", "status" to "Selesai", "desc" to "Mengenal besaran pokok, besaran turunan, konversi satuan SI, dan angka penting."),
        mapOf("icon" to "🚗", "judul" to "Gerak Lurus", "status" to "Selesai", "desc" to "Menghitung jarak, perpindahan, kecepatan, dan percepatan pada GLB dan GLBB."),
        mapOf("icon" to "🍎", "judul" to "Hukum Newton", "status" to "Sedang belajar", "desc" to "Menerapkan hukum inersia, F = m·a, serta gaya aksi-reaksi pada benda."),
        mapOf("icon" to "🧬", "judul" to "Sel", "status" to "Terkunci", "desc" to "Struktur membran sel, nukleus, dan fungsi organel pada sel hewan dan tumbuhan."),
        mapOf("icon" to "⚛️", "judul" to "Struktur Atom", "status" to "Terkunci", "desc" to "Konfigurasi elektron, nomor atom, nomor massa, dan posisi unsur di tabel periodik."),
        mapOf("icon" to "🧪", "judul" to "Reaksi Kimia", "status" to "Terkunci", "desc" to "Penyetaraan persamaan reaksi dan konsep mol dalam stoikiometri.")
    )

    private val topikUtbk = listOf(
        mapOf("icon" to "🧩", "judul" to "Penalaran Umum", "status" to "Sedang belajar", "desc" to "Subtes TPS yang menguji logika deduktif, induktif, serta penalaran kuantitatif dasar."),
        mapOf("icon" to "🔣", "judul" to "Pengetahuan Kuantitatif", "status" to "Terkunci", "desc" to "Soal aljabar, geometri, statistika dasar, dan tipe kecukupan data UTBK."),
        mapOf("icon" to "📚", "judul" to "Pengetahuan Umum", "status" to "Terkunci", "desc" to "Kedalaman kosakata, hubungan antar makna kata, dan pemahaman wacana umum."),
        mapOf("icon" to "📝", "judul" to "Pemahaman Bacaan", "status" to "Terkunci", "desc" to "Kemampuan memahami isi bacaan, kelengkapan kalimat, dan kaidah penulisan."),
        mapOf("icon" to "🇮🇩", "judul" to "Literasi B. Indonesia", "status" to "Terkunci", "desc" to "Analisis mendalam teks sastra, sains populer, dan sosial humaniora."),
        mapOf("icon" to "🇬🇧", "judul" to "Literasi B. Inggris", "status" to "Terkunci", "desc" to "Pemahaman teks akademik bahasa Inggris tingkat menengah hingga lanjut.")
    )

    private var topikAktif = topikMatematika
    private var mapelAktif = "Matematika"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPetaBelajarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Peta Belajar"
            subtitle = null
            setDisplayHomeAsUpEnabled(false)
            setDisplayShowHomeEnabled(false)
        }

        val daftarKartu = listOf(
            binding.cvTopik1, binding.cvTopik2, binding.cvTopik3,
            binding.cvTopik4, binding.cvTopik5, binding.cvTopik6
        )

        binding.chipGroupMapel.setOnCheckedStateChangeListener { _, checkedIds ->
            when (checkedIds.firstOrNull()) {
                R.id.chipMatematika -> tampilkanTopik("Matematika", topikMatematika)
                R.id.chipLiterasi -> tampilkanTopik("Literasi", topikLiterasi)
                R.id.chipSains -> tampilkanTopik("Sains", topikSains)
                R.id.chipUtbk -> tampilkanTopik("Persiapan UTBK", topikUtbk)
            }
        }

        daftarKartu.forEachIndexed { index, kartu ->
            kartu.setOnClickListener {
                val topik = topikAktif[index]
                val status = topik["status"] ?: ""
                val judul = topik["judul"] ?: ""
                if (status == "Terkunci") {
                    Toast.makeText(
                        requireContext(),
                        "$judul masih terkunci, selesaikan topik sebelumnya dulu ya!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val intent = DetailActivity.createIntent(
                        context = requireContext(),
                        title = judul,
                        subtitle = "$mapelAktif · Status: $status",
                        category = "$mapelAktif · $status",
                        description = topik["desc"] ?: "",
                        points = "Panduan Belajar Topik:\n• Baca ringkasan konsep utama di atas\n• Catat rumus atau istilah penting ke Catatan Belajar\n• Selesaikan latihan 5 menit untuk membuka topik berikutnya"
                    )
                    startActivity(intent)
                }
            }
        }

        tampilkanTopik("Matematika", topikMatematika)
    }

    private fun tampilkanTopik(namaMapel: String, daftarTopik: List<Map<String, String>>) {
        mapelAktif = namaMapel
        topikAktif = daftarTopik

        val daftarKartu = listOf(
            binding.cvTopik1, binding.cvTopik2, binding.cvTopik3,
            binding.cvTopik4, binding.cvTopik5, binding.cvTopik6
        )
        val daftarIcon = listOf(
            binding.tvIcon1, binding.tvIcon2, binding.tvIcon3,
            binding.tvIcon4, binding.tvIcon5, binding.tvIcon6
        )
        val daftarJudul = listOf(
            binding.tvJudul1, binding.tvJudul2, binding.tvJudul3,
            binding.tvJudul4, binding.tvJudul5, binding.tvJudul6
        )
        val daftarStatus = listOf(
            binding.tvStatus1, binding.tvStatus2, binding.tvStatus3,
            binding.tvStatus4, binding.tvStatus5, binding.tvStatus6
        )

        for (i in daftarTopik.indices) {
            val topik = daftarTopik[i]
            daftarIcon[i].text = topik["icon"]
            daftarJudul[i].text = topik["judul"]
            aturStatus(daftarKartu[i], daftarStatus[i], topik["status"] ?: "")
        }

        val jumlahSelesai = daftarTopik.count { it["status"] == "Selesai" }
        binding.tvSummaryLabel.text = "Peta Belajar · $namaMapel"
        binding.tvSummaryValue.text = "$jumlahSelesai dari ${daftarTopik.size} Topik Selesai"
        binding.pbSummary.progress = jumlahSelesai * 100 / daftarTopik.size
    }

    private fun aturStatus(kartu: MaterialCardView, tvStatus: TextView, status: String) {
        val context = requireContext()
        when (status) {
            "Selesai" -> {
                kartu.setCardBackgroundColor(ContextCompat.getColor(context, R.color.oppi_mint))
                tvStatus.text = "✓ Selesai"
                tvStatus.setTextColor(ContextCompat.getColor(context, R.color.oppi_green))
                kartu.alpha = 1f
            }
            "Sedang belajar" -> {
                kartu.setCardBackgroundColor(ContextCompat.getColor(context, R.color.oppi_lavender))
                tvStatus.text = "▶ Sedang belajar"
                tvStatus.setTextColor(ContextCompat.getColor(context, R.color.oppi_primary))
                kartu.alpha = 1f
            }
            else -> {
                kartu.setCardBackgroundColor(ContextCompat.getColor(context, R.color.oppi_card))
                tvStatus.text = "🔒 Terkunci"
                tvStatus.setTextColor(ContextCompat.getColor(context, R.color.oppi_gray))
                kartu.alpha = 0.6f
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
