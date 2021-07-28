package com.example.camerxtests;

import android.app.Activity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Activity context;
    ArrayList<Publication> publicationArrayList;

    public RecyclerViewAdapter(Activity context, ArrayList<Publication> publicationArrayList) {
        this.context = context;
        this.publicationArrayList = publicationArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.publication_item,parent,false);
        return new RecyclerViewViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        Publication publication = publicationArrayList.get(position);
        RecyclerViewViewHolder viewHolder= (RecyclerViewViewHolder) holder;
        //Check if file exists
        if(publication.getImageFile() != null ){
            viewHolder.imageView.setImageURI(Uri.parse(publication.getImageFile().getAbsolutePath()));
        }
        if(publication.getDescription() != null){

                String text = publication.getDescription();
                viewHolder.descriptionTextView.setText(text);
                System.out.println("");
        }
    }

    @Override
    public int getItemCount() {
        return publicationArrayList.size();
    }

    class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView descriptionTextView;

        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);

        }
    }


}
