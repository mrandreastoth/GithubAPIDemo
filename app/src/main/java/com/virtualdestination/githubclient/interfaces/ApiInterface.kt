package com.virtualdestination.githubclient.interfaces

import com.virtualdestination.githubclient.models.Contributor
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiInterface {
    @get:GET("/repos/ruby/ruby/contributors")
    @get:Headers("User-Agent: rashid56156", "Accept: application/vnd.github.v3+json")
    val contributors: Call<List<Contributor?>?>?
}