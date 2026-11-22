package com.example.appyuma.Home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.example.appyuma.DetailActivity
import com.example.appyuma.LoginActivity
import com.example.appyuma.Message.tutorial.TutorialMessageActivity
import com.example.appyuma.R
import com.example.appyuma.WebViewActivity
import com.example.appyuma.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Beranda"
            subtitle = null
            setDisplayHomeAsUpEnabled(false)
            setDisplayShowHomeEnabled(false)
        }

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.home_toolbar_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_tutorial_home -> {
                        startActivity(Intent(requireContext(), TutorialMessageActivity::class.java))
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        val sharedPref = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val username = sharedPref.getString("username", "Pengguna")
        binding.tvWelcomeUser.text = "Halo, $username"

        binding.btnViewDetail.setOnClickListener {
            openPersamaanLinearDetail()
        }

        binding.cvProjectCard.setOnClickListener {
            openPersamaanLinearDetail()
        }

        binding.btnOpenSnpmb.setOnClickListener {
            startActivity(Intent(requireContext(), WebViewActivity::class.java))
        }

        binding.cvSnpmbPortal.setOnClickListener {
            startActivity(Intent(requireContext(), WebViewActivity::class.java))
        }

        binding.btnLogout.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Logout")
            builder.setMessage("Apakah Anda yakin ingin keluar?")

            builder.setPositiveButton("Ya") { dialog, _ ->
                val editor = sharedPref.edit()
                editor.clear()
                editor.apply()

                dialog.dismiss()

                val intent = Intent(requireContext(), LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                requireActivity().finish()
            }

            builder.setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss()
            }

            val alertDialog = builder.create()
            alertDialog.show()
        }
    }

    private fun openPersamaanLinearDetail() {
        val intent = DetailActivity.createIntent(
            context = requireContext(),
            title = "Persamaan Linear",
            subtitle = "Matematika · Kelas 11 · Progres 45%",
            category = "Matematika · Sedang Dipelajari",
            description = "Persamaan linear adalah persamaan aljabar di mana setiap suku hanya mengandung konstanta atau perkalian konstanta dengan variabel berpangkat satu. Materi ini sering muncul pada soal Pengetahuan Kuantitatif dan Penalaran Matematika UTBK.",
            points = "Poin Penting Materi:\n• Bentuk umum 1 variabel: ax + b = 0 -> x = -b/a\n• Bentuk umum 2 variabel (SPLDV): ax + by = c\n• Metode penyelesaian: substitusi, eliminasi, dan grafik\n• Latihan harian: selesaikan 5 soal cerita SPLDV"
        )
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
