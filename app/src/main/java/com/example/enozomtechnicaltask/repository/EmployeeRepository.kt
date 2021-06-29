package com.example.madarsofttask.repository

import com.example.enozomtechnicaltask.database.EmployeeDao
import com.example.enozomtechnicaltask.database.entity.Employee
import com.example.enozomtechnicaltask.database.entity.Skills
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EmployeeRepository @Inject constructor(private val dao: EmployeeDao) {

    suspend fun insertEmployee(emp:Employee){
         dao.insertEmployee(emp)
    }

    suspend fun getEmployeeId(name : String){
        dao.getEmployeeId(name)
    }

    suspend fun getEmployeeData(id :Int){
        dao.getByArtistId(id = id)
    }

    suspend fun insertSkills(skillList:Skills){
        dao.insertSkills(skillList)
    }

    suspend fun getallEmployee():List<Employee>{
        return dao.getAllEmployee()
    }

}