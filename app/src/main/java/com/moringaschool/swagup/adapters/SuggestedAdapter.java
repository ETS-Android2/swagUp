package com.moringaschool.swagup.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.swagup.ClothesActivity;
import com.moringaschool.swagup.R;
import com.moringaschool.swagup.interfaces.RecycleViewClickListener;
import com.moringaschool.swagup.SuggestedModel;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SuggestedAdapter extends RecyclerView.Adapter<SuggestedAdapter.SuggestedViewHolder> {

    ArrayList<SuggestedModel> suggestedModels;
    final private RecycleViewClickListener clickListener;


    public SuggestedAdapter(ArrayList<SuggestedModel> suggestedModels, ClothesActivity clothesActivity, RecycleViewClickListener clickListener) {
        this.clickListener = clickListener;
        this.suggestedModels= suggestedModels;
    }


    @NonNull
    @Override
    public SuggestedAdapter.SuggestedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.suggested_items,parent,false);
        final SuggestedViewHolder suggestedViewHolder = new SuggestedViewHolder(view);
        return suggestedViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SuggestedAdapter.SuggestedViewHolder holder, int position) {
        SuggestedModel suggestedModel = suggestedModels.get(position);
        holder.image.setImageResource(suggestedModel.getImage());
        holder.title.setText(suggestedModel.getTitle());

    }

    @Override
    public int getItemCount() {
        return suggestedModels.size();
    }
    public class SuggestedViewHolder extends RecyclerView.ViewHolder{

        CircleImageView image;
        TextView title;

        public SuggestedViewHolder(@NonNull View itemView) {
            super(itemView);

            image =itemView.findViewById(R.id.suggestedImage);
            title =itemView.findViewById(R.id.suggestedTitle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onItemClick(getBindingAdapterPosition());
                }
            });
        }
    }
}

