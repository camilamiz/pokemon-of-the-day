package com.camilamizu.pokemon_of_the_day

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URL

class PokemonInfoActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_pokemon_info)

      val pokemonNumber = (0..894).random()
      val pokemonInfoText : TextView = findViewById(R.id.txvPokemonInfoText)
      val url = "https://pokeapi.co/api/v2/pokemon/$pokemonNumber/"

      val queue = Volley.newRequestQueue(this)

      val stringRequest = StringRequest(
        Request.Method.GET, url,
        Response.Listener<String> {response ->
          pokemonInfoText.text = "Response is: ${response.substring(0, 500)}"
        },
        Response.ErrorListener { pokemonInfoText.text = "That didn't work!" })

      queue.add(stringRequest)


  }
}