package uz.akmal.e_auksion.model.data.request

data class SimpleDataRequest(
    val action: Int = 7,
//    filt_map:["areas_id",id]
    val is_gzipped: Int = 0,
    val language: String = "uz",
    val currentPage: Int = 16,
    val version: String = "1.3.5"
)