package com.mystihgreeh.mareu.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

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
import com.mystihgreeh.mareu.service.ReunionApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class ReunionList extends AppCompatActivity {

    RecyclerView mRecyclerView;
    private ReunionApiService mApiService;
    private List<Reunion> mReunion;
    private FloatingActionButton mButton;


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
        initList();

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
    private void initList() {
        Log.i("debug", "initList appelé");
        mReunion = mApiService.getReunions();
        mRecyclerView.setAdapter(new MyReunionListRecyclerViewAdapter(mReunion));
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
    public void onDeleteNeighbour(DeleteReunionEvent event) {
        mApiService.deleteReunion(event.reunion);
        initList();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            Reunion reunion = new Reunion(data.getStringExtra("room"), data.getStringExtra("date"), data.getStringExtra("time"), data.getStringExtra("object"), data.getStringExtra("emails"));
            mApiService.createReunion(reunion);
            initList();
        }
    }





    //setting the filters menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.filters_menu, menu);
        return true;
    }
}