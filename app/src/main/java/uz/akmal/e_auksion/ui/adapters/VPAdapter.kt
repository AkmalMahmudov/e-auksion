package uz.akmal.e_auksion.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import uz.akmal.e_auksion.R
import uz.akmal.e_auksion.app.App
import uz.akmal.e_auksion.databinding.ItemViewpagerBinding

class VPAdapter(private val list: List<String>) :
    RecyclerView.Adapter<VPAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemViewpagerBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemViewpagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.binding.root).load(list[position]).diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true).placeholder(R.drawable.image_placeholder).into(holder.binding.image)
    }

    override fun getItemCount() = list.size
}