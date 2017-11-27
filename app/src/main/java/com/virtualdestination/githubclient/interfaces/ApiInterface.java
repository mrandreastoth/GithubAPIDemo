package com.virtualdestination.githubclient.interfaces;

import com.virtualdestination.githubclient.objects.Contributor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by Rashid on 27/11/2017.
 */

public interface ApiInterface {
    @Headers({"User-Agent: rashid56156",
            "Accept: application/vnd.github.v3+json"
    })
    @GET("/repos/ruby/ruby/contributors")
    Call<List<Contributor>> getContributors();
}
