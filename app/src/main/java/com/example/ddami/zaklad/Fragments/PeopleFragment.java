package com.example.ddami.zaklad.Fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.ddami.zaklad.R;
import com.facebook.FacebookActivity;
import com.facebook.Profile;
import com.facebook.internal.ImageRequest;
import com.facebook.login.widget.ProfilePictureView;


/**
 * Created by Wojtek on 28.10.2017.
 */

public class PeopleFragment extends Fragment {
    ImageView imageView;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.people_fragment, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String name = Profile.getCurrentProfile().getName().toString();

        ProfilePictureView profilePictureView;

        profilePictureView = (ProfilePictureView) getActivity().findViewById(R.id.friendProfilePicture);

        int dimensionPixelSize = getResources().getDimensionPixelSize(com.facebook.R.dimen.com_facebook_profilepictureview_preset_size_large);

        Uri profilePictureUri = ImageRequest.getProfilePictureUri(Profile.getCurrentProfile().getId(), dimensionPixelSize , dimensionPixelSize );

        profilePictureView.setProfileId(Profile.getCurrentProfile().getId());

        TextView textView = (TextView) getActivity().findViewById(R.id.textView3);
        textView.setText("Użytkownik: " +name);
    }

}
