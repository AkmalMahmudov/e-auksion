package uz.akmal.e_auksion.model.data.response

data class ConfiscantDetails(
    val confiscant_detail_field_id: Int,
    val detail_value: String,
    val id: Int,
    val json_params: String,
    val name: String,
    val short_name: String,
    val table_field_name: String
)