package com.moringaschool.swagup.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.moringaschool.swagup.ImageModel;
import com.moringaschool.swagup.R;

import java.util.List;


public class ImagesAdapter extends RecyclerView.Adapter<ImagesViewHolder> {

    private Context context;
    private List<ImageModel> imagesModelList;

    public ImagesAdapter(Context context, List<ImageModel> imagesModelList) {
        this.context = context;
        this.imagesModelList = imagesModelList;
    }

    @NonNull
    @Override
    public ImagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_image_item,parent,false);

        return new ImagesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesViewHolder holders, int position) {
        final ImagesViewHolder holder = holders;
        ImageModel model = imagesModelList.get(position);

        Glide.with(context).load(model.getMediumUrl()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "clicked!!!", Toast.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    public int getItemCount() {
        return imagesModelList.size();
    }
};

class ImagesViewHolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    TextView photographerName;

    public ImagesViewHolder(@NonNull View itemView) {
        super((itemView));

        imageView = itemView.findViewById(R.id.imageViewItem);
        photographerName = itemView.findViewById(R.id.photographerName);
    }
}
