package com.example.enozomtechnicaltask.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.enozomtechnicaltask.database.entity.Employee
import com.example.enozomtechnicaltask.database.entity.Skills

@Database(entities = [Employee::class , Skills::class], version = 2)
abstract class EmployeeDatabase : RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao

}