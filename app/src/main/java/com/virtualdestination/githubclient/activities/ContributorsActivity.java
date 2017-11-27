package com.virtualdestination.githubclient.activities;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.virtualdestination.githubclient.R;
import com.virtualdestination.githubclient.fragments.ContributorsFragment;

public class ContributorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo);

        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.listcontainer, new ContributorsFragment()).commit();

    }


}
