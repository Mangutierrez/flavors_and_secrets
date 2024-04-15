package com.mogu.flavorsandsecrets.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mogu.flavorsandsecrets.databinding.ActivityRecipeDetailBinding

class RecipeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        binding.txtRecipeTitle.text = "Batido de fresa y espinacas"
        binding.txtRecipeDescription.text = "Receta saludable de desayuno con frutas y verduras."
        binding.txtIngredients.text = "Fresa \nPlátano \nEspinacas \nLeche \nYogur \nMiel"
    }
}
