package com.example.appyuma.More

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.appyuma.R
import com.example.appyuma.databinding.FragmentMoreBinding
import com.google.android.material.tabs.TabLayoutMediator

class MoreFragment : Fragment() {

    private var _binding: FragmentMoreBinding? = null
    private val binding get() = _binding!!

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

        val tabsAdapter = MoreTabsAdapter(this)
        binding.viewPager.adapter = tabsAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Modul Baca"
                    tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_book)
                }
                1 -> {
                    tab.text = "Materi"
                    tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_compass)
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                }
                2 -> {
                    tab.text = "Tips Belajar"
                    tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_info)
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                    badge.number = 3
                }
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
