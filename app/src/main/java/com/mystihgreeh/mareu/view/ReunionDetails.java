package com.mystihgreeh.mareu.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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

    ImageView back_arrow;
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

        back_arrow = findViewById(R.id.back_arrow);
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
        mReunionObject.setText(reunion.getId());
        mEmails.setText(reunion.getEmails());


        // Back button finish the activity when clicked
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReunionDetails.this.finish();
            }
        });



    }
}
