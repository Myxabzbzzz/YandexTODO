package com.example.yandextodo.repository

import android.view.WindowManager.BadTokenException
import com.example.yandextodo.interfaces.ToDoApiService
import com.example.yandextodo.interfaces.ToDoItemRepository
import com.example.yandextodo.model.*
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import okhttp3.MediaType.Companion.toMediaType

class ToDoItemRepositoryImp : ToDoItemRepository {

    private val json = Json {
        ignoreUnknownKeys = true // Игнорировать неизвестные поля
        isLenient = true // Допускает более гибкую обработку JSON
    }

    private val okHttpClient = OkHttpClient.Builder()
        .certificatePinner(
            CertificatePinner.Builder()
                .add("hive.mrdekk.ru", "sha256/NYbU7PBwV4y9J67c4guWTki8FJ+uudrXL0a4V4aRcrg=")
                .build()
        )
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .addInterceptor { chain ->
            val request: Request = chain.request().newBuilder()
                .addHeader("Authorization", "Arathorn")
                .build()
            chain.proceed(request)
        }
        .build()

    @OptIn(ExperimentalSerializationApi::class)
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://hive.mrdekk.ru/todo/")
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    private val apiService: ToDoApiService = retrofit.create(ToDoApiService::class.java)

    override suspend fun getAllToDoItems(): Result<TodoListResponse> {
        return try {
            val response = apiService.getToDoList()
            if (response.status == "ok") {
                Result.success(response)
            } else {
                Result.failure(Exception("API returned error status: ${response.status}"))
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
                Result.failure(Exception("API returned error status: ${response.status}"))
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
                Result.failure(Exception("API returned error status: ${response.status}"))
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
                Result.failure(Exception("API returned error status: ${response.status}"))
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
                Result.failure(Exception("API returned error status: ${response.status}"))
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
                Result.failure(Exception("API returned error status: ${response.status}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}