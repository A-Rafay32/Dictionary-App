package com.example.dictionaryapp.view
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionaryapp.R
import layout.RecentWordAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var recentRecyclerView: RecyclerView
    private lateinit var recentWordList: ArrayList<String>
    private lateinit var editText: EditText
    lateinit var recentWord : String
    var PREF_KEY : String = "RECENT_WORD_LIST"

    val pref: SharedPreferences by lazy {
        getPreferences(MODE_PRIVATE)
    }
    val prefEdit: SharedPreferences.Editor by lazy {
        pref.edit()
    }

    fun initializeViews(){
        editText = findViewById(R.id.id_TextField)
        val pref: SharedPreferences by lazy {
            getPreferences(MODE_PRIVATE)
        }
        val prefEdit: SharedPreferences.Editor by lazy {
            pref.edit()
        }
        recentRecyclerView = findViewById(R.id.id_recent_recyclerView)
        recentRecyclerView.layoutManager = LinearLayoutManager(this)
        recentRecyclerView.setHasFixedSize(true)

        recentWordList = ArrayList(pref.getStringSet(PREF_KEY, emptySet()))
        recentRecyclerView.adapter = RecentWordAdapter(recentWordList)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()


        findViewById<ImageButton>(R.id.deleteButton).setOnClickListener{
            // Clear SharedPreferences or perform any other data update operation
            prefEdit.clear()
            prefEdit.commit()
            // Update recycleView
            recentWordList.clear()
            recentRecyclerView.adapter = RecentWordAdapter(recentWordList)
        }

        editText.setOnEditorActionListener { _, actionId, event ->
                recentWord = editText.text.toString()
                recentWordList.add(recentWord)

                prefEdit.putStringSet(PREF_KEY, recentWordList.toSet())
                prefEdit.apply() // Asynchronously save changes

                recentRecyclerView.adapter?.notifyDataSetChanged()

                //start response activity
                val intent = Intent(this, ResponseActivity::class.java)
                intent.putExtra("user_input", editText.text.toString())
                startActivity(intent)
                return@setOnEditorActionListener true
        }
    }
}


