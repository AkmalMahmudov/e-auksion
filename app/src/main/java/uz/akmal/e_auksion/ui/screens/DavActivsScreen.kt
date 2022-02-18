package uz.akmal.e_auksion.ui.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import uz.akmal.e_auksion.R
import uz.akmal.e_auksion.databinding.FragmentDavActivsBinding
import uz.akmal.e_auksion.model.data.response.allLots.LotsResponse
import uz.akmal.e_auksion.ui.adapters.LotRecycler1Adapter
import uz.akmal.e_auksion.ui.adapters.LotRecycler2Adapter
import uz.akmal.e_auksion.uitl.CurrencyEvent
import uz.akmal.e_auksion.viewmodel.MainViewModel

@AndroidEntryPoint
class DavActivsScreen : Fragment(R.layout.fragment_dav_activs) {
    private val binding by viewBinding(FragmentDavActivsBinding::bind)
    private val viewModel: MainViewModel by viewModels()
    private val navController by lazy { findNavController() }
    private lateinit var adapter1: LotRecycler1Adapter
    private lateinit var adapter2: LotRecycler2Adapter
    private lateinit var adapter3: LotRecycler2Adapter
    private var currentPage = 1
   private var currentPage2 = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadViews()
//        clickReceiver()

        viewModel.getAllLots(1)
        menu()
        observe()

    }

    private fun menu() {
        var orderby_ = ""
        var order_type = ""
        var t = false
        binding.menuSpinner.setOnClickListener {
            val menu = PopupMenu(context, it)
            menu.inflate(R.menu.menu_spinner)
            menu.setOnMenuItemClickListener {

                when (it.itemId) {
                    R.id.yangi -> {
                        t = true
                        currentPage2=1
                        orderby_ = "start_time"
                        order_type = "0"
                        load(order_type,orderby_)
                        adapter3.submitList(emptyList())
                        viewModel.orderBy(orderby_, order_type, 1)
                    }
                    R.id.eski -> {
                        t = true
                        currentPage2=1
                        orderby_ = "start_time"
                        order_type = "1"
                        load(order_type,orderby_)
                        adapter3.submitList(emptyList())
                        viewModel.orderBy(orderby_, order_type, 1)
                    }
                    R.id.qimmat -> {
                        t = true
                        currentPage2=1
                        orderby_ = "start_price"
                        order_type = "0"
                        load(order_type,orderby_)
                        adapter3.submitList(emptyList())
                        viewModel.orderBy(orderby_, order_type, 1)
                    }
                    R.id.arzon -> {
                        t = true
                        currentPage2=1
                        orderby_ = "start_price"
                        order_type = "1"
                        load(order_type,orderby_)
                        adapter3.submitList(emptyList())
                        viewModel.orderBy(orderby_, order_type, 1)
                    }
                    R.id.katta -> {
                        t = true
                        currentPage2=1
                        orderby_ = "land_area"
                        order_type = "0"
                        load(order_type,orderby_)
                        adapter3.submitList(emptyList())
                        viewModel.orderBy(orderby_, order_type, 1)
                    }
                    R.id.kichik -> {
                        t = true
                        currentPage2=1
                        orderby_ = "land_area"
                        order_type = "1"
                        load(order_type,orderby_)
                        adapter3.submitList(emptyList())
                        viewModel.orderBy(orderby_, order_type, 1)
                    }
                    R.id.kup -> {
                        t = true
                        currentPage2=1
                        orderby_ = "view_count"
                        order_type = "0"
                        viewModel.orderBy(orderby_, order_type, 1)
                        adapter3.submitList(emptyList())
                        load(order_type,orderby_)
                    }
                    R.id.kam -> {
                        t = true
                        currentPage2=1
                        orderby_ = "view_count"
                        order_type = "1"
                        viewModel.orderBy(orderby_, order_type, 1)
                        adapter3.submitList(emptyList())
                        load(order_type,orderby_)
                    }
                }
                t = true
                true
            }
            menu.show()
        }
//        adapter3.submitList(emptyList())



    }
    private fun load (order_type:String, orderby_:String) {

        val scrollListener1 = object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
//                if (currentPage2 == 1 && dy <= 0) {
//                    viewModel.orderBy(orderby_, order_type, 1)
//                    currentPage2++
//                } else {
                    if (layoutManager.findLastVisibleItemPosition() >= currentPage2 * 20 -2 ) {
                        viewModel.orderBy(orderby_, order_type, currentPage2)
                        Log.d("mkm1", "onScrolled: $currentPage2 ")
                        currentPage2++
                    }

//                }
            }
        }
        binding.recycler2.visibility=View.GONE
        binding.recycler3.visibility=View.VISIBLE
        binding.recycler3.addOnScrollListener(scrollListener1)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observe() {
        viewModel.getAllLots.observe(viewLifecycleOwner) {
            when (it) {
                is CurrencyEvent.Failure -> {
                    Snackbar.make(binding.root, it.errorText, Snackbar.LENGTH_SHORT).show()
                }
                is CurrencyEvent.Loading -> {
                    binding.progressBar.isVisible = true
                }
                is CurrencyEvent.Success<*> -> {
                    binding.progressBar.isVisible = false
                    val list = it.data as LotsResponse
                    Toast.makeText(context, list.result_msg, Toast.LENGTH_SHORT).show()
                    val ls = adapter2.currentList.toMutableList()
                    ls.addAll(list.shortLotBeans)
                    adapter2.notifyDataSetChanged()
                    adapter2.submitList(ls)
                }
                else -> {
                }
            }
        }
        viewModel.orderByLots.observe(viewLifecycleOwner) {

            when (it) {
                is CurrencyEvent.Failure -> {
                    Snackbar.make(binding.root, it.errorText, Snackbar.LENGTH_SHORT).show()
                }
                is CurrencyEvent.Loading -> {
                    binding.progressBar.isVisible = true
                }
                is CurrencyEvent.Success<*> -> {
                    binding.progressBar.isVisible = false
                    val list = it.data as LotsResponse
                    val ls = adapter3.currentList.toMutableList()
                    ls.addAll(list.shortLotBeans)
                    adapter3.submitList(ls)
                }
                else -> {
                }
            }
        }
    }

    private fun loadViews() {
        adapter1 = LotRecycler1Adapter()
        adapter2 = LotRecycler2Adapter()
        adapter3 = LotRecycler2Adapter()
        binding.apply {
            back.setOnClickListener {
                navController.navigateUp()
            }
            recycler1.layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
            recycler2.layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            recycler3.layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            recycler1.adapter = adapter1
            recycler2.adapter = adapter2
            recycler3.adapter = adapter3
            recycler2.addOnScrollListener(this@DavActivsScreen.scrollListener)
        }
    }

