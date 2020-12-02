package com.camilamizu.pokemon_of_the_day

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class PokemonInfoActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_pokemon_info)

      val pokemonNumber = (0..899).random()
      val pokemonInfoText : TextView = findViewById(R.id.txvPokemonInfoText)
      val pokemonInfoID : TextView = findViewById(R.id.txvPokemonInfoID)
      val pokemonInfoName : TextView = findViewById(R.id.txvPokemonInfoName)
      val pokemonInfoHeight : TextView = findViewById(R.id.txvPokemonInfoHeight)
      val pokemonInfoWeight : TextView = findViewById(R.id.txvPokemonInfoWeight)
      val pokemonInfoTypes : TextView = findViewById(R.id.txvPokemonInfoTypes)
      val url = "https://pokeapi.co/api/v2/pokemon/$pokemonNumber/"

      val queue = Volley.newRequestQueue(this)

      val stringRequest = StringRequest(
        Request.Method.GET, url,
        Response.Listener<String> {response ->
          pokemonInfoID.text = "$pokemonNumber"
          pokemonInfoName.text = "${JSONObject(response).get("name")}"
          pokemonInfoHeight.text = "${JSONObject(response).get("height")}"
          pokemonInfoWeight.text = "${JSONObject(response).get("weight")}"


//          val types = JSONObject(response).get("types")
//          for (i in 0 until arrayOf(types).size) {
//            pokemonInfoTypes.text = "${types(i)}"
//          }


//          pokemonInfoTypes.text = "${JSONObject(response).get("types")}"
        },
        Response.ErrorListener { pokemonInfoText.text = "That didn't work!" })

      queue.add(stringRequest)


  }
}