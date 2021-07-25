package com.example.camerxtests.base.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.camerxtests.R;
import com.example.camerxtests.base.pojo.Publication;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PublicationAdapter extends RecyclerView.Adapter<PublicationAdapter.PublicationViewHolder> {
    private List<Publication> publications;

    public List<Publication> getPublications() {
        return publications;
    }

    public void setPublications(List<Publication> publications) {
        this.publications = publications;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public PublicationViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.publication_item, parent, false);
        return new PublicationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PublicationViewHolder holder, int position) {

        Publication publication = publications.get(position);
        holder.titleTextView.setText(publication.getName());
        holder.descriptionTextView.setText(publication.getCommonDescription());
    }

    @Override
    public int getItemCount() {
        return publications.size();
    }

    class PublicationViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView descriptionTextView;
        private ImageView imageView;


        public PublicationViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            imageView = itemView.findViewById(R.id.imageView);

        }
    }
}
