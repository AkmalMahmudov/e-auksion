package uz.akmal.e_auksion.ui.adapters

import android.annotation.SuppressLint
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.akmal.e_auksion.databinding.ItemLotRecycler2Binding
import uz.akmal.e_auksion.model.data.response.allLots.ShortLotBean

class LotRecycler2Adapter : RecyclerView.Adapter<LotRecycler2Adapter.ViewHolder>() {
    private var currentData = ArrayList<ShortLotBean>()
    var itemClickListener: ((Int) -> Unit)? = null
    fun itemClickListener(block: (Int) -> Unit) {
        itemClickListener = block
    }

    inner class ViewHolder(private val binding: ItemLotRecycler2Binding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(position: Int) {
            binding.apply {
                val url =
                    "https://files.e-auksion.uz/files-worker/api/v1/images?file_hash=${currentData[position].file_hash}&from_mobile=1"
                Glide.with(root.context).load(url).centerCrop().into(image)
                name.isSelected = true
                name.movementMethod = ScrollingMovementMethod()
                name.text = currentData[position].name
                id.text = "№" + currentData[position].lot_number
                zakalatPrice.text = currentData[position].zaklad_summa.toString() + " UZS"
                boshlangichPrice.text = currentData[position].start_price.toString() + " UZS"
            }
        }

        init {
            itemView.setOnClickListener {
                itemClickListener?.invoke(absoluteAdapterPosition)
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

    override fun getItemCount() = currentData.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: List<ShortLotBean>) {
        currentData.addAll(newData)
        notifyDataSetChanged()
    }
}