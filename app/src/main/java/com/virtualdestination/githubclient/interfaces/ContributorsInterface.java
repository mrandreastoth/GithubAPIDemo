package com.virtualdestination.githubclient.interfaces;

import com.virtualdestination.githubclient.objects.Contributor;

import java.util.List;

/**
 * Created by Rashid on 26/11/2017.
 */

public interface ContributorsInterface {

    void fetchContributors(List<Contributor> contributorsList);

    void onError(Throwable t);

}
