package com.example.yandextodo.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.yandextodo.R
import java.util.Date
import com.example.yandextodo.model.TodoListResponse.TodoItemResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



data class TodoItem(
    val id: String,
    val text: String,
    val importance: TodoImportance = TodoImportance.DEFAULT,
    val deadline: Date? = null,
    val isCompleted: Boolean = false,
    val createdAt: Date = Date(),
    val modifiedAt: Date? = null
)

enum class TodoImportance(
    @StringRes val title: Int,
    @DrawableRes val logo: Int? = null,
) {
    DEFAULT(
        title = R.string.priority_default
    ),
    LOW(
        title = R.string.priority_low,
        logo = R.drawable.arrow
    ),
    HIGH(
        title = R.string.priority_high,
        logo = R.drawable.priority
    )
}



@Serializable
data class TodoListResponse(
    @SerialName("status")
    val status: String,

    @SerialName("list")
    val list: List<TodoItemResponse>,

    @SerialName("revision")
    val revision: Int
)
{
    @Serializable
    data class TodoItemResponse(
        @SerialName("id")
        val id: String,

        @SerialName("text")
        val text: String,

        @SerialName("importance")
        val importance: String,

        @SerialName("deadline")
        val deadline: Long? = null,

        @SerialName("done")
        val done: Boolean,

        @SerialName("color")
        val color: String? = null,

        @SerialName("created_at")
        val createdAt: Long,

        @SerialName("changed_at")
        val changedAt: Long,

        @SerialName("last_updated_by")
        val lastUpdatedBy: String
    )
}

@Serializable
data class TodoOneItemResponse(
    @SerialName("status")
    val status: String,

    @SerialName("element")
    val element: TodoItemResponse,

    @SerialName("revision")
    val revision: Int
)

@Serializable
data class TodoPostPutDeleteItemRequest(
    @SerialName("status")
    val status: String,

    @SerialName("element")
    val element: TodoItemResponse,
)

@Serializable
data class UpdateListRequest(
    @SerialName("status")
    val status: String,
    @SerialName("list")
    val list: List<TodoItemResponse>
)

//enum class Importance{
//    @SerialName("low")
//    Low,
//    @SerialName("basic")
//    Basic,
//    @SerialName("important")
//    Important,
//}