package com.virtualdestination.githubclient.apicontrollers;

import com.virtualdestination.githubclient.interfaces.ApiInterface;
import com.virtualdestination.githubclient.interfaces.ContributorsInterface;
import com.virtualdestination.githubclient.objects.Contributor;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rashid on 26/11/2017.
 */

public class ContributorsController {

    public void FetchRepositoryContributors(final ContributorsInterface contributorsInterface, ApiInterface apiService){
        Call<List<Contributor>> call = apiService.getContributors();
        call.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Call<List<Contributor>>call, Response<List<Contributor> >response) {
                List<Contributor> contributors = response.body();
                contributorsInterface.fetchContributors(contributors);
            }

            @Override
            public void onFailure(Call<List<Contributor>>call, Throwable t) {
                // Log error here since request failed
                contributorsInterface.onError(t);

            }
        });
    }
}
