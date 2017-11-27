package com.virtualdestination.githubclient.interfaces;

import android.support.annotation.Nullable;

import com.androidnetworking.error.ANError;
import com.virtualdestination.githubclient.objects.Contributor;

import java.util.List;

/**
 * Created by Rashid on 26/11/2017.
 */

public interface ContributorsInterface {

    void fetchContributors(@Nullable List<Contributor> contributorsList);

    void onError(ANError error);
}
