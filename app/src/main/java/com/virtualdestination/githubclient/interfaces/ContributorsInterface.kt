package com.virtualdestination.githubclient.interfaces

import com.virtualdestination.githubclient.models.Contributor

interface ContributorsInterface {
    fun fetchContributors(contributorsList: List<Contributor?>)
    fun onError(t: Throwable)
}