package uz.akmal.e_auksion.ui.adapters

import android.annotation.SuppressLint
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.akmal.e_auksion.databinding.ItemLotRecycler2Binding
import uz.akmal.e_auksion.model.data.response.allLots.ShortLotBean

class LotRecycler2Adapter :
    androidx.recyclerview.widget.ListAdapter<ShortLotBean, LotRecycler2Adapter.ViewHolder>(
        ShortLotBean.ITEM_CALLBACK
    ) {
//    private var currentData = emptyList<ShortLotBean>()
    var itemClickListener: ((String) -> Unit)? = null
    fun itemClickListener(block: (String) -> Unit) {
        itemClickListener = block
    }

    inner class ViewHolder(private val binding: ItemLotRecycler2Binding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(position: Int) {
            binding.apply {
                val url =
                    "https://files.e-auksion.uz/files-worker/api/v1/images?file_hash=${getItem(position).file_hash}&from_mobile=1"
                Glide.with(root.context).load(url).centerCrop().into(image)
//                name.isSelected = true
//                name.movementMethod = ScrollingMovementMethod()
                name.text = getItem(position).name
                id.text = "№" + getItem(position).lot_number
                zakalatPrice.text = getItem(position).zaklad_summa.toString() + " UZS"
                boshlangichPrice.text = getItem(position).start_price.toString() + " UZS"
            }
        }

        init {
            itemView.setOnClickListener {
                itemClickListener?.invoke(getItem(absoluteAdapterPosition).lot_number)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemLotRecycler2Binding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(position)

//    override fun getItemCount() = currentData.size

    /*@SuppressLint("NotifyDataSetChanged")
    fun setData(newData: List<ShortLotBean>) {
        currentData = newData
        notifyDataSetChanged()
    }*/
}