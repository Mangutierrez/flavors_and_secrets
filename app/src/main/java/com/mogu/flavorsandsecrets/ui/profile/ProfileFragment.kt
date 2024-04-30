package com.mogu.flavorsandsecrets.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mogu.flavorsandsecrets.MainActivity
import com.mogu.flavorsandsecrets.R
import com.mogu.flavorsandsecrets.data.DataDummy
import com.mogu.flavorsandsecrets.databinding.FragmentProfileBinding
import com.mogu.flavorsandsecrets.ui.detail.RecipeDetailActivity
import com.mogu.flavorsandsecrets.ui.home.Recipe

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var favoriteAdapter: FavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        setupRecyclerView()
        (activity as? MainActivity)?.appBarMain?.tvAppBarTitle?.text = "Perfil"
        (activity as? MainActivity)?.appBarMain?.tvAppBarSubtitle?.text = ""
        return binding.root
    }

    private fun setupRecyclerView() {
        favoriteAdapter = FavoriteAdapter(getFavoriteItems())
        if (getFavoriteItems().isEmpty()){
            binding.rvFavorites.visibility = View.GONE
            binding.tvNoFavorites.visibility = View.VISIBLE
        } else {
            binding.rvFavorites.visibility = View.VISIBLE
            binding.tvNoFavorites.visibility = View.GONE
        }
        binding.rvFavorites.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = favoriteAdapter
        }
    }

    private fun getFavoriteItems(): List<Recipe> {
        return DataDummy.getFavoriteRecipes()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

class FavoriteAdapter(private val items: List<Recipe>) :
    RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.ivFavoriteImage)
        val textViewTitle: TextView = view.findViewById(R.id.tvFavoriteTitle)
        val textViewDescription: TextView = view.findViewById(R.id.tvFavoriteDescription)
        val heartIcon: ImageView = view.findViewById(R.id.ivHeartIcon)
        var layoutContainer = view.findViewById<View>(R.id.layoutContainer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = items[position]
        holder.textViewTitle.text = recipe.title
        holder.textViewDescription.text = recipe.description
        Glide.with(holder.itemView.context)
            .load(recipe.imageUrl)
            .into(holder.imageView)
        holder.heartIcon.setOnClickListener {
            DataDummy.removeFavoriteRecipe(recipe)
            notifyDataSetChanged()
        }
        holder.layoutContainer.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, RecipeDetailActivity::class.java).apply {
                putExtra("RECIPE", recipe)
            }
            context.startActivity(intent)
        }
    }


    override fun getItemCount(): Int = items.size
}
