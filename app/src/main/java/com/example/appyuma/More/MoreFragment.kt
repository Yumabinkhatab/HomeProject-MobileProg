package com.example.appyuma.More

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.appyuma.R
import com.example.appyuma.data.AppDatabase
import com.example.appyuma.databinding.FragmentMoreBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch

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
                    tab.text = "Modul"
                    tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_book)
                }
                1 -> {
                    tab.text = "Materi"
                    tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_compass)
                }
                2 -> {
                    tab.text = "Tips"
                    tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_info)
                }
                3 -> {
                    tab.text = "Catatan"
                    tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_notes)
                }
            }
        }.attach()
    }

    override fun onResume() {
        super.onResume()
        updateNoteBadge()
    }

    fun updateNoteBadge(count: Int? = null) {
        if (_binding == null) return
        if (count != null) {
            applyNoteBadge(count)
            return
        }
        viewLifecycleOwner.lifecycleScope.launch {
            val total = AppDatabase.getInstance(requireContext()).noteDao().getAll().size
            if (_binding == null) return@launch
            applyNoteBadge(total)
        }
    }

    private fun applyNoteBadge(total: Int) {
        val tab = binding.tabLayout.getTabAt(3) ?: return
        if (total > 0) {
            val badge = tab.getOrCreateBadge()
            badge.isVisible = true
            badge.number = total
        } else {
            tab.removeBadge()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
