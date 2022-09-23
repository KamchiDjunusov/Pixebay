package com.example.pixebay

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.pixabay.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var adapter = ImageAdapter(mutableListOf())
    var perPage = 10
    var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClicker()
    }

    private fun initClicker() {
        with(binding) {
            changePageBtn.setOnClickListener {
                page++
                doRequest()
            }
            searchBtn.setOnClickListener(doRequest())
            addImagesBtn.setOnClickListener {
                perPage += 5
            }
        }
    }

    private fun ActivityMainBinding.doRequest(): (View) -> Unit =
        {
            App.api.getImages(wordEd.text.toString(), page = page, perPage = perPage)
                .enqueue(object : Callback<PixabayModel> {
                    override fun onResponse(
                        call: Call<PixabayModel>,
                        response: Response<PixabayModel>
                    ) {
                        if (response.isSuccessful) {
                            adapter = ImageAdapter(response.body()?.hits!!)
                            recyclerView.adapter = adapter
                        }
                    }

                    override fun onFailure(call: Call<PixabayModel>, t: Throwable) {
                        Log.e("ololo", "onFailure: ${t.message}")
                    }

                })
        }
}