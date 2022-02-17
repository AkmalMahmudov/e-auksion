package uz.akmal.e_auksion.model.data.response.allLots

import androidx.recyclerview.widget.DiffUtil

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
){
    companion object{
        val ITEM_CALLBACK=object : DiffUtil.ItemCallback<ShortLotBean>(){
            override fun areItemsTheSame(oldItem: ShortLotBean, newItem: ShortLotBean)=oldItem.id==newItem.id

            override fun areContentsTheSame(oldItem: ShortLotBean, newItem: ShortLotBean)=oldItem.name==newItem.name && oldItem.lot_number==newItem.lot_number
        }
    }
}