package com.uzlahpri.githubapp.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.uzlahpri.githubapp.model.Users
import cz.msebera.android.httpclient.Header

class FollowViewModel : ViewModel() {
    val listFollow = MutableLiveData<ArrayList<Users>>()
    fun setListFollow(username: String, page: String, context: Context) {
        val client = AsyncHttpClient()
        client.addHeader("Authorization", "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")
        client.addHeader("User-Agent", "request")

        val url = when (page) {
            "followers" -> "https://api.github.com/users$username/followers"
            "following" -> "https://api.github.com/users$username/following"
            else -> ""
        }

        client.get(url, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray
            ) {

            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray,
                error: Throwable
            ) {

            }
        })
    }
}