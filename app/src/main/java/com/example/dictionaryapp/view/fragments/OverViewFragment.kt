//package com.example.dictionaryapp.fragments
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.viewModels
//import com.example.dictionaryapp.R
//import com.example.dictionaryapp.adapters.DefinitionAdapter
//import com.example.dictionaryapp.databinding.DefinitionFragmentBinding
//import com.example.dictionaryapp.network.Api
//import com.example.dictionaryapp.network.ApiService
//
//class OverViewFragment(word : String) : Fragment(){
//
//    private val viewModel: OverviewViewModel by viewModels()
//
//    private val response : List<String> = Api.retrofitServiceScalar.getWordString(word).execute().body() as List<String>
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        val binding = DefinitionFragmentBinding.inflate(inflater)
//        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
//        binding.lifecycleOwner = this
//        // Giving the binding access to the OverviewViewModel
//        binding.viewModel = viewModel
//        // Sets the adapter of the photosGrid RecyclerView
//         binding.photosGrid.adapter = DefinitionAdapter(response as List<Char>)
//        return binding.root
//    }
//}