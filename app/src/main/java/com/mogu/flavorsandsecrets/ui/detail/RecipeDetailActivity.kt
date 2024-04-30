package com.mogu.flavorsandsecrets.ui.detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.mogu.flavorsandsecrets.data.DataDummy
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
            // Compartir la receta
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(
                    Intent.EXTRA_TEXT,
                    recipeDetailData?.title + "\n" + recipeDetailData?.description
                )
                type = "text/plain"
            }
            startActivity(Intent.createChooser(sendIntent, null))
        }

        //  añadir a favoritos
        binding.btnAddToFavorites.setOnClickListener {
            if (DataDummy.getFavoriteRecipes().contains(recipeDetailData)) {
                recipeDetailData?.let { recipe -> DataDummy.removeFavoriteRecipe(recipe) }
                binding.btnAddToFavorites.text = "Agregar a favoritos"
            } else {
                recipeDetailData?.let { recipe -> DataDummy.addFavoriteRecipe(recipe) }
                binding.btnAddToFavorites.text = "Eliminar de favoritos"
            }
        }

        loadRecipeData()
    }

    private fun loadRecipeData() {
        if (DataDummy.getFavoriteRecipes().contains(recipeDetailData)) {
            binding.btnAddToFavorites.text = "Eliminar de favoritos"
        } else {
            binding.btnAddToFavorites.text = "Agregar a favoritos"
        }
        binding.txtRecipeTitle.text = recipeDetailData?.title ?: ""
        binding.txtRecipeDescription.text = recipeDetailData?.description ?: ""
        binding.txtIngredients.text = recipeDetailData?.duration
        Glide.with(this)
            .load(recipeDetailData?.imageUrl)
            .into(binding.imgRecipe)

    }
}
