package com.example.dictionaryapp.model

data class DictionaryModel(
    val word: String,
    val phonetic:String,
    val phonetics : List<PhoneticModel>,
    val meanings: List<MeaningModel>

)

data class PhoneticModel(
    val text: String,
    val audio: String
)

data class MeaningModel(
    val partOfSpeech: String,
    val definitions:List<DefinitionModel>,
)
data class DefinitionModel(
    val definition : String,    
)

