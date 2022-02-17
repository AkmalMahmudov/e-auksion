package uz.akmal.e_auksion.model.data.response

data class LotItemResponse(
    val lotBean: LotBean,
    val result_code: Int,
    val result_msg: String
)