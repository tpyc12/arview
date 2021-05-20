package com.example.twitchinfo.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.twitchinfo.pojo.MovieInfo

@Dao
interface MovieInfoDao {

    @Query("SELECT * FROM movies")
    fun getAllMovies(): LiveData<List<MovieInfo>>

    @Query("DELETE FROM movies")
    fun deleteAllMovies()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMoviesList(moviesList: List<MovieInfo>)

}