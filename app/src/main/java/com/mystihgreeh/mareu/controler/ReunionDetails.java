package com.mystihgreeh.mareu.controler;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mystihgreeh.mareu.DI.Injection;
import com.mystihgreeh.mareu.R;
import com.mystihgreeh.mareu.model.Reunion;
import com.mystihgreeh.mareu.service.ReunionApiService;

import java.text.SimpleDateFormat;
import java.util.Objects;

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
        Objects.requireNonNull(this.getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setTitle("Détails de la réunion");

        mRoom = findViewById(R.id.room);
        mDate = findViewById(R.id.date);
        mTime = findViewById(R.id.time);
        mReunionObject = findViewById(R.id.reunion_details);
        mEmails = findViewById(R.id.emails);





        //Get the reunion information
        mApiService = Injection.getReunionApiService();
        Intent intent = getIntent();

        reunion = (Reunion) intent.getSerializableExtra("reunion");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        mRoom.setText(reunion.getRoom());
        mDate.setText(simpleDateFormat.format(reunion.getDate()));
        mTime.setText(reunion.getTime());
        mReunionObject.setText(reunion.getObject());
        mEmails.setText(reunion.getEmails());
    }
}
