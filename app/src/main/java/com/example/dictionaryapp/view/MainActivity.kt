package com.example.dictionaryapp.view
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionaryapp.R
import layout.RecentWordAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var recentWordList: ArrayList<String>
    lateinit var recentWord : String
    var PREF_KEY : String = "RECENT_WORD_LIST"

    val pref: SharedPreferences by lazy {
        getPreferences(MODE_PRIVATE)
    }
    val prefEdit: SharedPreferences.Editor by lazy {
        pref.edit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText: EditText = findViewById(R.id.id_TextField)

        val pref: SharedPreferences by lazy {
            getPreferences(MODE_PRIVATE)
        }
        val prefEdit: SharedPreferences.Editor by lazy {
            pref.edit()
        }
        newRecyclerView = findViewById(R.id.id_recent_recyclerView)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)

        // Load recentWordList from SharedPreferences
        recentWordList = ArrayList(pref.getStringSet(PREF_KEY, emptySet()))
        newRecyclerView.adapter = RecentWordAdapter(recentWordList)

        editText.setOnEditorActionListener { _, actionId, event ->
//            if (actionId == EditorInfo.IME_ACTION_DONE) {
                recentWord = editText.text.toString()
                recentWordList.add(recentWord)

                prefEdit.putStringSet(PREF_KEY, recentWordList.toSet())
                prefEdit.apply() // Asynchronously save changes

                newRecyclerView.adapter?.notifyDataSetChanged()

                val intent = Intent(this, ResponseActivity::class.java)
                intent.putExtra("user_input", editText.text.toString())
                startActivity(intent)
                return@setOnEditorActionListener true
//            }
//            false
        }
    }

}


