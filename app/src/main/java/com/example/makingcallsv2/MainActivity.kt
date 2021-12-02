package com.example.makingcallsv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = findViewById<TextView>(R.id.output_field)
        val getButton = findViewById<Button>(R.id.get_button)
        val deleteButton = findViewById<Button>(R.id.delete_button)
        val postButton = findViewById<Button>(R.id.post_button)

        val model: ToDoViewModel by viewModels()
        model.todoResponse.observe(this) {
            binding.text = model.todoResponse.value
        }

        getButton.setOnClickListener {
            model.getTodoItems()
        }

        deleteButton.setOnClickListener {
            model.deleteTodoItem(1)
        }

        postButton.setOnClickListener {
            val toDoItem = ToDoItem(99, "learnCoding", false)
            model.postToDoItem(toDoItem)

        }
    }

}