package uz.akmal.e_auksion.model.data.response.filters

data class FiltersResponse(
    val result_code: Int,
    val result_msg: String,
    val shortLotBeans: List<ShortLotBean>
)