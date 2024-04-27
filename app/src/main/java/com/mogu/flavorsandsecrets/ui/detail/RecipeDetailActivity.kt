package com.mogu.flavorsandsecrets.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.mogu.flavorsandsecrets.databinding.ActivityRecipeDetailBinding
import com.mogu.flavorsandsecrets.ui.home.Recipe

class RecipeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeDetailBinding
    private var recipeDetailData: Recipe? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recipeDetailData = intent.getParcelableExtra("RECIPE")
        binding.imgBackArrow.setOnClickListener {
            onBackPressed()
        }

        //  compartir la receta
        binding.btnShare.setOnClickListener {
            // Compartir la receta...
        }

        //  añadir a favoritos
        binding.btnAddToFavorites.setOnClickListener {
            // Añadir la receta a favoritos...
        }

        loadRecipeData()
    }

    private fun loadRecipeData() {
        binding.txtRecipeTitle.text = recipeDetailData?.title ?: ""
        binding.txtRecipeDescription.text = recipeDetailData?.description ?: ""
        binding.txtIngredients.text = recipeDetailData?.duration
        Glide.with(this)
            .load(recipeDetailData?.imageUrl)
            .into(binding.imgRecipe)
    }
}
