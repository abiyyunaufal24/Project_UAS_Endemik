package com.example.projectuas.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "endemik")
public class Endemik {
    @PrimaryKey
    @NonNull
    @SerializedName("id")
    public String id;

    @SerializedName("nama")
    public String name;

    @SerializedName("tipe")
    public String category; // "Hewan" or "Tumbuhan"

    @SerializedName("deskripsi")
    public String description;

    @SerializedName("foto")
    public String imageRes; // This is now a URL

    @SerializedName("asal")
    public String region;

    @SerializedName("nama_latin")
    public String scientificName;

    public Endemik(@NonNull String id, String name, String category, String description, String imageRes, String region) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.imageRes = imageRes;
        this.region = region;
    }
}
