package com.example.appyuma.More

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class MoreTabsAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TabModulFragment()
            1 -> TabMateriFragment()
            2 -> TabTipsFragment()
            else -> throw IllegalStateException("Posisi tidak valid")
        }
    }
}
