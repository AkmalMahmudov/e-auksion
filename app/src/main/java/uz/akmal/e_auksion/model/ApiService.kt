package uz.akmal.e_auksion.model

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import uz.akmal.e_auksion.model.data.request.AllLotsRequest
import uz.akmal.e_auksion.model.data.request.OrderByRequest
import uz.akmal.e_auksion.model.data.request.SimpleDataRequest
import uz.akmal.e_auksion.model.data.response.SimpleDataResponse
import uz.akmal.e_auksion.model.data.response.allLots.LotsResponse

interface ApiService {
    @POST("mobile")
    suspend fun getLots(@Body data: SimpleDataRequest): Response<SimpleDataResponse>

    @POST("mobile")
    suspend fun getAllLots(@Body datAllLotsRequest: AllLotsRequest): Response<LotsResponse>

    @POST("mobile")
    suspend fun getOrderByLots(@Body dataOrderByRequest: OrderByRequest): Response<LotsResponse>

}