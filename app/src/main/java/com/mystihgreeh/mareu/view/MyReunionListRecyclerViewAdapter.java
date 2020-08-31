package com.mystihgreeh.mareu.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mystihgreeh.mareu.R;
import com.mystihgreeh.mareu.events.DeleteReunionEvent;
import com.mystihgreeh.mareu.model.Reunion;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Random;


public class MyReunionListRecyclerViewAdapter extends RecyclerView.Adapter<MyReunionListRecyclerViewAdapter.ViewHolder> {

    private final List<Reunion> mReunion;
    Random random = new Random();

    Integer[] colors = {
            R.drawable.metting_room_one,
            R.drawable.meeting_room_two,
            R.drawable.meeting_room_three,
            R.drawable.meeting_room_four,
            R.drawable.meeting_room_five,
            R.drawable.meeting_room_six,
            R.drawable.meeting_room_seven,
            R.drawable.meeting_room_eight,
            R.drawable.meeting_room_nine,
            R.drawable.meeting_room_ten,
    };


    public MyReunionListRecyclerViewAdapter(List<Reunion> items) {
        mReunion = items;
    }

    @NonNull
    @Override
    public MyReunionListRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_reunion, parent, false);
        return new ViewHolder(view);
    }

    //The reunion elements are displayed in the list
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyReunionListRecyclerViewAdapter.ViewHolder holder, int position) {
        final Reunion reunion = mReunion.get(position);
        holder.mRoom.setText(reunion.getObject()+" - "+reunion.getTime()+ " - "+reunion.getRoom());
        holder.mEmails.setText(reunion.getEmails());
        holder.mCcolor.setImageResource(colors[position % colors.length]);


        //User click on the delete button to delete the reunion
        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                EventBus.getDefault().post(new DeleteReunionEvent(reunion));
            }
        });

        //User click on the reunion to open the reunion details
        holder.mConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ReunionDetails.class);
                intent.putExtra("reunion", (Parcelable) reunion);
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mReunion.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mRoom;
        public TextView mDate;
        public TextView mTime;
        public TextView mReunionObject;
        public TextView mEmails;
        public ImageButton mDeleteButton;
        public FrameLayout mConstraintLayout;
        public ImageView mCcolor;


        public ViewHolder(View itemView) {
            super(itemView);
            mRoom = itemView.findViewById(R.id.room);
            mDate = itemView.findViewById(R.id.date);
            mTime = itemView.findViewById(R.id.time);
            mReunionObject = itemView.findViewById(R.id.reunion_details);
            mEmails = itemView.findViewById(R.id.reunion_emails);
            mDeleteButton = itemView.findViewById(R.id.item_list_delete_button);
            mConstraintLayout = itemView.findViewById(R.id.reunion);
            mCcolor = itemView.findViewById(R.id.reunion_color);

        }

    }
}


