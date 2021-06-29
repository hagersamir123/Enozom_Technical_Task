package com.example.enozomtechnicaltask.di

import android.app.Application
import androidx.room.Room
import com.example.enozomtechnicaltask.database.EmployeeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {



    @Provides
    @Singleton
    fun provideDataBase(application : Application) : EmployeeDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            EmployeeDatabase::class.java,
            "EMPLOYEE-DB"
        )
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    fun provideTaskDao(db: EmployeeDatabase) = db.employeeDao()
}