package com.mystihgreeh.mareu.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mystihgreeh.mareu.R;
import com.mystihgreeh.mareu.DI.Injection;
import com.mystihgreeh.mareu.model.Reunion;
import com.mystihgreeh.mareu.service.ReunionApiService;

public class ReunionDetails extends AppCompatActivity {

    TextView mRoom;
    TextView mDate;
    TextView mTime;
    TextView mReunionObject;
    TextView mEmails;

    Reunion reunion;
    ReunionApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reunion_details);
        ActionBar actionBar = getActionBar();
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRoom = findViewById(R.id.room);
        mDate = findViewById(R.id.date);
        mTime = findViewById(R.id.time);
        mReunionObject = findViewById(R.id.reunion_details);
        mEmails = findViewById(R.id.emails);



        //Get the reunion information
        mApiService = Injection.getReunionApiService();
        Intent intent = getIntent();
        reunion = intent.getParcelableExtra("reunion");
        /*Glide.with(this)
                .load(reunion.getColor())
                .into(color);*/
        assert reunion != null;
        mRoom.setText(reunion.getRoom());
        mDate.setText(reunion.getDate());
        mTime.setText(reunion.getTime());
        mReunionObject.setText(reunion.getObject());
        mEmails.setText(reunion.getEmails());



    }
}
