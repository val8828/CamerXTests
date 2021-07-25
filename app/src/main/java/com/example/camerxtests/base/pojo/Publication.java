package com.example.camerxtests.base.pojo;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId", onDelete = CASCADE))
public class Publication {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("userId")
    @Expose
    private int userId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("commonDescription")
    @Expose
    private String commonDescription;
    @SerializedName("favorite")
    @Expose
    private boolean favorite;
    @SerializedName("creationDate")
    @Expose
    private String creationDate;
    @SerializedName("modificationDate")
    @Expose
    private String modificationDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommonDescription() {
        return commonDescription;
    }

    public void setCommonDescription(String commonDescription) {
        this.commonDescription = commonDescription;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }

}
