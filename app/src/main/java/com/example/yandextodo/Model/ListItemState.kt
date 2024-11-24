package com.example.yandextodo.Model

import androidx.compose.ui.text.style.TextDecoration


data class ListItemState(
    val iconResId: Int,
    val iconColorId: Int,
    val textColorResId: Int,
    val textDecoration: TextDecoration,
    val done: Boolean
)