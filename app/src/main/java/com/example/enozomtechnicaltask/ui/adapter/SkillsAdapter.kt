package com.example.enozomtechnicaltask.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.example.enozomtechnicaltask.R
import com.example.enozomtechnicaltask.database.entity.Skills
import com.example.enozomtechnicaltask.databinding.ItemSkillsBinding

class SkillsAdapter : RecyclerView.Adapter<SkillsAdapter.ViewHolder>() {
    private var skills = arrayListOf<Skills>()

    fun changeData(skills : ArrayList<Skills>){
        this.skills = skills
        notifyDataSetChanged()
    }
    inner class ViewHolder(private val binding:ItemSkillsBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.skill.text = skills[position].skillName
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSkillsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(position)

    override fun getItemCount(): Int {
        return skills.size
    }
}
