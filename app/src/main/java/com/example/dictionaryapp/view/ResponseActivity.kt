package com.example.dictionaryapp.view

import DictionaryViewModel
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dictionaryapp.services.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import retrofit2.Response
import java.io.IOException
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionaryapp.R
//import com.example.dictionaryapp.R.id.definition_recyclerView
import com.example.dictionaryapp.model.DictionaryModel
import com.example.dictionaryapp.view.adapters.DefinitionAdapter

class ResponseActivity : AppCompatActivity() {

    private lateinit var viewModel: DictionaryViewModel
    private lateinit var definitionRecyclerView: RecyclerView
    private lateinit var idDefinition: TextView
    private var definitionList: ArrayList<String> = arrayListOf( "hello" ,"hi" , "how are you")
    lateinit var definition : String

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.response_activity)

        val data = intent.getStringExtra("user_input")
        val word : String = data.toString()

        viewModel = ViewModelProvider(this).get(DictionaryViewModel::class.java)
        viewModel.fetchDictionaryData(word)



        definitionRecyclerView = findViewById(R.id.definition_recyclerView)
        definitionRecyclerView.layoutManager = LinearLayoutManager(this
        )
        definitionRecyclerView.setHasFixedSize(true)


        // Observe the LiveData and update the UI when data is available
        viewModel.dictionaryResponse.observe(this, { response ->
            if (response != null) {
                definitionList.clear()
                definitionList.addAll(response)
                println(definitionList)

                definitionRecyclerView.adapter = DefinitionAdapter(definitionList)
            } else {
                println("Empty Response")
            }
        })

        // Make the API call
        viewModel.fetchDictionaryData(word)
    }
}










