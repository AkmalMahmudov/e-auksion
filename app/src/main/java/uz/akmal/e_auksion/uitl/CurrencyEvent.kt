package uz.akmal.e_auksion.uitl

sealed class CurrencyEvent {
    data class Success<T>(val data: T?): CurrencyEvent()
    //    class Success(val resultText: String): CurrencyEvent()
    class Failure(val errorText: String): CurrencyEvent()
    object Loading : CurrencyEvent()
    object Empty : CurrencyEvent()
}