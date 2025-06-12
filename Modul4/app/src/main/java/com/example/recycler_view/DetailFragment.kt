package com.example.recycler_view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.recycler_view.databinding.FragmentDetailBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SeriesViewModel by viewModels { SeriesViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.selectedSeries.collectLatest { series ->
                series?.let {
                    Log.d("DetailFragment", "Menampilkan detail untuk: ${series.title}")
                    binding.tvName.text = series.title
                    binding.tvPlot.text = series.plot
                    binding.tvYear.text = series.year
                    binding.tvCast.text = series.cast
                    binding.imgItemPhoto.setImageResource(series.photo)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}