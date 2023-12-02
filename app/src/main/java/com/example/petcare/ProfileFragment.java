package com.example.petcare;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileFragment extends Fragment {

    String animalImage;

    private AppCompatButton editProfileBtn;
    private TextView petName;
    private TextView ownerName;
    private TextView petCategory;
    private TextView petBreed;
    private TextView petAge;
    private LinearLayout petInfoLayout;
    private LinearLayout editPetInfoLayout;
    private ImageView editProfileImgBtn;

    private EditText petNameEditText;
    private EditText petOwnerNameEditText;
    private EditText petAgeEditText;
    private EditText petCategoryEditText;
    private EditText petBreedEditText;

    private ImageView personImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        final Boolean[] isEdit = {false};
        animalImage = "";

        petInfoLayout = view.findViewById(R.id.per_info_layout);
        editPetInfoLayout = view.findViewById(R.id.edit_pet_info);
        editProfileImgBtn = view.findViewById(R.id.edit_profile);

        editProfileBtn = view.findViewById(R.id.edit_profile_btn);
        petName = view.findViewById(R.id.pet_name);
        ownerName = view.findViewById(R.id.owner_name);
        petCategory = view.findViewById(R.id.pet_category);
        petBreed = view.findViewById(R.id.pet_breed);
        petAge = view.findViewById(R.id.pet_age);

        petNameEditText = view.findViewById(R.id.pet_name_edit_text);
        petOwnerNameEditText = view.findViewById(R.id.pet_owner_edit_text);
        petAgeEditText = view.findViewById(R.id.pet_age_edit_text);
        petCategoryEditText = view.findViewById(R.id.pet_category_edit_text);
        petBreedEditText = view.findViewById(R.id.pet_breed_edit_text);
        personImage = view.findViewById(R.id.person_image);

        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);

        getSavedData();

        editProfileBtn.setOnClickListener(v-> {
            if (isEdit[0]) {
                String petNameStr = petNameEditText.getText().toString();
                String petOwnerNameStr = petOwnerNameEditText.getText().toString();
                String petCategoryStr = petCategoryEditText.getText().toString();
                String petBreedStr = petBreedEditText.getText().toString();
                String petAgeStr = petAgeEditText.getText().toString();

                if (petNameStr.isEmpty() || petOwnerNameStr.isEmpty() || petCategoryStr.isEmpty() || petBreedStr.isEmpty() || petAgeStr.isEmpty()) {
                    Toast.makeText(requireContext(), "Please enter all details", Toast.LENGTH_SHORT).show();
                    return;
                }

                isEdit[0] = false;
                editProfileBtn.setText("Edit Profile");
                petInfoLayout.setVisibility(View.VISIBLE);
                editPetInfoLayout.setVisibility(View.GONE);
                editProfileImgBtn.setVisibility(View.GONE);

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("petName", petNameStr);
                editor.putString("petOwnerName", petOwnerNameStr);
                editor.putString("petCategory", petCategoryStr);
                editor.putString("petBreed", petBreedStr);
                editor.putString("petAge", petAgeStr);
                editor.putString("petImage", animalImage);
                editor.apply();

                getSavedData();

            } else {
                isEdit[0] = true;
                editProfileBtn.setText("Save Info");
                petInfoLayout.setVisibility(View.GONE);
                editPetInfoLayout.setVisibility(View.VISIBLE);
                editProfileImgBtn.setVisibility(View.VISIBLE);
                getSavedDataForEditText();
            }
        });

        editProfileImgBtn.setOnClickListener(v->{
            showImageDialog(requireContext());
        });


        return view;
    }

    private void getSavedData() {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String retrievedPetName = sharedPreferences.getString("petName", "Edit Pet Name");
        String retrievedPetOwnerName = sharedPreferences.getString("petOwnerName", "Edit Owner Name");
        String retrievedPetCategory = sharedPreferences.getString("petCategory", "Edit Pet Category");
        String retrievedPetBreed = sharedPreferences.getString("petBreed", "Edit Pet Breed");
        String retrievedPetAge = sharedPreferences.getString("petAge", "Edit Pet Age");
        String retrievedPetImage = sharedPreferences.getString("petImage", "");

        petName.setText("Pet Name = " + retrievedPetName);
        ownerName.setText("Pet Owner Name = " + retrievedPetOwnerName);
        petCategory.setText("Pet Pet Category = " + retrievedPetCategory);
        petBreed.setText("Pet Pet Breed = " + retrievedPetBreed);
        petAge.setText("Pet Pet Age = " + retrievedPetAge);

        switch (retrievedPetImage) {
            case "dog":
                personImage.setImageResource(R.drawable.image_dog);
                break;
            case "cat":
                personImage.setImageResource(R.drawable.image_cat);
                break;
            case "rabbit":
                personImage.setImageResource(R.drawable.image_rabbit);
                break;
            case "hamster":
                personImage.setImageResource(R.drawable.image_hamster);
                break;
            case "turtle":
                personImage.setImageResource(R.drawable.image_turtle);
                break;
            default:
                personImage.setImageResource(R.drawable.aa);
                break;
        }
    }

    private void getSavedDataForEditText() {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String retrievedPetName = sharedPreferences.getString("petName", "");
        String retrievedPetOwnerName = sharedPreferences.getString("petOwnerName", "");
        String retrievedPetCategory = sharedPreferences.getString("petCategory", "");
        String retrievedPetBreed = sharedPreferences.getString("petBreed", "");
        String retrievedPetAge = sharedPreferences.getString("petAge", "");

        petNameEditText.setText(retrievedPetName);
        petOwnerNameEditText.setText(retrievedPetOwnerName);
        petCategoryEditText.setText(retrievedPetCategory);
        petBreedEditText.setText(retrievedPetBreed);
        petAgeEditText.setText(retrievedPetAge);
    }

    private void showImageDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Pick Pet");

        View customLayout = LayoutInflater.from(context).inflate(R.layout.dialog_pick_image, null);
        builder.setView(customLayout);

        ImageView imageDog = customLayout.findViewById(R.id.image_dog);
        ImageView imageCat = customLayout.findViewById(R.id.image_cat);
        ImageView imageRabbit = customLayout.findViewById(R.id.image_rabbit);
        ImageView imageHamster = customLayout.findViewById(R.id.image_hamster);
        ImageView imageTurtle = customLayout.findViewById(R.id.image_turtle);


        // Create and show the dialog
        AlertDialog dialog = builder.create();
        imageDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animalImage = "dog";
                personImage.setImageResource(R.drawable.image_dog);
                dialog.dismiss();
            }
        });
        imageCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animalImage = "cat";
                personImage.setImageResource(R.drawable.image_cat);
                dialog.dismiss();
            }
        });
        imageRabbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animalImage = "rabbit";
                personImage.setImageResource(R.drawable.image_rabbit);
                dialog.dismiss();
            }
        });
        imageHamster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animalImage = "hamster";
                personImage.setImageResource(R.drawable.image_hamster);
                dialog.dismiss();
            }
        });
        imageTurtle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animalImage = "turtle";
                personImage.setImageResource(R.drawable.image_turtle);
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}