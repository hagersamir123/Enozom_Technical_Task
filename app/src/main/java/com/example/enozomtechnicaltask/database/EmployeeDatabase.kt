package com.example.enozomtechnicaltask.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.enozomtechnicaltask.database.entity.Employee

@Database(entities = [Employee::class], version = 1)
abstract class EmployeeDatabase : RoomDatabase() {
    abstract fun employeeDao(): Dao

}