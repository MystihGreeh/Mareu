package com.mystihgreeh.mareu.controler;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mystihgreeh.mareu.R;
import com.mystihgreeh.mareu.DI.Injection;
import com.mystihgreeh.mareu.events.DeleteReunionEvent;
import com.mystihgreeh.mareu.model.Reunion;
import com.mystihgreeh.mareu.model.Room;
import com.mystihgreeh.mareu.service.ReunionApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ReunionList extends AppCompatActivity {

    RecyclerView mRecyclerView;
    private ReunionApiService mApiService;

    private List<Reunion> mReunion;
    private List<Room> mRoom;
    List<Reunion> mReunionListFiltered = new ArrayList<>();
    private FloatingActionButton mButton;


    Spinner roomSpinner;
    Boolean isDateFiltered = false;
    Boolean isLocationFiltered = false;
    Date dateFilterSelected;
    String roomFilterSelected = "";
    Date date;

    public static String[] ROOM_LIST = {

            new String("Luigi"),
            new String("Mario"),
            new String("Peach"),
            new String("Bowser"),
            new String("Toad"),

    };;

    private Room room;



    /**
     *
     * Create and return a new instance
     *
     * @return @{@link ReunionList}
     */
    public static ReunionList newInstance() {
        ReunionList fragment;
        fragment = new ReunionList();
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reunion_list);
        mApiService = Injection.getReunionApiService();
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        initList();
        mButton = findViewById(R.id.addButton);
        roomSpinner = findViewById(R.id.menu_filter_by_room);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NewReunion.class);
                Activity activity = (Activity) v.getContext();
                activity.startActivityForResult(intent, 1);
            }
        });


    }


                      ///////////////// LIST ////////////////////////

    /**
     * Init the List of reunion
     */
    private void initList() {
        mReunion = mApiService.getReunions();
        mRoom = mApiService.getRooms();
        // Filter the list if one is set
        mReunionListFiltered = mApiService.reunionListFilter(isDateFiltered, isLocationFiltered, roomFilterSelected, dateFilterSelected);
        mRecyclerView.setAdapter(new MyReunionListRecyclerViewAdapter(mReunionListFiltered, mRoom));
    }


    @Override
    public void onResume() {
        Log.i("debug", "onResume appelé");
        super.onResume();
        initList();
    }

    @Override
    public void onStart() {
        Log.i("debug", "onStart appelé");
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Fired if the user clicks on a delete button
     *
     * @param event
     */
    @Subscribe
    public void onDeleteReunion(DeleteReunionEvent event) {
        mApiService.deleteReunion(event.reunion);
        initList();
    }

    /**
     * Delete reunions when screen rotates
     */
    @Override
    public void onDestroy() {
        Injection.getNewInstanceApiService();
        super.onDestroy();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && data != null) {
            Reunion reunion = new Reunion(data.getStringExtra("object"),
                    date = new Date (data.getLongExtra("date", -1)),
                    data.getStringExtra("time"),
                    data.getStringExtra("room"),
                    data.getStringExtra("emails"));
            mApiService.addReunion(reunion);
            initList();
        }
    }




                         ///////////////////FILTERS//////////////////////


    //setting the filters menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.filters_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {


        // Filter by room
        if (item.getItemId() == R.id.menu_filter_by_room) {
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(ReunionList.this);
            mBuilder.setTitle(R.string.reunion_room_filter);
            mBuilder.setSingleChoiceItems(ROOM_LIST, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    System.out.println(which);
                    roomFilterSelected = ROOM_LIST[which];
                    initList();
                }
            });
            mBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                        isLocationFiltered = true;
                        initList();
                    }
            });
            mBuilder.setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    isLocationFiltered = false;
                    initList();
                }
            });
            AlertDialog mDialog = mBuilder.create();
            mDialog.show();
            isLocationFiltered = true;
            return true;
        }


        // Filter by date
        if (item.getItemId() == R.id.menu_filter_by_date){
        final Calendar cldr = Calendar.getInstance();
        int year = cldr.get(Calendar.YEAR);
        int month = cldr.get(Calendar.MONTH);
        int day = cldr.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(ReunionList.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                (view, year1, month1, dayOfMonth) -> {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.YEAR, year1);
                    calendar.set(Calendar.MONTH, month1);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    dateFilterSelected = calendar.getTime();
                    initList();

                }, year, month, day);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
        isDateFiltered = true;
        return true;}

        // Reset filter
        if (item.getItemId() == R.id.menu_reset_filters){
        isDateFiltered = false;
        isLocationFiltered = false;
        initList();
        return true;
    }
        return super.onOptionsItemSelected(item);
    }

}





