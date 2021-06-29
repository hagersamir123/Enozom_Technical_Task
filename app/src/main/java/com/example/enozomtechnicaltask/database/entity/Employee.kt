package com.example.enozomtechnicaltask.database.entity

import androidx.room.*

@Entity
data class Employee(
    @PrimaryKey(autoGenerate = true)
    val empId: Int = 0,
    @ColumnInfo
    val employeeName: String = "",
    val image: String,
    val mail: String,
)

@Entity(
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

data class EmployeeAndSkills(
    @Embedded
    val employee: Employee,
    @Relation(
        parentColumn = "id",
        entityColumn = "artist"
    )
    val albums: List<Skills>
)