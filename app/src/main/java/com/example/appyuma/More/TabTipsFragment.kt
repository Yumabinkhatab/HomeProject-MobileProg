package com.example.appyuma.More

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.appyuma.DetailActivity
import com.example.appyuma.Message.tutorial.TutorialMessageActivity
import com.example.appyuma.databinding.FragmentTabTipsBinding

class TabTipsFragment : Fragment() {

    private var _binding: FragmentTabTipsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabTipsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAboutDetail.setOnClickListener {
            startActivity(Intent(requireContext(), DetailActivity::class.java))
        }

        binding.btnOpenTutorial.setOnClickListener {
            startActivity(Intent(requireContext(), TutorialMessageActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
