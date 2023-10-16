package com.example.dictionaryapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionaryapp.R
import com.example.dictionaryapp.model.DefinitionModel

class DefinitionAdapter(private val response : List<DefinitionModel>) : RecyclerView.Adapter<DefinitionHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefinitionHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.definition_view,parent , false)
        return DefinitionHolder(itemView)
    }

    override fun onBindViewHolder(holder: DefinitionHolder, position: Int) {
        holder.definition.text = response[position].definition
        holder.definitionNum.text = (position + 1).toString()
    }

    override fun getItemCount(): Int {
        return response.size
    }
}
class DefinitionHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    val definition : TextView = itemView.findViewById(R.id.textView3)
    val definitionNum : TextView = itemView.findViewById(R.id.definition_num)

}