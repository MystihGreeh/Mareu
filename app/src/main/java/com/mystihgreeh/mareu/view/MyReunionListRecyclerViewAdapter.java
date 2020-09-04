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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.mystihgreeh.mareu.R;
import com.mystihgreeh.mareu.events.DeleteReunionEvent;
import com.mystihgreeh.mareu.model.Reunion;
import com.mystihgreeh.mareu.model.Room;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Random;

import static android.media.CamcorderProfile.get;


public class MyReunionListRecyclerViewAdapter extends RecyclerView.Adapter<MyReunionListRecyclerViewAdapter.ViewHolder> {

    private final List<Reunion> mReunion;
    private final List<Room> mRooms;


    public MyReunionListRecyclerViewAdapter(List<Reunion> items, List<Room> rooms) {
        mReunion = items;
        mRooms = rooms;
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
        //final Room rooms = mRooms.get(position) ;
        holder.mRoom.setText(reunion.getObject()+" - "+reunion.getTime()+ " - "+reunion.getRoom());
        holder.mEmails.setText(reunion.getEmails());
        //holder.mCcolor.setColorFilter(rooms.getmColor());

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
        public ImageView mDeleteButton;
        public ConstraintLayout mConstraintLayout;
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


