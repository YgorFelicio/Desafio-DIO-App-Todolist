package com.example.todolist.datasource

import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.datasource.db.DataBaseSharedPreferences
import com.example.todolist.model.Task

object TaskDataSource {
    private val list = mutableListOf<Task>()

    fun getList(activity: AppCompatActivity) : List<Task>{
        val tasks = DataBaseSharedPreferences(activity).listTask()
        list.clear()
        list.addAll(tasks)
        return list.toList()
    }

    fun insertTask(task: Task, activity: AppCompatActivity) {
        if (task.id == 0) {
            list.add(task.copy(id = list.size + 1))
        } else {
            list.remove(task)
            list.add(task)
        }

        DataBaseSharedPreferences(activity).saveTask(list)

    }

    fun findById(taskId: Int) = list.find { it.id == taskId }

    fun deleteTask(task: Task, activity: AppCompatActivity) {
        list.remove(task)
        DataBaseSharedPreferences(activity).saveTask(list)
    }
}