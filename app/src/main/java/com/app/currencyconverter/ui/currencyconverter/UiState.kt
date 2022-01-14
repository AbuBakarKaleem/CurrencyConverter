package com.app.currencyconverter.ui.currencyconverter

sealed class UiState

object LoadingState:UiState()
object UnloadingState:UiState()
object ContentState : UiState()
class ErrorState(val message: String) : UiState()
object EmptyState : UiState()