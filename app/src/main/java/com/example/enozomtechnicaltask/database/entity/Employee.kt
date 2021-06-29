package com.example.enozomtechnicaltask.database.entity

import androidx.room.*
import java.io.Serializable


@Entity
data class Employee(
    @PrimaryKey(autoGenerate = true)
    val empId: Int = 0,
    @ColumnInfo
    val employeeName: String = "",
    val image: String,
    val mail: String,

)

@Entity(tableName = "Skills" ,
    foreignKeys = [ForeignKey(
        entity = Employee::class,
        parentColumns = arrayOf("empId"),
        childColumns = arrayOf("emp_id"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Skills(
    @PrimaryKey(autoGenerate = true)
    val skillId: Int = 0,
    val skillName: String,
    @ColumnInfo(index = true)
    val emp_id: Int

)
@Entity
data class EmployeeAndSkills(
    @Embedded
    val employee: Employee,
    @Relation(
        parentColumn = "empId",
        entityColumn = "emp_id"
    )
    val albums: List<Skills>
)