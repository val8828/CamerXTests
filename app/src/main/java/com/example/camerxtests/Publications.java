package com.example.camerxtests;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Publications implements Serializable {
    List<Publication> publications;
    public void addPublication(Publication publication){
        if(publications == null){
            publications = new ArrayList<>();
        }

    }
}
