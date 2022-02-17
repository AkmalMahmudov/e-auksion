package uz.akmal.e_auksion.model.data.response.allLots

data class LotsResponse(
    val result_code: Int,
    val result_msg: String,
    val shortLotBeans: List<ShortLotBean>
)