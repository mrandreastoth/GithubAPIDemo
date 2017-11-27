package com.virtualdestination.githubclient.apicontrollers;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.virtualdestination.githubclient.interfaces.ContributorsInterface;
import com.virtualdestination.githubclient.objects.Contributor;
import com.virtualdestination.githubclient.utilities.GeneralUtilities;

import java.util.List;

/**
 * Created by Rashid on 26/11/2017.
 */

public class ContributorsController {

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
                        repositoryInterface.onError(error);
                    }
                });
    }
}
