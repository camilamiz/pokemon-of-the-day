package com.camilamizu.pokemon_of_the_day

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnMainCatch.setOnClickListener{
            Toast.makeText(this, "A wild pokemon appears!", Toast.LENGTH_SHORT).show()
            val mIntentCatch = Intent(this, PokemonInfoActivity::class.java)
            startActivity(mIntentCatch)
        }

        btnMainSearch.setOnClickListener{
            val pokemonSearch = edtMainPokemon.text.toString().trim().toLowerCase()
            if (pokemonSearch.isEmpty()) {
                edtMainPokemon.error = "Type a pokemon name or number"
                edtMainPokemon.requestFocus()
            } else {
                Toast.makeText(this, "You've chosen $pokemonSearch!", Toast.LENGTH_SHORT).show()
                val mIntentSearch = Intent(this, PokemonInfoActivity::class.java)
                mIntentSearch.putExtra("INTENT_POKEMON_SEARCH", pokemonSearch)
                startActivity(mIntentSearch)
            }
        }


    }
}