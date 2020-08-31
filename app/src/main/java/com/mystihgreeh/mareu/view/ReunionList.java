package com.mystihgreeh.mareu.view;

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
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
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
import com.mystihgreeh.mareu.service.DummyReunionGenerator;
import com.mystihgreeh.mareu.service.ReunionApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReunionList extends AppCompatActivity {

    RecyclerView mRecyclerView;
    private ReunionApiService mApiService;
    private List<Reunion> mReunion;
    private FloatingActionButton mButton;

    Boolean isDateFiltered = false;
    Boolean isLocationFiltered = false;
    Date dateFilterSelected;
    String roomFilterSelected = "";
    public final int DEFAULT = 0, BY_ROOM = 1, BY_DATE = 2;



    /**
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
        super.onCreate( savedInstanceState );
        setContentView(R.layout.activity_reunion_list);
        mApiService = Injection.getReunionApiService();
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        initList(DEFAULT);

        mButton = findViewById(R.id.addButton);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NewReunion.class);
                Activity activity = (Activity)v.getContext();
                activity.startActivityForResult(intent, 1);
            }
        });

    }

    /**
     * Init the List of reunion
     */
    private void initList(int type) {

        switch (type){
            case DEFAULT  :
                mReunion = mApiService.getReunions();
                break;
            case BY_ROOM :
                mReunion = mApiService.filteredByRoom();
                break;
            case BY_DATE :
                mReunion = mApiService.filteredByDate();
        }
        // Filter the list if one is selected
        mReunion = mApiService.reunionListFilter(isDateFiltered, isLocationFiltered, roomFilterSelected, dateFilterSelected);
        Log.i("debug", "initList appelé");

        mRecyclerView.setAdapter(new MyReunionListRecyclerViewAdapter(mReunion));
    }

    @Override
    public void onResume() {
        Log.i("debug", "onResume appelé");
        super.onResume();
        initList(DEFAULT);
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
        initList(DEFAULT);
    }

    /**
     * Delete reunions when screen rotates
     */
    @Override
    public void onDestroy(){
        Injection.getNewInstanceApiService();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && data != null){
            Reunion reunion = new Reunion(data.getStringExtra("object"), data.getStringExtra("date"), data.getStringExtra("time"), data.getStringExtra("room"), data.getStringExtra("emails"));
            mApiService.createReunion(reunion);
            initList(DEFAULT);
        }
    }

    //setting the filters menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.filters_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_filter_by_date) {
            filterByDate();
        } else {
            //filterByRoom();
        }
        return super.onOptionsItemSelected(item);
    }


    //Filter by date
    public void filterByDate() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final DatePicker filterDatePicker = new DatePicker(this);
        builder.setView(filterDatePicker);
        builder.setNegativeButton("clear", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                initList(DEFAULT);
            }
        });
        builder.setPositiveButton("filter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.DAY_OF_MONTH, filterDatePicker.getDayOfMonth());
                calendar.set(Calendar.MONTH, filterDatePicker.getMonth());
                calendar.set(Calendar.YEAR, filterDatePicker.getYear());
                initListByDate(calendar);
            }
        });
        builder.show();
    }
    public void initListByDate (Calendar calendar) {
        mApiService.filteredByDate();
        initList(BY_DATE);


    }

    //Filter by room


    // Clear filter


}
