package com.example.dictionaryapp.view

import DictionaryViewModel
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
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
    private lateinit var searchedWord: TextView
    private lateinit var phonetic: TextView
    private lateinit var definitionSize: TextView

    private var definitionList: ArrayList<String> = arrayListOf( "hello" ,"hi" , "how are you")
    lateinit var definition : String

    fun initViews(){
        // All Views
         searchedWord = findViewById(R.id.id_searched_word)
         phonetic  = findViewById(R.id.id_phonetics)
         definitionSize  = findViewById(R.id.def_size)
    }







    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.response_activity)

        val data = intent.getStringExtra("user_input")
        val word : String = data.toString()

        initViews()
        searchedWord.text = word


        viewModel = ViewModelProvider(this).get(DictionaryViewModel::class.java)
        viewModel.fetchDictionaryData(word)

        definitionRecyclerView = findViewById(R.id.definition_recyclerView)
        definitionRecyclerView.layoutManager = LinearLayoutManager(this
        )
        definitionRecyclerView.setHasFixedSize(true)
//        println(viewModel.dictionaryResponse.value)

        // Observe the LiveData and update the UI when data is available
        viewModel.dictionaryResponse.observe(this, { response ->
            if (response != null) {
                phonetic.text = response[0]?.phonetic
                definitionSize.text = response[0]?.meanings?.get(0)?.definitions?.size.toString()
                definitionList.clear()
//                definitionList.addAll(response[0].meanings.)
                println(definitionList)

                definitionRecyclerView.adapter = response[0]?.meanings?.get(0)?.let {
                    DefinitionAdapter(
                        it.definitions)
                }
            } else {
                println("Empty Response")
            }
        })

        // Make the API call
        viewModel.fetchDictionaryData(word)

        val backButton : ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}










