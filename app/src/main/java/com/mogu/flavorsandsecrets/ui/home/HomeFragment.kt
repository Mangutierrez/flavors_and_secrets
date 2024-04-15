package com.mogu.flavorsandsecrets.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mogu.flavorsandsecrets.R
import com.mogu.flavorsandsecrets.ui.detail.RecipeDetailActivity

class HomeFragment : Fragment() {

    private lateinit var rvPopularItems: RecyclerView
    private lateinit var rvOtherItems: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        rvPopularItems = view.findViewById(R.id.rvPopularItems)
        rvOtherItems = view.findViewById(R.id.rvOtherItems)

        // Configuración de RecyclerView
        rvPopularItems.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rvPopularItems.adapter = RecipeAdapter(getPopularItems())

        // Configuración de RecyclerView
        rvOtherItems.layoutManager = LinearLayoutManager(activity)
        rvOtherItems.adapter = RecipeAdapter(getOtherItems())

        return view
    }

    // Funciones para obtener los datos
    private fun getPopularItems(): List<Recipe> {
        return listOf(
            Recipe(1, "Título 1", "Descripción 1", ""),
            Recipe(2, "Título 2", "Descripción 2", ""),
            Recipe(3, "Título 3", "Descripción 3", "")
        )
    }

    private fun getOtherItems(): List<Recipe> {
        return listOf(
            Recipe(4, "Título 4", "Descripción 4", ""),
            Recipe(5, "Título 5", "Descripción 5", ""),
            Recipe(6, "Título 6", "Descripción 6", "")
        )
    }

}


class RecipeAdapter(private val items: List<Recipe>) :
    RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.ivRecipeImage)
        val textViewTitle: TextView = view.findViewById(R.id.tvRecipeTitle)
        val textViewDescription: TextView = view.findViewById(R.id.tvRecipeDescription)
        val layoutContainer: LinearLayout = view.findViewById(R.id.layoutContainer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = items[position]

        holder.textViewTitle.text = recipe.title
        holder.textViewDescription.text = recipe.description
        // TODO Cargar imagen en image imageView
        holder.layoutContainer.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, RecipeDetailActivity::class.java)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }
}

data class Recipe(
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String
)
