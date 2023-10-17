import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dictionaryapp.model.DictionaryModel
import com.example.dictionaryapp.services.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

enum class ApiStatus { LOADING, ERROR, DONE }
class DictionaryViewModel : ViewModel() {

    // LiveData to hold the API response data
    private val _dictionaryResponse = MutableLiveData<List<DictionaryModel?>>()
    val dictionaryResponse: MutableLiveData<List<DictionaryModel?>> = _dictionaryResponse

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<ApiStatus>()
    // The external immutable LiveData for the request status
    val status: LiveData<ApiStatus> = _status

    // Function to make the API call and update the LiveData
    fun fetchDictionaryData(word: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _status.postValue(ApiStatus.LOADING)

                val response: Response<List<DictionaryModel>> = Api.retrofitServiceMoshi.getWordDictionary(word).execute()

                if (response.isSuccessful) {

                    _status.postValue(ApiStatus.DONE)

                    println(response.message())
                    println(response.body())
                    println(response.code())
                    println(response.raw())
                    val body = response.body()
                    if (body != null) {
                        // Update the LiveData with the response data
                        println(body[0].toMap())
                        _dictionaryResponse.postValue(body)
                    }

                }
                println(response.message())
                println(response.body())
                println(response.code())
                println(response.raw())// Handle other cases like errors, etc.
            } catch (e: Exception) {
                _status.postValue(ApiStatus.ERROR)
                println(e.printStackTrace())
                // Handle network errors
            }


        }
    }
}

