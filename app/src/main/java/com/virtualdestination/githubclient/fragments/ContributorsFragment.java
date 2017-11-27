package com.virtualdestination.githubclient.fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.virtualdestination.githubclient.R;
import com.virtualdestination.githubclient.adapters.ContributorsAdapter;
import com.virtualdestination.githubclient.apicontrollers.ApiController;
import com.virtualdestination.githubclient.apicontrollers.ContributorsController;
import com.virtualdestination.githubclient.interfaces.ApiInterface;
import com.virtualdestination.githubclient.interfaces.ContributorsInterface;
import com.virtualdestination.githubclient.objects.Contributor;
import com.virtualdestination.githubclient.utilities.GeneralUtilities;

import java.util.List;

/**
 * Created by Rashid on 26/11/2017.
 */

public class ContributorsFragment extends Fragment implements ContributorsInterface {

    private Button fetchRepositories;
    private RecyclerView mRecyclerView;
    private ApiInterface apiService;

    ProgressDialog progressDialog;
    private ContributorsController repositoryController = new ContributorsController();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repo, container, false);


        fetchRepositories = view.findViewById(R.id.button_fetch);
        mRecyclerView = view.findViewById(R.id.list_contributors);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getString(R.string.loading_message));
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);


        if(! GeneralUtilities.checkInternetConnection() ){
            fetchRepositories.setEnabled(false);
            Toast.makeText(getActivity(), getString(R.string.loading_message), Toast.LENGTH_LONG).show();
        } else {
            apiService = ApiController.getClient().create(ApiInterface.class);
        }


        fetchRepositories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   loadContributors();
                   fetchRepositories.setVisibility(View.GONE);
            }
        });


        return view;
    }


    @Override
    public void fetchContributors(@Nullable List<Contributor> contributorsList){

        ContributorsAdapter adapter = new ContributorsAdapter(contributorsList, R.layout.contributor_item, getActivity());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(adapter);
        hideProgressDialog();


    }

    @Override
    public void onError(Throwable throwable){
        throwable.printStackTrace();
        hideProgressDialog();
        showFailureDialog();
    }


    public void loadContributors(){
        showProgressDialog();
        repositoryController.FetchRepositoryContributors(this, apiService);
    }



    public void showProgressDialog(){
        if(!progressDialog.isShowing()){
            progressDialog.show();
        }
    }

    public void hideProgressDialog(){
        if(progressDialog.isShowing()){
            progressDialog.hide();
        }
    }

    public void showFailureDialog() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                fetchRepositories.setVisibility(View.VISIBLE);
                                break;

                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Error!");
                builder.setMessage(getString(R.string.fetching_contributors_error)).setPositiveButton(getString(R.string.button_ok), dialogClickListener)
                        .show();
            }
        });

    }

}