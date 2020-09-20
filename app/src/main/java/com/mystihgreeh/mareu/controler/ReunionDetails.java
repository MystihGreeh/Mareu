package com.mystihgreeh.mareu.controler;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.Toolbar;

import com.mystihgreeh.mareu.R;
import com.mystihgreeh.mareu.DI.Injection;
import com.mystihgreeh.mareu.model.Reunion;
import com.mystihgreeh.mareu.service.ReunionApiService;

import java.util.Calendar;

public class ReunionDetails extends AppCompatActivity {

    TextView mRoom;
    TextView mDate;
    TextView mTime;
    TextView mReunionObject;
    TextView mEmails;
    Toolbar toolbar;

    Reunion reunion;
    ReunionApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reunion_details);
        ActionBar actionBar = getActionBar();
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setTitle("Détails de la réunion");

        mRoom = findViewById(R.id.room);
        mDate = findViewById(R.id.date);
        mTime = findViewById(R.id.time);
        mReunionObject = findViewById(R.id.reunion_details);
        mEmails = findViewById(R.id.emails);



        //Get the reunion information
        mApiService = Injection.getReunionApiService();
        Intent intent = getIntent();
        reunion = intent.getParcelableExtra("reunion");
        assert reunion != null;
        mRoom.setText(reunion.getRoom());
        mDate.setText(reunion.getRoom());
        mTime.setText(reunion.getTime());
        mReunionObject.setText(reunion.getObject());
        mEmails.setText(reunion.getEmails());



    }
}
