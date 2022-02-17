package uz.akmal.e_auksion.model

import android.util.Log
import uz.akmal.e_auksion.model.data.request.AllLotsRequest
import uz.akmal.e_auksion.model.data.request.OrderByMap
import uz.akmal.e_auksion.model.data.request.OrderByRequest
import uz.akmal.e_auksion.model.data.request.SimpleDataRequest
import uz.akmal.e_auksion.uitl.CurrencyEvent
import javax.inject.Inject

class MainRepository @Inject constructor(private val api: ApiService) {
    suspend fun getAllLots(page: Int): CurrencyEvent {
        val request = SimpleDataRequest(5, 0, "uz", page, "1.3.5")
        return try {
            val response = api.getLots(request)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                CurrencyEvent.Success(result)
            } else {
                CurrencyEvent.Failure(response.message())
            }
        } catch (e: Exception) {
            CurrencyEvent.Failure(e.message ?: "error")
        }
    }

    suspend fun getFullLots(page: Int): CurrencyEvent {
        val request = AllLotsRequest(5, page.toString(), 0, "uz", "1.3.5")
        return try {
            val response = api.getAllLots(request)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                CurrencyEvent.Success(result)
            } else {
                CurrencyEvent.Failure(response.message())
            }
        } catch (e: Exception) {
            CurrencyEvent.Failure(e.message ?: "error")
        }
    }

    suspend fun getOrderByLots(orderby_: String, order_type: String, page: Int): CurrencyEvent {
        Log.d("TTT", "request: $orderby_ : $order_type")
        val request =
            OrderByRequest(5, "$page", OrderByMap(orderby_, order_type), 0, "uz", "1.3.5")
        return try {
            val response = api.getOrderByLots(request)
            val result = response.body()
            Log.d("TTT", "response: ${result?.shortLotBeans} : $order_type")
            if (response.isSuccessful && result != null) {
                CurrencyEvent.Success(result)
            } else {
                CurrencyEvent.Failure(response.message())
            }
        } catch (e: Exception) {
            CurrencyEvent.Failure(e.message ?: "error")
        }
    }
}