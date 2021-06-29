package com.example.souqadmin.ui.product.Adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.enozomtechnicaltask.database.entity.Employee
import com.example.enozomtechnicaltask.databinding.EmployeeRvBinding


class EmployeeAdapter(val context: Context) : RecyclerView.Adapter<EmployeeAdapter.ViewHolder>() {
    private var items = listOf<Employee>()
    var setOnDeleteClickListener: ItemClickListener? = null
    var setOnEditClickListener: ItemClickListener? = null
    private lateinit var _viewBinding: EmployeeRvBinding
    val viewBinding: EmployeeRvBinding get() = _viewBinding

    fun changeData(newData: List<Employee>, clearOldData: Boolean = false) {
        if (clearOldData) {
            items = newData
            notifyDataSetChanged()
        }
        val oldData = items
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(
            EmployeeAdapter.ItemsDiffCallback(
                oldData,
                newData
            )

        )
        items = newData
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(val binding: EmployeeRvBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun binding(item: Employee, position: Int) = with(itemView) {

            Glide.with(context)
                .load(item.image)
                .centerCrop()
                .into(binding.employeeImg)

            binding.name.text = item.employeeName
            binding.mail.text = item.mail

            if (setOnDeleteClickListener != null) {
                binding.deleteBtn.setOnClickListener{
                    notifyItemRemoved(position)
                    setOnDeleteClickListener!!.onClick(emp = item , position)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        _viewBinding =
            EmployeeRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(
            _viewBinding
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.binding(items.get(position), position)


    class ItemsDiffCallback(
        private val oldData: List<Employee>,
        private val newData: List<Employee>
    ) : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldData[oldItemPosition].employeeName == newData[newItemPosition].employeeName
        }

        override fun getOldListSize(): Int {
            return oldData.size
        }

        override fun getNewListSize(): Int {
            return newData.size
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldData[oldItemPosition] == newData[newItemPosition]
        }
    }


    interface ItemClickListener{
        fun onClick(emp:Employee , position : Int)
    }
}