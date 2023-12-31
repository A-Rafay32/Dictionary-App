package com.example.dictionaryapp.model

data class DictionaryModel(
    val word: String,
    val phonetic: String?,
    val meanings: List<MeaningModel>
) {
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "word" to word,
            "phonetic" to phonetic,
//            "phonetics" to phonetics.map { it.toMap() },
            "meanings" to meanings.map { it.toMap() }
        )
    }
}


data class MeaningModel(

    val definitions: List<DefinitionModel>,
    val synonyms : List<String>,
    val antonyms : List<String>,
 ) {
    fun toMap(): Map<String, Any> {
        return mapOf(
            "definitions" to definitions.map { it.toMap() },
            "synonyms" to synonyms,
            "antonyms" to antonyms
        )
    }
}

data class DefinitionModel(
    val definition: String,
    val example: String?
) {
    fun toMap(): Map<String, String?> {
        return mapOf(
            "definition" to definition,
            "example" to example
        )
    }
}

