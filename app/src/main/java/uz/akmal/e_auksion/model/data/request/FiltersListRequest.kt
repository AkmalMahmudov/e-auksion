package uz.akmal.e_auksion.model.data.request

data class FiltersListRequest(
    val action: Int,
    val is_gzipped: Int,
    val language: String,
    val version: String
)