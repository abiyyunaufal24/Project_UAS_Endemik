package com.example.projectuas.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorit")
public class Favorit {
    @PrimaryKey
    @NonNull
    public String endemikId;

    public Favorit(@NonNull String endemikId) {
        this.endemikId = endemikId;
    }
}
