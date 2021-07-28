package com.virtualdestination.githubclient.apicontrollers

import com.virtualdestination.githubclient.interfaces.ApiInterface
import com.virtualdestination.githubclient.interfaces.ContributorsInterface
import com.virtualdestination.githubclient.models.Contributor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContributorsController {

    fun fetchRepositoryContributors(contributorsInterface: ContributorsInterface, apiService: ApiInterface) {
        val call = apiService.contributors
        call!!.enqueue(object : Callback<List<Contributor?>?> {
            override fun onResponse(call: Call<List<Contributor?>?>, response: Response<List<Contributor?>?>) {
                val contributors = response.body()
                if (contributors != null) {
                    contributorsInterface.fetchContributors(contributors)
                }
            }

            override fun onFailure(call: Call<List<Contributor?>?>, t: Throwable) {
                // Log error here since request failed
                contributorsInterface.onError(t)
            }
        })
    }
}