package uz.akmal.e_auksion.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.akmal.e_auksion.databinding.ItemRecyclerBinding

class MainRecyclerAdapter(private val list: List<String>, private val width: Int) :
    RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>() {

    var itemClickListener: ((Int) -> Unit)? = null

    fun itemClickListener(block: (Int) -> Unit) {
        itemClickListener = block
    }


    inner class ViewHolder(private val binding: ItemRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                card.layoutParams.width = width
                card.layoutParams.height = width
                name.text = list[position]
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
            ItemRecyclerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(position)

    override fun getItemCount() = list.size
}