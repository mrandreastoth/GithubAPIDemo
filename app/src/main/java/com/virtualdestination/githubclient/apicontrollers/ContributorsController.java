package com.virtualdestination.githubclient.apicontrollers;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.virtualdestination.githubclient.interfaces.ApiInterface;
import com.virtualdestination.githubclient.interfaces.ContributorsInterface;
import com.virtualdestination.githubclient.objects.Contributor;
import com.virtualdestination.githubclient.utilities.GeneralUtilities;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rashid on 26/11/2017.
 */

public class ContributorsController {


    /**
     * This method uses Fast Networking Library
     * @param repositoryInterface
     */

    public void GetRepositoryContributors(final ContributorsInterface repositoryInterface) {
        AndroidNetworking.get(GeneralUtilities.getApiUrl("/repos/ruby/ruby/contributors"))
                .addHeaders("User-Agent", "rashid56156")
                .addHeaders("Accept", "application/vnd.github.v3+json")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObjectList(Contributor.class, new ParsedRequestListener<List<Contributor>>() {
                    @Override
                    public void onResponse(List<Contributor> contributors) {
                        // do anything with response
                        repositoryInterface.fetchContributors(contributors);
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                       // repositoryInterface.onError(error);
                    }
                });
    }


    /**
     * This method used Retrofit as networking Client
     * @param contributorsInterface
     * @param apiService
     */


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
