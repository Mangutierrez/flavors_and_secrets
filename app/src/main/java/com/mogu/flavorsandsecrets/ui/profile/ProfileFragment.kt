package com.mogu.flavorsandsecrets.ui.profile
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mogu.flavorsandsecrets.R
import com.mogu.flavorsandsecrets.databinding.FragmentProfileBinding

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
        return binding.root
    }

    private fun setupRecyclerView() {
        favoriteAdapter = FavoriteAdapter(getFavoriteItems())
        binding.rvFavorites.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = favoriteAdapter
        }
    }

    private fun getFavoriteItems(): List<FavoriteItem> {
        return listOf(
            FavoriteItem("Sopa", "Descripción de la sopa", ""),
            FavoriteItem("Carne", "Descripción de la carne", ""),
            FavoriteItem("Ensalada", "Descripción de la ensalada", ""),
            FavoriteItem("Hamburguesa", "Descripción de la hamburg", ""),
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

data class FavoriteItem(
    val title: String,
    val description: String,
    val imageResId: String
)

class FavoriteAdapter(private val items: List<FavoriteItem>) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.ivFavoriteImage)
        val textViewTitle: TextView = view.findViewById(R.id.tvFavoriteTitle)
        val textViewDescription: TextView = view.findViewById(R.id.tvFavoriteDescription)
        val heartIcon: ImageView = view.findViewById(R.id.ivHeartIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val favorite = items[position]
        holder.textViewTitle.text = favorite.title
        holder.textViewDescription.text = favorite.description
        // TODO: Cargar imagen
    }

    override fun getItemCount(): Int = items.size
}
