//package com.example.dictionaryapp.view.fragments
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.viewModelScope
//import androidx.lifecycle.ViewModel
//import com.example.dictionaryapp.services.Api
//import com.example.dictionaryapp.model.DictionaryModel
//import kotlinx.coroutines.launch
//
//enum class ApiStatus { LOADING, ERROR, DONE }
//
//class OverviewViewModel( word :String )  : ViewModel(){
//    // The internal MutableLiveData that stores the status of the most recent request
//    private val _status = MutableLiveData<ApiStatus>()
//    // The external immutable LiveData for the request status
//    val status: LiveData<ApiStatus> = _status
////    private val _dictionary = MutableLiveData<List<DictionaryModel>>()
////    val dictionary: LiveData<List<DictionaryModel>> = _dictionary
//    private val _dictionary = MutableLiveData<List<DictionaryModel>>()
//    val dictionary: LiveData<List<DictionaryModel>> = _dictionary
//
//
//    /**
//     * Call getMarsPhotos() on init so we can display status immediately.
//     */
//    init {
//        getDictionary(word)
//    }
//    private fun getDictionary(word : String){
//        viewModelScope.launch {
//            _status.value = ApiStatus.LOADING
//            try {
//                _dictionary.value = Api.retrofitServiceMoshi.getWordDictionary(word).execute().body()
//                _status.value = ApiStatus.DONE
//            } catch (e: Exception) {
//                _status.value = ApiStatus.ERROR
//                _dictionary.value = listOf()
//            }
//        }
//    }
//
//}