package com.example.dictionaryapp.view.adapters



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionaryapp.R
import com.example.dictionaryapp.model.DefinitionModel

class SynonymsAdapter(private val response : List<String>) : RecyclerView.Adapter<SynonymsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SynonymsHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.synonyms_view,parent , false)
        return SynonymsHolder(itemView)
    }

    override fun onBindViewHolder(holder: SynonymsHolder, position: Int) {
        holder.synonyms.text = response[position].toString()

    }

    override fun getItemCount(): Int {
        return response.size
    }
}
class SynonymsHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    val synonyms : TextView = itemView.findViewById(R.id.synonyms_view)
}