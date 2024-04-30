package com.mogu.flavorsandsecrets.ui.photo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mogu.flavorsandsecrets.MainActivity
import com.mogu.flavorsandsecrets.R
import com.mogu.flavorsandsecrets.data.DataDummy
import com.mogu.flavorsandsecrets.databinding.FragmentPhotoBinding
import com.mogu.flavorsandsecrets.ui.detail.RecipeDetailActivity
import com.mogu.flavorsandsecrets.ui.home.Recipe

class PhotosFragment : Fragment() {

    private var _binding: FragmentPhotoBinding? = null
    private val binding get() = _binding!!
    private val photosAdapter by lazy { PhotosAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoBinding.inflate(inflater, container, false)
        (activity as? MainActivity)?.appBarMain?.tvAppBarTitle?.text = "Fotos"
        (activity as? MainActivity)?.appBarMain?.tvAppBarSubtitle?.text = ""
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

    private fun getPhotos(): List<Recipe> {
         return DataDummy.getAllRecipes()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class PhotosAdapter : RecyclerView.Adapter<PhotosAdapter.PhotoViewHolder>() {

    private var photoRecipeList = emptyList<Recipe>()

    fun submitList(urls: List<Recipe>) {
        photoRecipeList = urls
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val recipe = photoRecipeList[position]
        // Cargar imagen
        Glide.with(holder.itemView.context)
            .load(recipe.imageUrl)
            .centerCrop()
            .into(holder.imgPhoto)
        holder.itemView.rootView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, RecipeDetailActivity::class.java).apply {
                putExtra("RECIPE", recipe)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = photoRecipeList.size

    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.imgPhoto)
    }
}
