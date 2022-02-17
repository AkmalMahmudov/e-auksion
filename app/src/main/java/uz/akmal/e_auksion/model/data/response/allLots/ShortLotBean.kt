package uz.akmal.e_auksion.model.data.response.allLots

data class ShortLotBean(
    val confiscant_categories_id: Int,
    val confiscant_groups_id: Int,
    val confiscants_id: Int,
    val file_hash: String,
    val id: Int,
    val lot_number: String,
    val lot_statuses_id: Int,
    val name: String,
    val order_end_time_str: String,
    val start_price: Double,
    val start_time_date_str: String,
    val zaklad_summa: Double
)