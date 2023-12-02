package com.example.petcare;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class PetSitterFragment extends Fragment {

    private RecyclerView petSitterRv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pet_sitter, container, false);

        petSitterRv = view.findViewById(R.id.petSitterRv);
        petSitterRv.setLayoutManager(new GridLayoutManager(requireContext(), 1));
        final PetSitterAdapter adapter = new PetSitterAdapter();
        petSitterRv.setAdapter(adapter);

        ArrayList<String> imageList = new ArrayList<>();
        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<String> phoneList = new ArrayList<>();
        ArrayList<String> expList = new ArrayList<>();
        ArrayList<String> catList = new ArrayList<>();
        ArrayList<String> locationList = new ArrayList<>();

        //single item
        imageList.add("https://assets.petbacker.com/user-images/640/u_2fe9840443.5f0f174169be9.jpg");
        nameList.add("Kartikey");
        phoneList.add("987653210");
        expList.add("2 years");
        catList.add("Dogs, Cats");
        locationList.add("Delhi");

        //single item
        imageList.add("https://assets.petbacker.com/user-images/640/e54d9d4c2e.5d1e33e19abfa.jpg");
        nameList.add("Apoorva");
        phoneList.add("9983369431");
        expList.add("3 years");
        catList.add("Dogs, Cats, Rabbit");
        locationList.add("Delhi");

        imageList.add("https://assets.petbacker.com/user-images/320/u_a33bd85845.64df4cf837486.jpg");
        nameList.add("Mahika");
        phoneList.add("8588847247");
        expList.add("1 year");
        catList.add("Cats");
        locationList.add("Delhi");

        imageList.add("https://assets.petbacker.com/user-images/640/u_2bba0683b8.60b72c752a10c.jpg");
        nameList.add("Ankita Chauhan");
        phoneList.add("9999554455");
        expList.add("2 years");
        catList.add("Dogs, Turtle");
        locationList.add("Delhi");

        imageList.add("https://assets.petbacker.com/user-images/320/u_4deba7a4df.62c3a5e509f88.jpg");
        nameList.add("Aaditya Gupta");
        phoneList.add("8703134667");
        expList.add("5 years");
        catList.add("Dogs, Rabbits, Hamsters");
        locationList.add("Delhi");

        imageList.add("https://assets.petbacker.com/user-images/640/u_2a044cc01b.636b83158bd22.jpg");
        nameList.add("Aditi Khreta");
        phoneList.add("9873001493");
        expList.add("7 years");
        catList.add("Dogs,Turtle, Rabbits, Hamsters");
        locationList.add("Delhi");

        imageList.add("https://assets.petbacker.com/user-images/320/u_4f3b6a37db.626a337a537cd.jpg");
        nameList.add("Anmol Khurana");
        phoneList.add("8368943391");
        expList.add("1+ years");
        catList.add("Dogs, Hamsters");
        locationList.add("Delhi");


        adapter.setListItem(imageList, nameList, phoneList, expList, catList, locationList);



        return view;
    }
}