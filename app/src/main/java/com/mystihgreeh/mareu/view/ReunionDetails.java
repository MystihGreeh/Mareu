package com.mystihgreeh.mareu.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.mystihgreeh.mareu.R;

public class ReunionDetails extends AppCompatActivity {

    ImageView back_arrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reunion_details);

        back_arrow = findViewById(R.id.back_arrow);

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReunionDetails.this.finish();
            }
        });
    }
}
