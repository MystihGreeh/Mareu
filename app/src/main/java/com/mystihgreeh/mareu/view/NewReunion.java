package com.mystihgreeh.mareu.view;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.mystihgreeh.mareu.R;
import com.mystihgreeh.mareu.service.ReunionApiService;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.OnClick;



public class NewReunion extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    ImageView back_arrow;
    EditText date_in;
    EditText time_in;
    EditText id;
    TextInputEditText emails;
    Button addButton;

    private ReunionApiService mApiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reunion);

        back_arrow = findViewById(R.id.back_arrow);
        date_in = findViewById(R.id.date);
        time_in = findViewById(R.id.time);
        id = findViewById(R.id.id);
        emails = findViewById(R.id.emails);
        addButton = findViewById(R.id.save);

        Spinner spinner = findViewById(R.id.roomList);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.rooms, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        date_in.setInputType(InputType.TYPE_NULL);
        time_in.setInputType(InputType.TYPE_NULL);


        //Going back to the reunion list when back arrow is clicked
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewReunion.this.finish();
            }
        });


        //Opening the date picker on click
        date_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(date_in);
            }
        });


        //Opening the time picker on click
        time_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog(time_in);
            }
        });

        //Validating the reunion on click


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewReunion.this.finish();
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateEmailAdress(emails);
                NewReunion.this.finish();
            }
        });
    }

    private void validateEmailAdress (TextInputEditText emails){
        String emailInput = emails.getText().toString();

        if (!emailInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            Toast.makeText(this, "Email valide", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Email invalide", Toast.LENGTH_SHORT).show();
        }
    }

    //Setting time picker
    private void showTimeDialog(final EditText time_in) {
        final Calendar calendar = Calendar.getInstance();

        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                time_in.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };
        TimePickerDialog timeDialog = new TimePickerDialog(NewReunion.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        timeDialog.show();
    }



    //Setting date picker
    private void showDateDialog(final EditText date_in) {
        Log.i("debug", "methode appelée");
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                date_in.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };
        DatePickerDialog dateDialog = new DatePickerDialog(NewReunion.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dateDialog.show();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    @OnClick(R.id.addButton)
    void addReunion() {
        finish();
    }

    /**
     * Used to navigate to this activity
     * @param activity
     */
    /*public static void navigate(FragmentActivity activity) {
        Intent intent = new Intent(activity, NewReunion.class);
        ActivityCompat.startActivity(activity, intent, null);
    }*/
}



