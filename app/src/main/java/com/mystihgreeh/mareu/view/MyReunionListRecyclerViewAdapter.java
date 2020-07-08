package com.mystihgreeh.mareu.view;

import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mystihgreeh.mareu.R;
import com.mystihgreeh.mareu.events.DeleteReunionEvent;
import com.mystihgreeh.mareu.model.Reunion;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyReunionListRecyclerViewAdapter extends RecyclerView.Adapter<MyReunionListRecyclerViewAdapter.ViewHolder> {

    private final List<Reunion> mReunion;


    public MyReunionListRecyclerViewAdapter(List<Reunion> items) {
        mReunion = items;
    }

    @Override
    public MyReunionListRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_reunion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyReunionListRecyclerViewAdapter.ViewHolder holder, int position) {
        final Reunion reunion = mReunion.get(position);
        Glide.with(holder.mReunionAvatar.getContext())
                .load(reunion.getId())
                .into(holder.mReunionAvatar);

        holder.mReunionDetails.setText(reunion.getId());
        holder.mRoom.setText(reunion.getRoom());
        holder.mTime.setText(reunion.getTime());

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

        @BindView(R.id.item_list_avatar)
        public ImageView mReunionAvatar;
        @BindView(R.id.date)
        public TextView mDate;
        @BindView(R.id.time)
        public TextView mTime;
        @BindView(R.id.room)
        public TextView mRoom;
        @BindView(R.id.reunion_details)
        public TextView mReunionDetails;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;
        @BindView(R.id.reunion)
        public ConstraintLayout mConstraintLayout;



        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}


