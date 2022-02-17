package uz.akmal.e_auksion.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.akmal.e_auksion.databinding.ItemViewpagerBinding

class VPAdapter(private val list: List<String>) :
    RecyclerView.Adapter<VPAdapter.ViewHolder>() {

    inner class ViewHolder(binding: ItemViewpagerBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemViewpagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }

    override fun getItemCount() = list.size
}