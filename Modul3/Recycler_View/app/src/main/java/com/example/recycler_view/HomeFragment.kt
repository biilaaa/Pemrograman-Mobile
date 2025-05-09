package com.example.recycler_view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recycler_view.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvDrama.layoutManager = LinearLayoutManager(requireContext())
        binding.rvDrama.setHasFixedSize(true)

        val dramaAdapter = DramaAdapter(getDramaList(),
            onWikiClick = { link ->
                val uri = Uri.parse(link)
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            },
            onDetailClick = { title, photo, plot, year, cast ->
                val detailFragment = DetailFragment().apply {
                    arguments = Bundle().apply {
                        putString("EXTRA_TITLE", title)
                        putInt("EXTRA_PHOTO", photo)
                        putString("EXTRA_PLOT", plot)
                        putString("EXTRA_YEAR", year)
                        putString("EXTRA_CAST", cast)
                    }
                }
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frame_container, detailFragment)
                    .addToBackStack(null)
                    .commit()
            })

        binding.rvDrama.adapter = dramaAdapter
    }

    private fun getDramaList(): ArrayList<Series> {
        val dataTitle = resources.getStringArray(R.array.data_name)
        val dataLink = resources.getStringArray(R.array.data_link)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataPlot = resources.getStringArray(R.array.data_plot)
        val dataYear = resources.getStringArray(R.array.data_year)
        val dataCast = resources.getStringArray(R.array.data_cast)
        val listDrama = ArrayList<Series>()
        for (i in dataTitle.indices) {
            val drama = Series(
                dataTitle[i],
                dataLink[i],
                dataPhoto.getResourceId(i, -1),
                dataPlot[i],
                dataYear[i],
                dataCast[i]
            )
            listDrama.add(drama)
        }
        dataPhoto.recycle()
        return listDrama
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}