package com.example.dictionaryapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dictionaryapp.R

class DefinitionFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.definition_view, container, false)
        return v
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

}