package com.virtualdestination.githubclient.utilities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.virtualdestination.githubclient.GithubApplication;

/**
 * Created by Rashid on 26/11/2017.
 */

public class GeneralUtilities {

    public static String getApiUrl(String relativeUrl) {
        String serviceURL = Constants.GITHUB_API_PATH;

        return serviceURL + relativeUrl;
    }

    public static boolean checkInternetConnection() {
        ConnectivityManager cm = (ConnectivityManager) GithubApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }
}
