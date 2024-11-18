package com.example.yandextodo


import android.app.Application
import com.example.yandextodo.model.TodoItemRepository
import com.example.yandextodo.repository.ToDoItemRepositoryImp

class TodoApp : Application() {
    val todoItemRepository: TodoItemRepository by lazy { ToDoItemRepositoryImp() as TodoItemRepository }

    override fun onCreate() {
        super.onCreate()
    }
}
