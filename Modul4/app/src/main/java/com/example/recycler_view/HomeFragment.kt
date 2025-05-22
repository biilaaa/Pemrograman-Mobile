package com.example.recycler_view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recycler_view.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SeriesViewModel by viewModels { SeriesViewModelFactory() }

    private lateinit var seriesAdapter: ListSeriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        seriesAdapter = ListSeriesAdapter(
            emptyList(),
            onWikiClick = { link ->
                Log.d("HomeFragment", "Tombol Intent (WeTV) diklik: $link")
                val uri = Uri.parse(link)
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            },
            onDetailClick = { series ->
                Log.d("HomeFragment", "Tombol Detail diklik: ${series.title}")
                viewModel.selectSeries(series)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frame_container, DetailFragment())
                    .addToBackStack(null)
                    .commit()
            }
        )

        binding.rvDrama.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = seriesAdapter
        }

        lifecycleScope.launch {
            viewModel.seriesList.collectLatest { updatedList ->
                seriesAdapter.updateList(updatedList)
            }
        }

        if (viewModel.seriesList.value.isEmpty()) {
            viewModel.setSeriesList(getSeriesList())
        }
    }

    private fun getSeriesList(): List<Series> {
        val dataTitle = resources.getStringArray(R.array.data_name)
        val dataLink = resources.getStringArray(R.array.data_link)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataPlot = resources.getStringArray(R.array.data_plot)
        val dataYear = resources.getStringArray(R.array.data_year)
        val dataCast = resources.getStringArray(R.array.data_cast)

        val list = ArrayList<Series>()
        for (i in dataTitle.indices) {
            val series = Series(
                dataTitle[i],
                dataLink[i],
                dataPhoto.getResourceId(i, -1),
                dataPlot[i],
                dataYear[i],
                dataCast[i]
            )
            list.add(series)
        }
        dataPhoto.recycle()
        return list
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}