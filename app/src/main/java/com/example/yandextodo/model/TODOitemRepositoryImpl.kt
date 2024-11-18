package com.example.yandextodo.model

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.ZoneId
import java.util.Calendar
import java.util.Date
import java.util.TimeZone

class TodoItemsRepositoryImpl : TodoItemRepository {

    private val _itemsFlow = MutableStateFlow<List<TodoItem>>(defaultItems)

    override fun getItemsFlow(): StateFlow<List<TodoItem>> = _itemsFlow.asStateFlow()

    override suspend fun getItem(id: String): TodoItem? =
        _itemsFlow.value.firstOrNull { it.id == id }

    override suspend fun addItem(item: TodoItem) {
        _itemsFlow.update { state ->
            state + listOf(
                item.copy(
                    id = ((state.maxOfOrNull { item -> item.id.toLong() }
                        ?: 0L) + 1L).toString(),
                    createdAt = Date()
                )
            )
        }
    }

    override suspend fun saveItem(item: TodoItem) {
        _itemsFlow.update { state ->
            state.map {
                if (it.id == item.id)
                    item.copy(modifiedAt = Date())
                else
                    it
            }
        }
    }

    override suspend fun deleteItem(item: TodoItem) {
        _itemsFlow.update { state -> state.filter { it.id != item.id } }
    }

    companion object {
        private fun createDate(year: Int, month: Int, day: Int): Date {
            return Calendar.getInstance(TimeZone.getTimeZone("UTC")).apply {
                set(year, month - 1, day, 0, 0, 0)
                set(Calendar.MILLISECOND, 0)
            }.time
        }

        private val defaultItems = listOf(
            TodoItem(
                id = "1",
                text = "Купить что-то",
                importance = TodoImportance.HIGH,
                deadline = createDate(2024, 11, 25),
                createdAt = Date()
            ),
            TodoItem(
                id = "2",
                text = "Купить что-то",
                importance = TodoImportance.DEFAULT,
                deadline = createDate(2024, 11, 26),
                createdAt = Date()
            ),
            TodoItem(
                id = "3",
                text = "Купить что-то",
                importance = TodoImportance.LOW,
                createdAt = Date()
            ),
            TodoItem(
                id = "4",
                text = "Купить что-то",
                importance = TodoImportance.HIGH,
                deadline = createDate(2024, 11, 24),
                isCompleted = true,
                createdAt = Date()
            ),
            TodoItem(
                id = "5",
                text = "Купить что-то",
                importance = TodoImportance.HIGH,
                deadline = createDate(2024, 11, 27),
                createdAt = Date()
            ),
            TodoItem(
                id = "6",
                text = "Купить что-то",
                importance = TodoImportance.DEFAULT,
                deadline = createDate(2024, 11, 28),
                createdAt = Date()
            ),
            TodoItem(
                id = "7",
                text = "Купить что-то",
                importance = TodoImportance.LOW,
                createdAt = Date()
            ),
            TodoItem(
                id = "8",
                text = "Купить что-то",
                importance = TodoImportance.HIGH,
                deadline = createDate(2024, 11, 28),
                createdAt = Date()
            ),
            TodoItem(
                id = "9",
                text = "Купить что-то",
                importance = TodoImportance.DEFAULT,
                deadline = createDate(2024, 11, 28),
                createdAt = Date()
            ),
            TodoItem(
                id = "10",
                text = "Купить что-то",
                importance = TodoImportance.HIGH,
                deadline = createDate(2024, 11, 28),
                createdAt = Date()
            ),
            TodoItem(
                id = "11",
                text = "Купить что-то",
                importance = TodoImportance.DEFAULT,
                deadline = createDate(2024, 11, 28),
                createdAt = Date()
            ),
            TodoItem(
                id = "12",
                text = "Купить что-то",
                importance = TodoImportance.LOW,
                deadline = createDate(2024, 11, 28),
                createdAt = Date()
            ),
            TodoItem(
                id = "13",
                text = "Купить что-то",
                importance = TodoImportance.HIGH,
                deadline = createDate(2024, 11, 28),
                createdAt = Date()
            ),
            TodoItem(
                id = "14",
                text = "Купить что-то",
                importance = TodoImportance.DEFAULT,
                deadline = createDate(2024, 11, 28),
                createdAt = Date()
            ),
            TodoItem(
                id = "15",
                text = "Купить что-то",
                importance = TodoImportance.LOW,
                deadline = createDate(2024, 11, 28),
                createdAt = Date()
            ),
            TodoItem(
                id = "16",
                text = "Купить что-то",
                importance = TodoImportance.DEFAULT,
                deadline = createDate(2024, 11, 28),
                createdAt = Date()
            ),
            TodoItem(
                id = "17",
                text = "Купить что-то",
                importance = TodoImportance.LOW,
                deadline = createDate(2024, 11, 28),
                createdAt = Date()
            ),
            TodoItem(
                id = "18",
                text = "Купить что-то",
                importance = TodoImportance.HIGH,
                deadline = createDate(2024, 11, 28),
                createdAt = Date()
            ),
            TodoItem(
                id = "19",
                text = "Купить что-то",
                importance = TodoImportance.HIGH,
                deadline = createDate(2024, 11, 28),
                createdAt = Date()
            ),
            TodoItem(
                id = "20",
                text = "Купить что-то",
                importance = TodoImportance.LOW,
                deadline = createDate(2024, 11, 17),
                createdAt = Date()
            ),
        )
    }
}