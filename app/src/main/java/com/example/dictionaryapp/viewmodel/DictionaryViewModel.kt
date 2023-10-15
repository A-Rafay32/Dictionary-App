import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dictionaryapp.services.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class DictionaryViewModel : ViewModel() {

    // LiveData to hold the API response data
    private val _dictionaryResponse = MutableLiveData<List<String>>()
    val dictionaryResponse: LiveData<List<String>> = _dictionaryResponse



    // Function to make the API call and update the LiveData
    fun fetchDictionaryData(word: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response: Response<List<String>> = Api.retrofitServiceScalar.getWordString(word).execute()

                if (response.isSuccessful) {
                    println(response.message())
                    println(response.body())
                    println(response.code())
                    println(response.raw())
                    val body = response.body()
                    if (body != null) {
                        // Update the LiveData with the response data
                        println(body)
                        _dictionaryResponse.postValue(body as List<String>)
                    }
                }
                println(response.message())
                println(response.body())
                println(response.code())
                println(response.raw())// Handle other cases like errors, etc.
            } catch (e: Exception) {
                println(e.printStackTrace())
                // Handle network errors
            }
        }
    }
}
