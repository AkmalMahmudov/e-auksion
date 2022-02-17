package uz.akmal.e_auksion.model.data.request

data class OrderByRequest(
    val action: Int = 5,
    val currentPage: String = "1",
    val orderBy_map: OrderByMap,
    val is_gzipped: Int = 0,
    val language: String = "uz",
    val version: String = "1.3.5"
)