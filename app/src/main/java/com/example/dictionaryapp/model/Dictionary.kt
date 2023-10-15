package com.example.dictionaryapp.model

data class DictionaryModel(
    val word: String,
    val phonetic: String,
    val phonetics: List<PhoneticModel>,
    val meanings: List<MeaningModel>
) {
    fun toMap(): Map<String, Any> {
        return mapOf(
            "word" to word,
            "phonetic" to phonetic,
            "phonetics" to phonetics.map { it.toMap() },
            "meanings" to meanings.map { it.toMap() }
        )
    }
}

data class PhoneticModel(
    val text: String,
    val audio: String
) {
    fun toMap(): Map<String, String> {
        return mapOf(
            "text" to text,
            "audio" to audio
        )
    }
}


data class MeaningModel(
    val partOfSpeech: String,
    val definitions: List<DefinitionModel>
) {
    fun toMap(): Map<String, Any> {
        return mapOf(
            "partOfSpeech" to partOfSpeech,
            "definitions" to definitions.map { it.toMap() }
        )
    }
}

data class DefinitionModel(
    val definition: String
) {
    fun toMap(): Map<String, String> {
        return mapOf(
            "definition" to definition
        )
    }
}

