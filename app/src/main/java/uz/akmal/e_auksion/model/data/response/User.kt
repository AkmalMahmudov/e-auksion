package uz.akmal.e_auksion.model.data.response

data class User(
    val additional_phones: String,
    val address: String,
    val area_name: String,
    val areas_id: Int,
    val created_date: String,
    val director_inn: String,
    val email: String,
    val file_hash: String,
    val fio: String,
    val full_address: String,
    val id: Int,
    val image_id: Int,
    val inn: String,
    val is_confirm_email: Int,
    val is_confirm_phone: Int,
    val name: String,
    val offer_signed: Int,
    val passport_sn: String,
    val phone: String,
    val pinfl: String,
    val region_name: String,
    val sex: Int,
    val subject_type: Int,
    val two_step_verification: Int
)