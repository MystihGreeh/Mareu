package com.mystihgreeh.mareu.view;

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

    @NonNull
    @Override
    public MyReunionListRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_reunion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyReunionListRecyclerViewAdapter.ViewHolder holder, int position) {
        final Reunion reunion = mReunion.get(position);
        /*Glide.with(holder.mReunionAvatar.getContext())
                .load(reunion.getId())
                .into(holder.mReunionAvatar);*/

        //holder.mRoom.setText(reunion.getRoom());
        //holder.mTime.setText(reunion.getTime());
        holder.mReunionObject.setText(reunion.getId());
        holder.mEmails.setText(reunion.getEmails());

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


        public ImageView mReunionAvatar;
        public TextView mRoom;
        public TextView mDate;
        public TextView mTime;
        public TextView mReunionObject;
        public TextView mEmails;
        public ImageButton mDeleteButton;
        public FrameLayout mConstraintLayout;


        public ViewHolder(View itemView) {
            super(itemView);
            mRoom = itemView.findViewById(R.id.room);
            mDate = itemView.findViewById(R.id.date);
            mTime = itemView.findViewById(R.id.time);
            mReunionObject = itemView.findViewById(R.id.reunion_details);
            mEmails = itemView.findViewById(R.id.reunion_emails);
            mDeleteButton = itemView.findViewById(R.id.item_list_delete_button);
            mConstraintLayout = itemView.findViewById(R.id.reunion);


        }

    }
}


