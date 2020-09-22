package com.mystihgreeh.mareu.controler;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
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
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.mystihgreeh.mareu.DI.Injection;
import com.mystihgreeh.mareu.R;
import com.mystihgreeh.mareu.model.Reunion;
import com.mystihgreeh.mareu.model.Room;
import com.mystihgreeh.mareu.service.DummyReunionGenerator;
import com.mystihgreeh.mareu.service.ReunionApiService;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class NewReunion extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    Spinner room;
    EditText date_in;
    EditText time_in;
    EditText object;
    TextInputLayout emails;
    Button addButton;
    ReunionApiService mApiService;
    Date datePicked;
    String roomSelected;
    String timeSelected;
    String participants;
    String reunionObject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reunion);
        ActionBar actionBar = getActionBar();
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setTitle("Nouvelle réunion");

        initialisation();
    }

    //INITIALISATION

    private void initialisation() {
        room = findViewById(R.id.roomList);
        date_in = findViewById(R.id.date);
        time_in = findViewById(R.id.time);
        object = findViewById(R.id.reunion_object);
        emails = findViewById(R.id.emailsLyt);
        addButton = findViewById(R.id.save);
        mApiService = Injection.getNewInstanceApiService();
        date_in.setInputType(InputType.TYPE_NULL);
        time_in.setInputType(InputType.TYPE_NULL);


        //Opening Room Spinner on click
        @SuppressLint("CutPasteId") Spinner spinner = findViewById(R.id.roomList);
        List<Room> roomList = Arrays.asList(DummyReunionGenerator.getListRooms());
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, roomList);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

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
                showTimeDialog(time_in); }
        });


        //Book the reunion when user click on addButton
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reunion reunion = new Reunion(roomSelected, datePicked, timeSelected, reunionObject, participants);
                Calendar startCalendar = Calendar.getInstance();
                if (!validateEmailAddress()) return;
                initFields(startCalendar);
                finish();

            }
        });
    }

    private void initFields(Calendar startCalendar) {
        Intent intent = new Intent();
        intent.putExtra("room", room.getSelectedItem().toString());
        intent.putExtra("date", datePicked.getTime());
        intent.putExtra("time", time_in.getText().toString());
        intent.putExtra("object", object.getText().toString());
        intent.putExtra("emails", Objects.requireNonNull(emails.getEditText()).getText().toString());
        setResult(1, intent);
    }

    // Emails must be valid email address
    private boolean validateEmailAddress() {
        String emailInput = emails.getEditText().getText().toString().trim();
        if (Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            emails.setError(null);
        return true;}
        if (emailInput.isEmpty()) {
            emails.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            emails.setError("Please enter a valid email address");
            return false; }
        return false;
    }




                //////////////// SETTING DATE AND TIME PICKER ////////////////////


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
                datePicked = calendar.getTime();
                date_in.setText(simpleDateFormat.format(datePicked));
            }
        };
        DatePickerDialog dateDialog = new DatePickerDialog(NewReunion.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dateDialog.show();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();

    }

    //If nothing is selected, the reunion cannot be saved
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }


    /**
     * Used to navigate to this activity
     *
     * @param activity
     */
    public static void navigate(FragmentActivity activity) {
        Intent intent = new Intent(activity, NewReunion.class);
        ActivityCompat.startActivity(activity, intent, null);
    }

}



