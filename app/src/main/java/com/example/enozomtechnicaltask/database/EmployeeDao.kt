package com.example.enozomtechnicaltask.database

import android.icu.text.Replaceable
import androidx.room.*
import com.example.enozomtechnicaltask.database.entity.Employee
import com.example.enozomtechnicaltask.database.entity.EmployeeAndSkills
import com.example.enozomtechnicaltask.database.entity.Skills

@Dao
interface EmployeeDao {


    // insert new Employee
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployee(vararg employee: Employee)

    // insert new Skills
    @Insert(onConflict = OnConflictStrategy.REPLACE)
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

    @Query("DELETE FROM Employee WHERE empId = :id")
    suspend fun deleteEmp(id : Int)

    // select all employee data
    @Query("SELECT empId FROM Employee where employeeName = :name")
    suspend fun getEmployeeId(name : String): Int
}