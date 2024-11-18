package com.example.yandextodo.viewModel

import com.example.yandextodo.model.TodoItem

sealed class TodoListUiState {
    data object Loading : TodoListUiState()
    data class Error(val exception: Throwable) : TodoListUiState()

    data class Loaded(
        val items: List<TodoItem>,
        val filterState: FilterState,
        val doneCount: Int
    ) : TodoListUiState()

    enum class FilterState(val filter: (TodoItem) -> Boolean) {
        ALL({ true }),
        NOT_COMPLETED({ !it.isCompleted })
    }
}