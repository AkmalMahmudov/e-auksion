package uz.akmal.e_auksion.model.data.response

data class ConfiscantDocuments(
    val confiscants_id: Int,
    val description: String,
    val document_resources_id: Int,
    val extension: String,
    val external_document: Int,
    val file_hash: String,
    val file_name: String,
    val id: Int
)