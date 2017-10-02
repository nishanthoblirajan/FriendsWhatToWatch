package com.zaptrapp.friendswhattowatch;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.florent37.diagonallayout.DiagonalLayout;

public class ProfileActivity extends AppCompatActivity {

    private DiagonalLayout diagonalLayout;
    private ImageView imageViewProfileActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        initView();
    }

    private void initView() {
        imageViewProfileActivity = (ImageView) findViewById(R.id.imageViewProfileActivity);
        Glide.with(this).load("https://static.pexels.com/photos/279315/pexels-photo-279315.jpeg").into(imageViewProfileActivity);
    }
}
