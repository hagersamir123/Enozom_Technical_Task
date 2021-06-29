package com.example.enozomtechnicaltask.ui.fragment.addEmployeeFragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.enozomtechnicaltask.R
import com.example.enozomtechnicaltask.database.entity.Employee
import com.example.enozomtechnicaltask.database.entity.Skills
import com.example.enozomtechnicaltask.databinding.FragmentAddEmployeeBinding
import com.example.enozomtechnicaltask.ui.adapter.SkillsAdapter
import com.example.madarsofttask.ui.viewModel.EmployeeViewModel
import com.example.souqadmin.ui.product.Adapter.EmployeeAdapter
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList


@AndroidEntryPoint
class AddEmployeeFragment : Fragment(R.layout.fragment_add_employee) {

    private lateinit var _binding:FragmentAddEmployeeBinding
    private val binding:FragmentAddEmployeeBinding get() = _binding
    private lateinit var skillsList : MutableList<String>
    private var filePath: Uri? = null
    private var imageUri: String = ""
    private lateinit var adapter: SkillsAdapter
    private var firebaseStore: FirebaseStorage? = null
    private val PICK_IMAGE_REQUEST = 71
    private var storageReference: StorageReference? = null
    private lateinit var emp : Employee

    private val viewModel by viewModels<EmployeeViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().getReference()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAddEmployeeBinding.bind(view)
        var id : Int
        val name = binding.userNameTV.editText!!.text.toString()
        subscribeToLiveData()


        binding.saveBtn.setOnClickListener {
            if (validateInputs(binding.userNameTV) &&
                validateInputs(binding.mailTv) &&
                validateInputs(binding.Skillstv)
            ){

                emp =  Employee(
                    employeeName = binding.userNameTV.editText!!.text.toString(),
                    mail = binding.mailTv.editText!!.text.toString() ,
                    image = filePath.toString()
                )
                viewModel.insertEmployee(emp)



                val skills = Skills(
                    skillName = binding.Skillstv.editText!!.text.toString(),
                    emp_id = 1
                )

                binding.ddSkillsBtn.setOnClickListener {
                    skillsList.add((binding.Skillstv.editText!!.text.toString()))
                    viewModel.insertSkills(skills)
                    initAutoCompleteGenderText()
                }
                binding.saveBtn.animate().apply {
                    duration = 1000
                    rotationXBy(360f).start()
                }

                showSnackBar(view = it)
            }
        }

        binding.displayBtn.setOnClickListener {
                findNavController().navigate(R.id.action_addEmployeeFragment_to_displyEmployeeFragment)
        }

        binding.image.setOnClickListener{
            launchGallery()
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
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, skillsList)
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


    private fun launchGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE_REQUEST) {
            filePath = data?.data
            binding.image.setImageURI(filePath)
        }
    }


}