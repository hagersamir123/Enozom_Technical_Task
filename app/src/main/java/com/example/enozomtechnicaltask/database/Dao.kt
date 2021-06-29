package com.example.enozomtechnicaltask.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.enozomtechnicaltask.database.entity.Employee
import com.example.enozomtechnicaltask.database.entity.EmployeeAndSkills
import com.example.enozomtechnicaltask.database.entity.Skills


@Dao
interface Dao {

    // insert new Employee
    @Insert
    suspend fun insertAll(vararg users: Employee)

    // insert new Skills
    @Insert
    suspend fun insertSkills(artist: Skills)

    // select all employee data and skills
    @Transaction
    @Query("SELECT * FROM Employee")
    suspend fun getAll(): List<EmployeeAndSkills>

    //select employee
    @Transaction
    @Query("SELECT * FROM Employee WHERE empId = :id")
    suspend fun getByArtistId(id: Int): EmployeeAndSkills

    // select all employee data
    @Query("SELECT * FROM Employee")
    suspend fun getAllEmployee(): List<Employee>

}