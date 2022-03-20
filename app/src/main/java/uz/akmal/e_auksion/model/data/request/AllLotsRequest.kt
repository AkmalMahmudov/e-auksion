package uz.akmal.e_auksion.model.data.request

class AllLotsRequest(
    val action: Int = 5,
    val currentPage: String,
    val is_gzipped: Int = 0,
    val language: String = "uz",
    val version: String = "1.3.7"
)