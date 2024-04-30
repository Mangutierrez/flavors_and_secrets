package com.mogu.flavorsandsecrets.ui.web

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mogu.flavorsandsecrets.MainActivity
import com.mogu.flavorsandsecrets.databinding.FragmentWebSearchBinding

class WebSearchFragment : Fragment() {

    private var _binding: FragmentWebSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWebSearchBinding.inflate(inflater, container, false)
        (activity as? MainActivity)?.appBarMain?.tvAppBarTitle?.text = "Web"
        (activity as? MainActivity)?.appBarMain?.tvAppBarSubtitle?.text = ""
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSearch.setOnClickListener {
            performSearch(binding.etSearch.text.toString())
        }
    }

    private fun performSearch(query: String) {
        if (query.isEmpty()) return
        if (query.startsWith("http://") || query.startsWith("https://")) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(query))
            startActivity(intent)
        } else {
            if (query.isNotEmpty()) {
                val intent =
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=$query"))
                startActivity(intent)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
