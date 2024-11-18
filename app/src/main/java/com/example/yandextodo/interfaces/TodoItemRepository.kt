package com.example.yandextodo.interfaces

import com.example.yandextodo.model.TodoItem
import com.example.yandextodo.model.TodoListResponse
import com.example.yandextodo.model.TodoOneItemResponse
import com.example.yandextodo.model.TodoPostPutDeleteItemRequest
import com.example.yandextodo.model.UpdateListRequest

interface ToDoItemRepository {
    suspend fun getItemById(userId: String): Result<TodoOneItemResponse>
    suspend fun getAllToDoItems(): Result<TodoListResponse>
    suspend fun addToDoItem(todoPostPutDeleteItemRequest: TodoPostPutDeleteItemRequest, revision : Int) : Result<TodoPostPutDeleteItemRequest>
    suspend fun deleteToDoItemById(id: String, revision : Int) : Result<TodoPostPutDeleteItemRequest>
    suspend fun updateToDoItemById(id: String, todoPostPutDeleteItemRequest: TodoPostPutDeleteItemRequest, revision : Int)  : Result<TodoPostPutDeleteItemRequest>
    suspend fun updateList(updateListRequest: UpdateListRequest, revision : Int) : Result<TodoListResponse>
}