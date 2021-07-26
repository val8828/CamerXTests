package com.example.camerxtests;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {
    MutableLiveData<ArrayList<Publication>> publicationLiveData;
    ArrayList<Publication> publicationArrayList;

    public MainViewModel() {
        publicationLiveData = new MutableLiveData<>();
        init();
    }

    public MutableLiveData<ArrayList<Publication>> getPublicationMutableLiveData() {
        return publicationLiveData;
    }

    public void init(){
        populateList();
        publicationLiveData.setValue(publicationArrayList);
    }

    public void populateList(){

        Publication publication1 = new Publication();
        Publication publication2 = new Publication();
        Publication publication3 = new Publication();
        Publication publication4 = new Publication();

        publication1.setDescription("Darknight");
        publication2.setDescription("Darknight");
        publication3.setDescription("Darknight");
        publication4.setDescription("Darknight");

        publication1.setId(1);
        publication1.setId(2);
        publication1.setId(3);
        publication1.setId(4);

        publicationArrayList = new ArrayList<>();
        publicationArrayList.add(publication1);
        publicationArrayList.add(publication2);
        publicationArrayList.add(publication3);
        publicationArrayList.add(publication4);
    }
}


