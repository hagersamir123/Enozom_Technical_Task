package com.example.enozomtechnicaltask.ui.fragment.displyEmployeeFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.enozomtechnicaltask.R
import com.example.enozomtechnicaltask.database.entity.Employee
import com.example.enozomtechnicaltask.databinding.FragmentDisplyEmployeeBinding
import com.example.madarsofttask.ui.viewModel.EmployeeViewModel
import com.example.souqadmin.ui.product.Adapter.EmployeeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DisplyEmployeeFragment : Fragment() {

    private var _binding: FragmentDisplyEmployeeBinding? = null
    private val binding: FragmentDisplyEmployeeBinding get() = _binding!!

    private val viewModel by viewModels<EmployeeViewModel>()

    private lateinit var adapter: EmployeeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_disply_employee, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDisplyEmployeeBinding.bind(view)
        subscribeToLiveData()
        initRecyclerView()
    }



    private fun initRecyclerView() {
        adapter = EmployeeAdapter(requireContext())
        binding.employeeList.adapter = adapter

        adapter.setOnDeleteClickListener = object : EmployeeAdapter.ItemClickListener {
            override fun onClick(emp: Employee, position: Int) {
                viewModel.deleteEmp(emp.empId)
                adapter.notifyItemRemoved(position)
                adapter.changeData(listOf())
                viewModel.getAllEmployee()
            }
        }
    }


    private fun subscribeToLiveData() {
        viewModel.employeeData.observe(viewLifecycleOwner,{
            if (it.isNotEmpty()) {
                adapter.changeData(it)
            }
        })
    }


}