package com.mogu.flavorsandsecrets.ui.video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mogu.flavorsandsecrets.R
import com.mogu.flavorsandsecrets.databinding.FragmentPhotoBinding
import com.mogu.flavorsandsecrets.databinding.FragmentVideoBinding

class VideosFragment : Fragment() {

    private var _binding: FragmentVideoBinding? = null
    private val binding get() = _binding!!
    private val videosAdapter by lazy { VideosAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVideoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // RecyclerView con un GridLayout para vista cuadriculada
        binding.rvVideos.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = videosAdapter
        }

        // Cargar datos en el adaptador
        videosAdapter.submitList(getVideos())
    }

    private fun getVideos(): List<String> {
        // Listado de las fotos
        return listOf(
            "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class VideosAdapter : RecyclerView.Adapter<VideosAdapter.PhotoViewHolder>() {

    private var videoUrls = emptyList<String>()

    fun submitList(urls: List<String>) {
        videoUrls = urls
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photoUrl = videoUrls[position]
        // Cargar imagen

    }

    override fun getItemCount() = videoUrls.size

    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgVideo: ImageView = itemView.findViewById(R.id.imgVideo)
    }
}
