package uz.akmal.e_auksion.model.data.request

data class LotItemRequest(
    val action: Int=15,
    val is_gzipped: Int=0,
    val is_view: String="true",
    val language: String="uz",
    val lot_id: String,
    val version: String="1.3.5"
)
