package uz.akmal.e_auksion.model.data.response.filtersList

data class Category(
    val confiscant_groups_id: Int,
    val id: Int,
    val name: String,
    val name_ru: String,
    val name_uk: String,
    val ordering: Int
)