package uz.akmal.e_auksion.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.akmal.e_auksion.databinding.ItemLotRecycler1Binding
import uz.akmal.e_auksion.model.recyclerData.LotRecycler1Data

class LotRecycler1Adapter :
    RecyclerView.Adapter<LotRecycler1Adapter.ViewHolder>() {
    private var currentData = emptyList<LotRecycler1Data>()
    var itemClickListener: ((Int) -> Unit)? = null
    fun itemClickListener(block: (Int) -> Unit) {
        itemClickListener = block
    }

    inner class ViewHolder(private val binding: ItemLotRecycler1Binding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                if (position == 0) {
                    cancel.visibility = View.GONE
                }
                title.text = currentData[position].title
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
            ItemLotRecycler1Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(position)

    override fun getItemCount() = currentData.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: List<LotRecycler1Data>) {
        currentData = newData
        notifyDataSetChanged()
    }
}