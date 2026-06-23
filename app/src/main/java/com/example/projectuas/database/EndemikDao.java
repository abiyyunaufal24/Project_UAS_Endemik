package com.example.projectuas.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EndemikDao {
    @Query("SELECT * FROM endemik")
    LiveData<List<Endemik>> getAllEndemik();

    @Query("SELECT * FROM endemik WHERE category = :category")
    LiveData<List<Endemik>> getEndemikByCategory(String category);

    @Query("SELECT * FROM endemik WHERE name LIKE '%' || :query || '%'")
    LiveData<List<Endemik>> searchEndemik(String query);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertEndemik(Endemik... endemik);

    @Query("SELECT * FROM endemik WHERE id IN (SELECT endemikId FROM favorit)")
    LiveData<List<Endemik>> getFavoriteEndemik();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavorite(Favorit favorit);

    @Delete
    void deleteFavorite(Favorit favorit);

    @Query("SELECT EXISTS(SELECT 1 FROM favorit WHERE endemikId = :id)")
    LiveData<Boolean> isFavorite(String id);
}
