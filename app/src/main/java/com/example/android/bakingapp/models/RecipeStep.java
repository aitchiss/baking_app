package com.example.android.bakingapp.models;

/**
 * Created by suzanneaitchison on 14/11/2017.
 */

public class RecipeStep {

    private int id;
    private String shortDescription;
    private String description;
    private String videoUrl;
    private String thumbnailUrl;

    public RecipeStep(int id, String shortDescription, String description, String videoUrl, String thumbnailUrl){
        this.id = id;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoUrl = videoUrl;
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getId() {
        return id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

}
