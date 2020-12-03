package com.camilamizu.pokemon_of_the_day

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
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
          pokemonInfoID.text = JSONObject(response).get("id").toString()
          pokemonInfoName.text = JSONObject(response).get("name").toString().capitalize()
          pokemonInfoHeight.text = JSONObject(response).get("height").toString()
          pokemonInfoWeight.text = JSONObject(response).get("weight").toString()
//          val pokemonImage = "${JSONObject(response).get("sprites").get("front_default")}"

          val types = JSONObject(response).getJSONArray("types")
          val allTypes : MutableList<String> = ArrayList()
          for (i in 0 until types.length()) {
            val elementType = types.getJSONObject(i).get("type")
            allTypes.add("$elementType")
          }

          pokemonInfoTypes.text = "$allTypes"


//          pokemonInfoTypes.text = "${JSONObject(response).get("types")}"
        },
        Response.ErrorListener { pokemonInfoText.text = "That didn't work!" })

      queue.add(stringRequest)


  }
}