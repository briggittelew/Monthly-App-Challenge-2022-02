package com.briggittelew.wikistarswars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.briggittelew.wikistarswars.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var b: ActivityMainBinding
    private lateinit var adapter: PersonajesAdapter
    private val resultsMutable = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
        initRecyclerView()
        b.svSearch.setOnQueryTextListener(this)
    }

    //Gestionar adapter
    private fun initRecyclerView(){
        adapter = PersonajesAdapter(resultsMutable)
        b.rvResult.layoutManager = LinearLayoutManager(this)
        b.rvResult.adapter = adapter
    }

    //Instancia retrofit
    private fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://swapi.dev/api/people/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchByName(query: String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getPersonajes("?search=$query")
            val callResponse = call.body()
            runOnUiThread {
                showMessage(call.toString())
                showMessage("Buscando...")
                if (call.isSuccessful) {
                    //Mostrar Recyclerview
                    showMessage("Mostrando resultados")
                    val resulted = callResponse?.count ?: emptyList()
                    //resulted.forEach { showMessage(it) }
                    showMessage(resulted.size.toString())
                    resultsMutable.clear()
                    resultsMutable.addAll(resulted)
                    adapter.notifyDataSetChanged()
                } else {
                    //mostrar error
                    showMessage("Ha ocurrido un error")
                }
                hideKeyboard()
            }
        }
        hideKeyboard()
    }

    private fun showMessage(text: String){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    //Esconder el teclado
    private fun hideKeyboard(){
        val input = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        input.hideSoftInputFromWindow(b.parentLayout.windowToken,0)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty())
            searchByName(query.lowercase())
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
    /* private fun searching(search: SearchView) {
         override fun onQueryTextSubmit(query: String?): Boolean {
             if (!query.isNullOrEmpty()) {
                 searchByName()
             }
             return true
         }

         override fun onQueryTextChange(newText: String?): Boolean {
             return true
         }
}*/
}