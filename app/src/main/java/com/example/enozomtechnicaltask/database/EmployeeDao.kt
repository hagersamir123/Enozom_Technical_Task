package com.example.enozomtechnicaltask.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.enozomtechnicaltask.database.entity.Employee
import com.example.enozomtechnicaltask.database.entity.EmployeeAndSkills
import com.example.enozomtechnicaltask.database.entity.Skills

@Dao
interface EmployeeDao {


    // insert new Employee
    @Insert
    suspend fun insertEmployee(vararg employee: Employee)

    // insert new Skills
    @Insert
    suspend fun insertSkills(skills: Skills)

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

    // select all employee data
    @Query("SELECT empId FROM Employee where employeeName = :name")
    suspend fun getEmployeeId(name : String): Int
}