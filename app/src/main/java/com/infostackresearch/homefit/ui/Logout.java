package com.infostackresearch.homefit.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.infostackresearch.homefit.R;
import com.infostackresearch.homefit.sessions.UserSessionManager;

public class Logout extends Fragment {
    UserSessionManager session;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_logout, container, false);
        session=new UserSessionManager(getContext());
        session.logoutUser();
        return rootView;
    }
}