package com.camilamizu.pokemon_of_the_day

import android.content.Context
import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_pokemon_info.*
import org.json.JSONArray
import org.json.JSONObject
import java.lang.reflect.Type

class PokemonInfoActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_pokemon_info)

      val pokemonSearch = intent.getStringExtra("INTENT_POKEMON_SEARCH")

      val pokemonInfoText : TextView = findViewById(R.id.txvPokemonInfoText)
      val pokemonInfoID : TextView = findViewById(R.id.txvPokemonInfoID)
      val pokemonInfoName : TextView = findViewById(R.id.txvPokemonInfoName)
      val pokemonInfoHeight : TextView = findViewById(R.id.txvPokemonInfoHeight)
      val pokemonInfoWeight : TextView = findViewById(R.id.txvPokemonInfoWeight)
      val pokemonInfoTypes : TextView = findViewById(R.id.txvPokemonInfoTypes)

      val pokemonReference = if (pokemonSearch != null) {
        pokemonSearch
      } else {
        (0..899).random().toString()
      }
      val url = "https://pokeapi.co/api/v2/pokemon/$pokemonReference/"

      val queue = Volley.newRequestQueue(this)

      val stringRequest = StringRequest(
        Request.Method.GET, url,
        Response.Listener<String> {response ->
          val jObject = JSONObject(response)
          pokemonInfoID.text = jObject.get("id").toString()
          pokemonInfoName.text = jObject.get("name").toString().capitalize()
          pokemonInfoHeight.text = jObject.get("height").toString()
          pokemonInfoWeight.text = jObject.get("weight").toString()

          val pokemonImageURL = jObject.getJSONObject("sprites").getString("front_default")
          Picasso.get().load(pokemonImageURL).into(imvPokemonInfoAvatar)

          val allTypes : MutableList<String> = ArrayList()
          val types = jObject.getJSONArray("types")
          for (i in 0 until types.length()) {
            val elementType = types.getJSONObject(i).getJSONObject("type").getString("name")
            allTypes.add("$elementType")
          }
          pokemonInfoTypes.text = "${allTypes.joinToString()}"
        },
        Response.ErrorListener { pokemonInfoText.text = "That didn't work!" })

      queue.add(stringRequest)

  }
}