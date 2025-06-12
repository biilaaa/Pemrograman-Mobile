package com.example.recycler_view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recycler_view.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        val title = arguments?.getString("EXTRA_TITLE")
        val photo = arguments?.getInt("EXTRA_PHOTO")
        val plot = arguments?.getString("EXTRA_PLOT")
        val year = arguments?.getString("EXTRA_YEAR")
        val cast = arguments?.getString("EXTRA_CAST")

        binding.tvName.text = title
        binding.tvPlot.text = plot
        binding.tvYear.text = "$year"
        binding.tvCast.text = "$cast"
        photo?.let { binding.imgItemPhoto.setImageResource(it) }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}