//    private fun clickReceiver() {
//        binding.sort.onItemSelectedListener = object : AdapterView.OnItemClickListener,
//            AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                var orderby_: String = ""
//                var order_type: String = ""
//                when (p2) {
//                    0 -> {
//                        orderby_ = "start_time"
//                        order_type = "0"
//                    }
//                    1 -> {
//                        orderby_ = "start_time"
//                        order_type = "1"
//                    }
//                    2 -> {
//                        orderby_ = "start_price"
//                        order_type = "0"
//                    }
//                    3 -> {
//                        orderby_ = "start_price"
//                        order_type = "1"
//                    }
//                    4 -> {
//                        orderby_ = "land_area"
//                        order_type = "0"
//                    }
//                    5 -> {
//                        orderby_ = "land_area"
//                        order_type = "1"
//                    }
//                    6 -> {
//                        orderby_ = "view_count"
//                        order_type = "0"
//                    }
//                    7 -> {
//                        orderby_ = "view_count"
//                        order_type = "1"
//                    }
//                }
////                currentPage=1
////                adapter2.submitList(emptyList())
////                viewModel.orderBy(orderby_, order_type, currentPage++)
////                val scrollListener = object : RecyclerView.OnScrollListener() {
////
////                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
////                        super.onScrolled(recyclerView, dx, dy)
////
////                        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
////                        if (currentPage == 1 && dy <= 0) {
////                            viewModel.orderBy(orderby_,order_type,1)
////                            currentPage++
////                        } else {
////                            if (layoutManager.findLastVisibleItemPosition() >= currentPage * 20 - 1) {
////                                viewModel.orderBy(orderby_,order_type,currentPage)
////                                currentPage++
////                            }
////
////                        }
////                    }
////                }
////                binding.recycler2.addOnScrollListener(scrollListener)
//
//            }
//
//            override fun onNothingSelected(p0: AdapterView<*>?) {
////                binding.sort.setSelection(0)
//            }
//
//            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//            }
//
//        }
//        binding.filter.setOnClickListener {
//            navController.navigate(DavActivsScreenDirections.openFilterScreen())
//        }
//        adapter1.itemClickListener {
//
//        }
//        adapter2.itemClickListener {
//            navController.navigate(DavActivsScreenDirections.openItemScreen(it))
//        }
//    }


    private val scrollListener = object : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            if (currentPage == 1 && dy <= 0) {
                viewModel.getAllLots(1)
                currentPage++
            } else {
                if (layoutManager.findLastVisibleItemPosition() >= currentPage * 20 - 1) {
                    viewModel.getAllLots(currentPage)
                    currentPage++
                    Log.d("mkm", "onScrolled: $currentPage ")
                }
            }
        }
    }
}