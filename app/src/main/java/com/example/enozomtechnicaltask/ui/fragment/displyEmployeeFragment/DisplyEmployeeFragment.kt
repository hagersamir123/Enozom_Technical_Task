package com.example.enozomtechnicaltask.ui.fragment.displyEmployeeFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.enozomtechnicaltask.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DisplyEmployeeFragment : Fragment() {


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


}