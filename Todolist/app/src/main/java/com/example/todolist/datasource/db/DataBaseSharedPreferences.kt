package com.example.todolist.datasource.db

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.model.Task
import  com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class DataBaseSharedPreferences(val activity : AppCompatActivity) {

    private val NAME_SHARED = "TO_DO_LIST_APP"
    private val LIST_TASK = "LIST_TASK"
    private val EMPTY_STRING = ""

    fun saveTask(tasks : List<Task>){

        val editor = getPrefs().edit()
        val json = Gson().toJson(tasks)

        editor.putString(LIST_TASK, json)
        editor.apply()
    }

    fun listTask() : List<Task>{
        val json = getPrefs().getString(LIST_TASK, EMPTY_STRING) ?: EMPTY_STRING

        if(json.isNotEmpty()){
            val turnsType = object : TypeToken<List<Task>>() {}.type
            val task: List<Task> = Gson().fromJson(json, turnsType)
            return task
        }

        return listOf()

    }

    private fun getPrefs() : SharedPreferences{
        return activity.getSharedPreferences(NAME_SHARED, Context.MODE_PRIVATE)
    }

}