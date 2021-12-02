package com.example.makingcallsv2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception

private const val TAG = "TodoViewModel"

class ToDoViewModel : ViewModel() {
    private val _todoResponse: MutableLiveData<String> = MutableLiveData()
    val todoResponse: LiveData<String>
        get() = _todoResponse

    init {
        getTodoItems()
    }

    fun getTodoItems() {
        viewModelScope.launch {
            _todoResponse.value = try {
                ToDoApi.retrofitService.getTodos()
            }catch(e : Exception){
                e.message.toString()
            }
        }
    }

    fun deleteTodoItem(todoId: Int) {
        viewModelScope.launch {
            ToDoApi.retrofitService.deleteItem(todoId)
            _todoResponse.value = try {
                "deleteTodoItem: ${todoId} deleted"
            }catch (e: Exception){
                e.message.toString()
            }
        }
    }
    fun postToDoItem(toDoItem: ToDoItem) {
        viewModelScope.launch {
            try{
                ToDoApi.retrofitService.postItem(toDoItem)
                _todoResponse.value = "postTodoItem: ${toDoItem} posted"
            }catch (e : Exception){
                e.message.toString()
            }
        }
    }
}