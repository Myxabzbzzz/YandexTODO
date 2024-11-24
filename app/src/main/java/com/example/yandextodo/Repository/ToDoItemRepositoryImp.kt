package com.example.yandextodo.Repository


import android.view.WindowManager.BadTokenException
import com.example.yandextodo.APIHandler.RetrofitInstance
import com.example.yandextodo.Interfaces.ToDoItemRepository
import com.example.yandextodo.Model.TodoListResponse
import com.example.yandextodo.Model.TodoOneItemResponse
import com.example.yandextodo.Model.TodoPostPutDeleteItemRequest
import com.example.yandextodo.Model.UpdateListRequest

class ToDoItemRepositoryImp : ToDoItemRepository {
    private val apiService = RetrofitInstance.api

    override suspend fun getAllToDoItems(): Result<TodoListResponse> {
        return try {
            val response = apiService.getToDoList()
            if (response.status == "ok") {
                Result.success(response)
            } else {
                throw BadTokenException()
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun addToDoItem(
        todoPostPutDeleteItemRequest: TodoPostPutDeleteItemRequest,
        revision: Int
    ): Result<TodoPostPutDeleteItemRequest> {
        return try {
            val response = apiService.postItem(todoPostPutDeleteItemRequest, revision)
            if (response.status == "ok") {
                Result.success(response)
            } else {
                throw BadTokenException()
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteToDoItemById(
        id: String,
        revision: Int
    ): Result<TodoPostPutDeleteItemRequest> {
        return try {
            val response = apiService.deleteItemById(id, revision)
            if (response.status == "ok") {
                Result.success(response)
            } else {
                throw BadTokenException()
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateToDoItemById(
        id: String,
        todoPostPutDeleteItemRequest: TodoPostPutDeleteItemRequest,
        revision: Int
    ): Result<TodoPostPutDeleteItemRequest> {
        return try {
            val response = apiService.updateItemById(id, todoPostPutDeleteItemRequest, revision)
            if (response.status == "ok") {
                Result.success(response)
            } else {
                throw BadTokenException()
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateList(
        updateListRequest: UpdateListRequest,
        revision: Int
    ): Result<TodoListResponse> {
        return try {
            val response = apiService.updateList(updateListRequest, revision)
            if (response.status == "ok") {
                Result.success(response)
            } else {
                throw BadTokenException()
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getItemById(userId: String): Result<TodoOneItemResponse> {
        return try {
            val response = apiService.getItemById(userId)
            if (response.status == "ok") {
                Result.success(response)
            } else {
                throw BadTokenException()
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}