///*
// * Copyright (C) 2021 The Android Open Source Project.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *     http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package com.example.dictionaryapp.adapters
//
//import android.view.View
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.core.net.toUri
//import androidx.databinding.BindingAdapter
//import androidx.recyclerview.widget.RecyclerView
//import com.example.dictionaryapp.R
//import com.example.dictionaryapp.fragments.ApiStatus
//import com.example.dictionaryapp.network.DictionaryModel
//import org.w3c.dom.Text
//
///**
// * Updates the data shown in the [RecyclerView].
// */
//@BindingAdapter("listData")
//fun bindRecyclerView(recyclerView: RecyclerView, data: List<DictionaryModel>?) {
//    val adapter = recyclerView.adapter as DefinitionAdapter
//    adapter.submitList(data)
//}
//
///**
// * Uses the Coil library to load an image by URL into an [ImageView]
// */
//@BindingAdapter("imageUrl")
//fun bindImage(imgView: ImageView, imgUrl: String?) {
//    imgUrl?.let {
//        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
//        imgView.load(imgUri) {
//            placeholder(R.drawable.loading_animation)
//            error(R.drawable.ic_broken_image)
//        }
//    }
//}
//
///**
// * This binding adapter displays the [MarsApiStatus] of the network request in an image view.  When
// * the request is loading, it displays a loading_animation.  If the request has an error, it
// * displays a broken image to reflect the connection error.  When the request is finished, it
// * hides the image view.
// */
//@BindingAdapter("ApiStatus")
//fun bindStatus(statusTextView: TextView, status: ApiStatus) {
//    when (status) {
//        ApiStatus.LOADING -> {
//            statusTextView.visibility = View.VISIBLE
//            statusTextView.setText(R.drawable.loading_animation)
//        }
//        ApiStatus.ERROR -> {
//            statusTextView.visibility = View.VISIBLE
//            statusTextView.setText(R.drawable.ic_connection_error)
//        }
//        ApiStatus.DONE -> {
//            statusTextView.visibility = View.GONE
//        }
//    }
//}
