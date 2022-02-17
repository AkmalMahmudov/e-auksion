package uz.akmal.e_auksion.model.data.response

data class Rekvisits(
    val pay_bank_currency_xr: String,
    val pay_bank_mfo: String,
    val pay_bank_name: String,
    val pay_bank_swift: String,
    val pay_bank_xr: String,
    val pay_inn: String,
    val pay_receiver: String
)