package com.example.yandextodo


import android.app.Application
import com.example.yandextodo.model.TodoItemRepository
import com.example.yandextodo.model.TodoItemsRepositoryImpl

class TodoApp: Application(){
    val todoItemRepository: TodoItemRepository by lazy { TodoItemsRepositoryImpl() }
    override fun onCreate() {
        super.onCreate()

    }
}