package com.example.yandextodo.Interfaces


import com.example.yandextodo.Model.TodoListResponse
import com.example.yandextodo.Model.TodoOneItemResponse
import com.example.yandextodo.Model.TodoPostPutDeleteItemRequest
import com.example.yandextodo.Model.UpdateListRequest

interface ToDoItemRepository {
    suspend fun getItemById(userId: String): Result<TodoOneItemResponse>
    suspend fun getAllToDoItems(): Result<TodoListResponse>
    suspend fun addToDoItem(
        todoPostPutDeleteItemRequest: TodoPostPutDeleteItemRequest,
        revision: Int
    ): Result<TodoPostPutDeleteItemRequest>

    suspend fun deleteToDoItemById(id: String, revision: Int): Result<TodoPostPutDeleteItemRequest>
    suspend fun updateToDoItemById(
        id: String,
        todoPostPutDeleteItemRequest: TodoPostPutDeleteItemRequest,
        revision: Int
    ): Result<TodoPostPutDeleteItemRequest>

    suspend fun updateList(
        updateListRequest: UpdateListRequest,
        revision: Int
    ): Result<TodoListResponse>
}