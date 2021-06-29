package com.example.madarsofttask.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.enozomtechnicaltask.database.entity.Employee
import com.example.enozomtechnicaltask.database.entity.EmployeeAndSkills
import com.example.enozomtechnicaltask.database.entity.Skills
import com.example.madarsofttask.repository.EmployeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel @Inject constructor(private val repository: EmployeeRepository):ViewModel(){
    private var _employeeData = MutableLiveData<List<Employee>>()
    val employeeData: LiveData<List<Employee>> get() = _employeeData

    private var _alleData = MutableLiveData<EmployeeAndSkills>()
    val allData: LiveData<EmployeeAndSkills> get() = _alleData

    var id: Int = 0

    init {
        getAllEmployee()
    }

    fun insertSkills(skill : Skills) = viewModelScope.launch{
        repository.insertSkills(skill)
    }

     fun getAllEmployee() = viewModelScope.launch {
        _employeeData.value = repository.getallEmployee()
    }
     fun insertEmployee(emp:Employee) = viewModelScope.launch {
         repository.insertEmployee(emp)
    }
    fun getAllData(id : Int) = viewModelScope.launch {
        _alleData.value = repository.getEmployeeData(id = id) as EmployeeAndSkills
     }

    fun getId(name : String) = viewModelScope.launch{
        id = repository.getEmployeeId(name) as Int
    }
}