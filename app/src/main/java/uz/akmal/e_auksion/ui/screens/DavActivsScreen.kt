package uz.akmal.e_auksion.ui.screens

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
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
    private var currentPage = 0

    companion object {
        private const val QUERY_PAGE_SIZE = 20
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadViews()
        clickReceiver()
        //pagination ishlatilganda har scroll qilganingda pag++

        viewModel.getAllLots(1)
        observe()

    }

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
                    adapter2.setData(list.shortLotBeans)
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
                    adapter2.setData(list.shortLotBeans)
                }
                else -> {
                }
            }
        }
    }

    private fun loadViews() {
        adapter1 = LotRecycler1Adapter()
        adapter2 = LotRecycler2Adapter()
        binding.apply {
            back.setOnClickListener {
                navController.navigateUp()
            }
            recycler1.layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
            recycler2.layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            recycler1.adapter = adapter1
            recycler2.adapter = adapter2
            recycler2.addOnScrollListener(this@DavActivsScreen.scrollListener)
        }
    }

    private fun clickReceiver() {
        binding.sort.onItemSelectedListener = object : AdapterView.OnItemClickListener,
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                var orderby_: String = ""
                var order_type: String = ""
                when (p2) {
                    0 -> {
                        orderby_ = "start_time"
                        order_type = "0"
                    }
                    1 -> {
                        orderby_ = "start_time"
                        order_type = "1"
                    }
                    2 -> {
                        orderby_ = "start_price"
                        order_type = "0"
                    }
                    3 -> {
                        orderby_ = "start_price"
                        order_type = "1"
                    }
                    4 -> {
                        orderby_ = "land_area"
                        order_type = "0"
                    }
                    5 -> {
                        orderby_ = "land_area"
                        order_type = "1"
                    }
                    6 -> {
                        orderby_ = "view_count"
                        order_type = "0"
                    }
                    7 -> {
                        orderby_ = "view_count"
                        order_type = "1"
                    }
                }
                viewModel.orderBy(orderby_, order_type, 1)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                binding.sort.setSelection(0)
            }

            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            }

        }
        binding.filter.setOnClickListener {
            navController.navigate(DavActivsScreenDirections.openFilterScreen())
        }
        adapter1.itemClickListener {

        }
        adapter2.itemClickListener {

        }
    }

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            if (currentPage == 0) {
                viewModel.getAllLots(1)
                currentPage++
            } else {
                if (layoutManager.findLastVisibleItemPosition() >= 19) {
                    viewModel.getAllLots(currentPage)
                    currentPage++
                }

            }
        }
    }
}