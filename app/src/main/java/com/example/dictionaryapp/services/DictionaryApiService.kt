package com.example.dictionaryapp.services
import com.example.dictionaryapp.model.DictionaryModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


import retrofit2.http.GET
import retrofit2.http.Path



private const val BASE_URL =
    "https://api.dictionaryapi.dev/api/v2/"


val logging = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY // You can use other levels like BASIC or HEADERS for less detailed logging
}

val httpClient = OkHttpClient.Builder()



private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//retrofit object
private val retrofitMoshi = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(BASE_URL).client(httpClient.addInterceptor(logging).build()).build()

//retrofit object that receives response and converts it
private val retrofitScalar = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL).client(httpClient.addInterceptor(logging).build())
    .build()
val retrofitGson = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create()) // Add this line
    .build()


//interface that defines how retrofit talks to server using http
interface ApiService {

//    //function with endpoint photos with return type
//    @GET("entries/en/{word}")
//    fun getPhotos(@Path("word") word : String) : List<DictionaryModel>

//    function with endpoint photos with return type
    @GET("entries/en/{word}")
    fun getWordDictionary(@Path("word") word : String) :Call<List<DictionaryModel>>

    @GET("entries/en/{word}")
    fun getWordString(@Path("word") word : String) :Call<List<String>>

    //function with endpoint photos with return type
//    @GET("entries/en/hello")
//    fun getWord() : Call<String>

}

//The call to create() function on a Retrofit object is expensive and the app needs only one instance of Retrofit API service. So, you expose the service to the rest of the app using object declaration ensuring singleton pattern with object keyword

object Api {

//"lazy instantiation" is when object creation is purposely delayed until you actually need that object to avoid unnecessary computation or use of other computing resources

    val retrofitServiceMoshi : ApiService by lazy {
        retrofitMoshi.create(ApiService::class.java)}

    val retrofitServiceScalar : ApiService by lazy {
        retrofitScalar.create(ApiService::class.java)}
}


