package com.example.dictionaryapp.view

import DictionaryViewModel
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ProgressBar
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
import com.example.dictionaryapp.view.adapters.AntonymsAdapter
import com.example.dictionaryapp.view.adapters.DefinitionAdapter
import com.example.dictionaryapp.view.adapters.SynonymsAdapter

class ResponseActivity : AppCompatActivity() {

    private lateinit var viewModel: DictionaryViewModel
    private lateinit var definitionRecyclerView: RecyclerView
    private lateinit var synonymsRecyclerView: RecyclerView
    private lateinit var antonymsRecyclerView: RecyclerView

    private lateinit var progressBar: ProgressBar
    private lateinit var searchedWord: TextView
    private lateinit var phonetic: TextView
    private lateinit var definitionSize: TextView

    private var definitionList: ArrayList<String> = arrayListOf( "hello" ,"hi" , "how are you")


    fun initViews(){
        // All Views
         searchedWord = findViewById(R.id.id_searched_word)
         phonetic  = findViewById(R.id.id_phonetics)
         definitionSize  = findViewById(R.id.def_size)
        progressBar = findViewById(R.id.progressBar)

        definitionRecyclerView = findViewById(R.id.definition_recyclerView)
        definitionRecyclerView.layoutManager = LinearLayoutManager(this)
        definitionRecyclerView.setHasFixedSize(true)

        synonymsRecyclerView = findViewById(R.id.synonyms_RecyclerView)
        synonymsRecyclerView.layoutManager = LinearLayoutManager(this)
        synonymsRecyclerView.setHasFixedSize(true)

        antonymsRecyclerView = findViewById(R.id.antonyms_RecyclerView)
        antonymsRecyclerView.layoutManager = LinearLayoutManager(this)
        antonymsRecyclerView.setHasFixedSize(true)
    }

    fun updateUI(){
        findViewById<TextView>(R.id.id_definition_heading).setText("DEFINITION")
        findViewById<TextView>(R.id.synonyms_heading).setText("SYNONYMS")
        findViewById<TextView>(R.id.antonyms_heading).setText("ANTONYMS")
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.response_activity)

        val data = intent.getStringExtra("user_input")
        val word : String = data.toString()

        initViews()



        viewModel = ViewModelProvider(this).get(DictionaryViewModel::class.java)


        viewModel.loading.observe(this) { isLoading ->
            if (isLoading) {
                progressBar.visibility = View.VISIBLE
            } else {
                // Observe the LiveData and update the UI when data is available
                viewModel.dictionaryResponse.observe(this) { response ->
                    if (response != null) {
                        progressBar.visibility = View.GONE
                        updateUI()
                        searchedWord.text = word
                        phonetic.text = response[0]?.phonetic
                        definitionSize.text =
                            response[0]?.meanings?.get(0)?.definitions?.size.toString()
                        definitionList.clear()
//                definitionList.addAll(response[0].meanings.)
                        println(definitionList)
                        synonymsRecyclerView.adapter = response[0]?.meanings?.get(0)
                            ?.let { SynonymsAdapter(it?.synonyms?.subList(0,7) as List<String>) }
                        antonymsRecyclerView.adapter = response[0]?.meanings?.get(0)
                                ?.let { AntonymsAdapter(it?.antonyms?.subList(0,7) as List<String>) }

                        definitionRecyclerView.adapter = response[0]?.meanings?.get(0)?.let {
                            DefinitionAdapter(it.definitions)
                        }
                    } else { println("Empty Response")}
                }
            }
        }

        // Make the API call
        viewModel.fetchDictionaryData(word)

        val backButton : ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}










