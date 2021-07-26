package com.example.camerxtests;

public class Publication {

    private int id;

    private String description;

    private boolean favorite;

    private String creationDate;

    private String modificationDate;

    private String imageFileLocation;

    private String audioFileLocation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getImageFileLocation() {
        return imageFileLocation;
    }

    public void setImageFileLocation(String imageFileLocation) {
        this.imageFileLocation = imageFileLocation;
    }

    public String getAudioFileLocation() {
        return audioFileLocation;
    }

    public void setAudioFileLocation(String audioFileLocation) {
        this.audioFileLocation = audioFileLocation;
    }
}
