package com.example.petcare;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class PetSitterAdapter extends RecyclerView.Adapter<PetSitterAdapter.MyImagesHolder> {

    private ArrayList<String> imagesList = new ArrayList<>();
    private ArrayList<String> nameList = new ArrayList<>();
    private ArrayList<String> phoneList = new ArrayList<>();
    private ArrayList<String> expList = new ArrayList<>();
    private ArrayList<String> catList = new ArrayList<>();
    private ArrayList<String> locationList = new ArrayList<>();
    private PetSitterAdapter.onImageClickListener listener;

    public void setListener(PetSitterAdapter.onImageClickListener listener) {
        this.listener = listener;
    }

    public void setListItem(ArrayList<String> imagesList, ArrayList<String> nameList, ArrayList<String> phoneList, ArrayList<String> expList, ArrayList<String> catList, ArrayList<String> locationList) {
        this.imagesList = imagesList;
        this.nameList = nameList;
        this.phoneList = phoneList;
        this.expList = expList;
        this.catList = catList;
        this.locationList = locationList;
        notifyDataSetChanged();
    }

    public interface onImageClickListener
    {
        void onImageClick(MyImages myImages);
    }

    @NonNull
    @Override
    public PetSitterAdapter.MyImagesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewholder_pet_sitter_layout,parent,false);

        return new PetSitterAdapter.MyImagesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetSitterAdapter.MyImagesHolder holder, int position) {

        String imgData = imagesList.get(position);
        String nameData = nameList.get(position);
        String phoneData = phoneList.get(position);
        String expData = expList.get(position);
        String catData = catList.get(position);
        String locationData = locationList.get(position);

        holder.name.setText("Name: " + nameData);
        holder.location.setText("City: " + locationData);
        holder.category.setText("Specialization: " + catData);
        holder.exp.setText("Experience: " + expData);
        holder.phone.setText("+91 " + phoneData);

        Glide.with(holder.itemView.getContext()).load(imgData).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return imagesList.size();
    }

    public class MyImagesHolder extends RecyclerView.ViewHolder
    {
        private ImageView imageView;
        private TextView name;
        private TextView location;
        private TextView category;
        private TextView exp;
        private TextView phone;


        public MyImagesHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.pet_setting_image);
            name = itemView.findViewById(R.id.pet_sitter_person_name);
            location = itemView.findViewById(R.id.pet_sitter_location);
            category = itemView.findViewById(R.id.pet_sitter_category);
            exp = itemView.findViewById(R.id.pet_sitter_exp);
            phone = itemView.findViewById(R.id.pet_sitter_phone);
        }
    }
}