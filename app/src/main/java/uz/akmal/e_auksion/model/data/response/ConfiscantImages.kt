package uz.akmal.e_auksion.model.data.response

data class ConfiscantImages(
    val confiscant_groups_id: Int,
    val confiscants_id: Int,
    val document_resources_id: Int,
    val file_hash: String,
    val id: Int,
    val image_positions_id: Int,
    val image_positions_name: String
)