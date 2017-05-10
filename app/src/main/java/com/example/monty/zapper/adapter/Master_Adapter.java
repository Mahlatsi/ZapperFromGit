package com.example.monty.zapper.adapter;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.monty.zapper.MainActivity;
import com.example.monty.zapper.R;
import com.example.monty.zapper.fragments.DetailFragment;
import com.example.monty.zapper.helper.Constants;
import com.example.monty.zapper.medels.PersonDetails;

import java.util.List;

import static com.example.monty.zapper.R.id.details_container;
import static com.example.monty.zapper.R.id.main_container;

/**
 * Created by Monty on 5/7/2017.
 */

public class Master_Adapter extends RecyclerView.Adapter<Master_Adapter.ViewHolder> {
    private List<PersonDetails> mPersonDetailsList;
    private Context mContext;

    public Master_Adapter(List<PersonDetails> PersonDetailsData,Context context) {
        this.mPersonDetailsList = PersonDetailsData;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_of_persons,parent,false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mId.setText(String.valueOf(mPersonDetailsList.get(position).getId()));
        holder.mFirstName.setText(mPersonDetailsList.get(position).getFirstName());
        holder.mLastName.setText(mPersonDetailsList.get(position).getLastName());
        holder.mCardView.setCardBackgroundColor(ContextCompat.getColor(mContext, Constants.getBackgroundColor(position)));
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = mPersonDetailsList.get(holder.getAdapterPosition()).getId();
                int color = holder.getAdapterPosition();
                holder.getAdapterPosition();

                FragmentManager mFragmentManager = ((FragmentActivity)mContext).getSupportFragmentManager();
                FragmentTransaction mTransaction = mFragmentManager.beginTransaction();

                mTransaction.addToBackStack("");
                if(mContext.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                    mTransaction.replace(details_container, new DetailFragment(id,color));
                }else {
                    mTransaction.replace(main_container, new DetailFragment(id,color));
                }
                mTransaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPersonDetailsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView mCardView;
        private TextView mFirstName,mLastName,mId;
        public ViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView)itemView.findViewById(R.id.card_view);
            mId = (TextView)itemView.findViewById(R.id.id_text);
            mFirstName = (TextView)itemView.findViewById(R.id.firstname_text_view);
            mLastName = (TextView)itemView.findViewById(R.id.lastname_text_view);
        }
    }

    public int getBackgroundColor(int position) {
        int color;
        int num = position % 6;

        switch (num) {
            case 0:
                color = R.color.app_color_lime;
                break;
            case 1:
                color = R.color.app_color_orange;
                break;
            case 2:
                color = R.color.app_color_blue;
                break;
            case 3:
                color = R.color.app_color_red;
                break;
            case 4:
                color = R.color.app_color_purple;
                break;
            default:
                color = R.color.app_custom_color;
                break;
        }
        return  color;
    }

}
