package uz.akmal.e_auksion.model.data.request

data class FiltersRequest(
    val action: Int,
    val currentPage: String,
    val filters_map: Map<String,String>,
    val is_gzipped: Int,
    val language: String,
    val version: String
)