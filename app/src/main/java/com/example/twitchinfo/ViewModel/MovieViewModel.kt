package com.example.twitchinfo.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.twitchinfo.api.ApiFactory
import com.example.twitchinfo.database.AppDatabase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    val db = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()

    val movieList = db.movieInfoDao().getAllMovies()

    init {
        loadData(1)
    }

    fun loadData(page: Int) {
        val disposable = ApiFactory.apiService.getMovieList(lang = "ru-RU", page = page)
            .map { it.results }
            .subscribeOn(Schedulers.io())
            .subscribe({
                db.movieInfoDao().insertMoviesList(it)
                Log.d("TAG", "Success${it}")
            }, {
                Log.d("TAG", "Failure " + it.message.toString())
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}