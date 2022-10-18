package com.example.jsonassignmentbmb;

import com.google.gson.annotations.SerializedName;

public class Model
{
    @SerializedName("name") private String engineName;
    @SerializedName("rating") private Integer rating;
    @SerializedName("description") private String description;

    public Model(final String name, final Integer rating, final String description)
    {
        this.engineName = name;
        this.rating = rating;
        this.description = description;
    }

    public String getName() {return engineName;}
    public Integer getRating() {return rating;}
    public String getDescription() {return description;}

    public void setName(final String name) {this.engineName = name;}
    public void setRating(final Integer year) {this.rating = year;}
    public void setDescription(final String recentConsole) {this.description = recentConsole;}
}
