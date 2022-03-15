package com.briggittelew.wikistarswars

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PersonajesAdapter (private val results: List<String>):
    RecyclerView.Adapter<PersonajesViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonajesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PersonajesViewHolder(layoutInflater.inflate(R.layout.item_recycler,
        parent, false))
    }

    override fun onBindViewHolder(holder: PersonajesViewHolder, position: Int) {
        val item = results[position]
        holder.bind()
    }

    override fun getItemCount(): Int = results.size

}
/*class DogAdapter(private val images: List<String>) : RecyclerView.Adapter<DogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DogViewHolder(layoutInflater.inflate(R.layout.item_dog, parent, false))
    }

    override fun getItemCount(): Int = images.size


    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val item = images[position]
        holder.bind(item)
    }
}*/