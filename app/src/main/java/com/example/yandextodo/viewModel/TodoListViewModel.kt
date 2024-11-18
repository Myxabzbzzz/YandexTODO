package com.example.yandextodo.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.yandextodo.TodoApp
import com.example.yandextodo.model.TodoItem
import com.example.yandextodo.model.TodoItemRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TodoListViewModel(
    private val todoItemRepository: TodoItemRepository
) : ViewModel() {

    private val filterFlow = MutableStateFlow(TodoListUiState.FilterState.NOT_COMPLETED)
    val uiState: StateFlow<TodoListUiState> =
        todoItemRepository.getItemsFlow()
            .combine<List<TodoItem>, TodoListUiState.FilterState, TodoListUiState>(
                filterFlow
            ) { list, filter ->
                TodoListUiState.Loaded(
                    items = list.filter(filter.filter),
                    filterState = filter,
                    doneCount = list.count { it.isCompleted }
                )
            }
            .catch { e ->
                Log.e("MyLog", "Todo list flow error: ${e.message ?: e.cause}")
                emit(TodoListUiState.Error(e))
            }
            .stateIn(viewModelScope, SharingStarted.Eagerly, TodoListUiState.Loading)


    fun onChecked(item: TodoItem, checked: Boolean) {
        viewModelScope.launch {
            if (uiState.value is TodoListUiState.Loaded) {
                val newItem = item.copy(isCompleted = checked)
                todoItemRepository.saveItem(newItem)
            }
        }
    }

    fun delete(item: TodoItem) {
        viewModelScope.launch {
            todoItemRepository.deleteItem(item)
        }
    }

    fun onFilterChange(filterState: TodoListUiState.FilterState) {
        viewModelScope.launch {
            filterFlow.emit(filterState)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY]
                TodoListViewModel(
                    todoItemRepository = (application as TodoApp).todoItemRepository
                )
            }
        }
    }
}