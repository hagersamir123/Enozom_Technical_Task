package com.example.enozomtechnicaltask.ui.fragment.addEmployeeFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.enozomtechnicaltask.R
import com.example.enozomtechnicaltask.database.entity.Employee
import com.example.enozomtechnicaltask.database.entity.Skills
import com.example.enozomtechnicaltask.databinding.FragmentAddEmployeeBinding
import com.example.madarsofttask.ui.viewModel.EmployeeViewModel
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddEmployeeFragment : Fragment(R.layout.fragment_add_employee) {

    private var _binding:FragmentAddEmployeeBinding?=null
    private val binding:FragmentAddEmployeeBinding get() = _binding!!
    private lateinit var skillsList : List<String>

    private val viewModel by viewModels<EmployeeViewModel>()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var id : Int
        val name = binding.userNameTV.editText!!.text.toString()
        subscribeToLiveData()

        _binding = FragmentAddEmployeeBinding.bind(view)
        initAutoCompleteGenderText()

        binding.saveBtn.setOnClickListener {
            if (validateInputs(binding.userNameTV) &&
                validateInputs(binding.mailTv) &&
                validateInputs(binding.Skillstv)
            ){
                val emp =  Employee(
                    employeeName = binding.userNameTV.editText!!.text.toString(),
                    mail = binding.mailTv.editText!!.text.toString() ,
                    image = ""
                )
                id = viewModel.getId(name) as Int
                val skills = Skills(
                    emp_id = id,
                    skillName = binding.Skillstv.editText!!.text.toString()
                )

                binding.saveBtn.animate().apply {
                    duration = 1000
                    rotationXBy(360f).start()
                }
                viewModel.insertEmployee(emp = emp)
                viewModel.insertSkills(skills)
                showSnackBar(view = it)
            }
        }

        binding.displayBtn.setOnClickListener {
                findNavController().navigate(R.id.action_addEmployeeFragment_to_displyEmployeeFragment)
        }


    }



    override fun onResume() {
        super.onResume()
        viewModel.getAllEmployee()
    }

    private fun subscribeToLiveData() {
        viewModel.employeeData.observe(viewLifecycleOwner,{
            if (it.isEmpty())
                binding.displayBtn.visibility = View.GONE
            else
                binding.displayBtn.visibility = View.VISIBLE
        })
    }

    private fun showSnackBar(view: View) {
        Snackbar.make(view,getString(R.string.data_saved), Snackbar.LENGTH_SHORT).addCallback(object :
            BaseTransientBottomBar.BaseCallback<Snackbar>() {
            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                super.onDismissed(transientBottomBar, event)
                view.findNavController().navigate(R.id.action_addEmployeeFragment_to_displyEmployeeFragment)
            }
        }).show()
    }

    private fun initAutoCompleteGenderText() {
        val items = resources.getStringArray(R.array.skills_list)
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
        (binding.Skillstv.editText as? AutoCompleteTextView)?.setAdapter(adapter)

    }

    private fun validateInputs(inputLayout: TextInputLayout):Boolean{
        var content = inputLayout.editText!!.text.toString()
        if (content.isEmpty() && content.isBlank()){
            inputLayout.isErrorEnabled = true
            inputLayout.error =getString(R.string.input_required)
            return false
        }else{
            inputLayout.isErrorEnabled = false
        }
        return true
    }






}