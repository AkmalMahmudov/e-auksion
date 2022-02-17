package uz.akmal.e_auksion.model.data.response.filtersList

data class FiltersListResponse(
    val areas: List<Area>,
    val categories: List<Category>,
    val directions: List<Direction>,
    val groups: List<Group>,
    val regions: List<Region>,
    val result_code: Int,
    val result_msg: String
)