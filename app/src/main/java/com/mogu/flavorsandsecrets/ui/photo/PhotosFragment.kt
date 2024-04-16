package com.mogu.flavorsandsecrets.ui.photo

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

class PhotosFragment : Fragment() {

    private var _binding: FragmentPhotoBinding? = null
    private val binding get() = _binding!!
    private val photosAdapter by lazy { PhotosAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // RecyclerView con un GridLayout para vista cuadriculada
        binding.rvPhotos.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = photosAdapter
        }

        // Cargar datos en el adaptador
        photosAdapter.submitList(getPhotos())
    }

    private fun getPhotos(): List<String> {
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

class PhotosAdapter : RecyclerView.Adapter<PhotosAdapter.PhotoViewHolder>() {

    private var photoUrls = emptyList<String>()

    fun submitList(urls: List<String>) {
        photoUrls = urls
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photoUrl = photoUrls[position]
        // Cargar imagen

    }

    override fun getItemCount() = photoUrls.size

    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.imgPhoto)
    }
}
