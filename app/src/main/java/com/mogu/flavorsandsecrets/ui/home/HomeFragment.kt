package com.mogu.flavorsandsecrets.ui.home

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mogu.flavorsandsecrets.R
import com.mogu.flavorsandsecrets.data.DataDummy
import com.mogu.flavorsandsecrets.ui.detail.RecipeDetailActivity
import kotlinx.parcelize.Parcelize

class HomeFragment : Fragment() {
    private var fragmentTitle: String = "Flavors & Secrets"
    private var fragmentSubTitle: String = "Las mejores recetas"
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
        rvPopularItems.adapter = RecipeAdapter(getPopularRecipes(), isHorizontal = true)

        // Configuración de RecyclerView
        rvOtherItems.layoutManager = LinearLayoutManager(activity)
        rvOtherItems.adapter = RecipeAdapter(getOtherRecipes(), isHorizontal = false)

        // (activity as? MainActivity)?.appBarMain?.tvAppBarTitle?.text = fragmentTitle
        // (activity as? MainActivity)?.appBarMain?.tvAppBarSubtitle?.text = fragmentSubTitle
        return view
    }

    private fun updateToolbarTitle() {

    }

    // Funciones para obtener los datos
    private fun getPopularRecipes(): List<Recipe> {
        return DataDummy.getPopularRecipes()
    }

    private fun getOtherRecipes(): List<Recipe> {
        return DataDummy.getOtherRecipes()
    }

    override fun onResume() {
        super.onResume()
        (activity as? AppCompatActivity)?.supportActionBar?.title = fragmentTitle
    }

}


class RecipeAdapter(private val items: List<Recipe>, private val isHorizontal: Boolean = false) :
    RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.ivRecipeImage)
        val textViewTitle: TextView = view.findViewById(R.id.tvRecipeTitle)
        val textViewDescription: TextView = view.findViewById(R.id.tvRecipeDescription)
        val layoutContainer: CardView = view.findViewById(R.id.layoutContainer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var layout = R.layout.item_recipe_vertical
        if (isHorizontal) {
            layout = R.layout.item_recipe_horizontal
        }

        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = items[position]
        if (!isHorizontal) {
            holder.layoutContainer.cardElevation = 0F
        }
        holder.textViewTitle.text = recipe.title
        holder.textViewDescription.text = recipe.description
        Glide.with(holder.itemView.context)
            .load(recipe.imageUrl)
            .centerCrop()
            .into(holder.imageView)
        holder.layoutContainer.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, RecipeDetailActivity::class.java).apply {
                putExtra("RECIPE", recipe)
            }
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }
}

@Parcelize
data class Recipe(
    val id: Int,
    val title: String,
    val description: String,
    val duration: String,
    val imageUrl: String,
) : Parcelable
