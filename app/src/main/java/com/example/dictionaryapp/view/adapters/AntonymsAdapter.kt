package com.example.dictionaryapp.view.adapters



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionaryapp.R
import com.example.dictionaryapp.model.DefinitionModel

class AntonymsAdapter(private val response : List<String>) : RecyclerView.Adapter<AntonymsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AntonymsHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.antonyms_view,parent , false)
        return AntonymsHolder(itemView)
    }

    override fun onBindViewHolder(holder: AntonymsHolder, position: Int) {
        holder.antonyms.text = response[position].toString()

    }

    override fun getItemCount(): Int {
        return response.size
    }
}
class AntonymsHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    val antonyms : TextView = itemView.findViewById(R.id.antonyms_view)
}