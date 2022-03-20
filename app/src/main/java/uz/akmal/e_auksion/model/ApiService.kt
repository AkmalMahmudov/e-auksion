package uz.akmal.e_auksion.model

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import uz.akmal.e_auksion.model.data.request.*
import uz.akmal.e_auksion.model.data.response.LotItemResponse
import uz.akmal.e_auksion.model.data.response.allLots.LotsResponse
import uz.akmal.e_auksion.model.data.response.filters.FiltersResponse
import uz.akmal.e_auksion.model.data.response.filtersList.FiltersListResponse

interface ApiService {

    @POST("mobile")
    suspend fun getAllLots(@Body datAllLotsRequest: AllLotsRequest): Response<LotsResponse>

    @POST("mobile")
    suspend fun getOrderByLots(@Body dataOrderByRequest: OrderByRequest): Response<LotsResponse>

    @POST("mobile")
    suspend fun getFiltersList(@Body dataFiltersListRequest: FiltersListRequest): Response<FiltersListResponse>

    @POST("mobile")
    suspend fun getFilterById(@Body dataFiltersRequest: FiltersRequest): Response<FiltersResponse>

    @POST("mobile")
    suspend fun getLotItem(@Body lotItemRequest: LotItemRequest): Response<LotItemResponse>

    @POST("mobile")
    suspend fun getFiltered (@Body body:FiltersRequest): Response<FiltersResponse>
}
//(@QueryMap params: Map<String, String>,