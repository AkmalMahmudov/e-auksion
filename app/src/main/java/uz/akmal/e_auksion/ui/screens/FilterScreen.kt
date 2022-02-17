package uz.akmal.e_auksion.ui.screens

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import uz.akmal.e_auksion.R
import uz.akmal.e_auksion.databinding.FragmentFilterBinding
import uz.akmal.e_auksion.model.data.response.filtersList.FiltersListResponse
import uz.akmal.e_auksion.uitl.CurrencyEvent
import uz.akmal.e_auksion.viewmodel.MainViewModel

@AndroidEntryPoint
class FilterScreen : Fragment(R.layout.fragment_filter) {
    private val binding by viewBinding(FragmentFilterBinding::bind)
    private val navController by lazy { findNavController() }
    private val viewModel: MainViewModel by viewModels()
    var groupNumber: Int = 0
    var categoryNumber = 0
    var regionNumber = 0
    var areaNumber = 0


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.filtersList()

        observe()
        clickReceiver()
    }

    private fun observe() {
        viewModel.filtersList.observe(viewLifecycleOwner) {
            when (it) {
                is CurrencyEvent.Failure -> {
                    Snackbar.make(binding.root, it.errorText, Snackbar.LENGTH_SHORT).show()
                }
                is CurrencyEvent.Loading -> {
                }
                is CurrencyEvent.Success<*> -> {
                    val list = it.data as FiltersListResponse
                    val groups = ArrayList<String>()
                    val categories = ArrayList<String>()
                    val regions = ArrayList<String>()
                    val areas = ArrayList<String>()

                    for (group in list.groups) {
                        groups.add(group.name)
                    }
                    for (category in list.categories) {
                        if (category.confiscant_groups_id == groupNumber) {
                            categories.add(category.name)
                        }
                    }
                    for (region in list.regions) {
                        regions.add(region.name)
                    }
                    for (area in list.areas) {
                        if (area.regions_id == groupNumber) {
                            areas.add(area.name)
                        }
                    }
                    val groupAdapter = ArrayAdapter(
                        requireContext(), R.layout.item_spinner, groups
                    )
                    val categoryAdapter = ArrayAdapter(
                        requireContext(), R.layout.item_spinner, categories
                    )
                    val regionAdapter =
                        ArrayAdapter(requireContext(), R.layout.item_spinner, regions)

                    val areaAdapter =
                        ArrayAdapter(requireContext(), R.layout.item_spinner, areas)
                    binding.apply {
                        this.groups.adapter = groupAdapter
                        category.adapter = categoryAdapter
                        region.adapter = regionAdapter
                        area.adapter = areaAdapter

                        this.groups.setSelection(groupNumber)
                        category.setSelection(categoryNumber)
                        region.setSelection(regionNumber)
                        area.setSelection(areaNumber)
                    }
                }
                else -> {
                }
            }
        }
        /*   viewModel.categoriesList.observe(viewLifecycleOwner) {
               when (it) {
                   is CurrencyEvent.Failure -> {
                       Snackbar.make(binding.root, it.errorText, Snackbar.LENGTH_SHORT).show()
                   }
                   is CurrencyEvent.Loading -> {
                   }
                   is CurrencyEvent.Success<*> -> {
                       val list = it.data as FiltersListResponse

                       val areas = ArrayList<String>()
   //                    val groups = ArrayList<String>()
   //                    val regions = ArrayList<String>()

                       for (area in list.areas) {
                           areas.add(group.name)
                       }
                       for (region in list.regions) {
                           regions.add(region.name)
                       }
                       val groupAdapter = ArrayAdapter(
                           requireContext(),
                           R.layout.item_spinner,
                           groups
                       )
                       val categoryAdapter =
                           ArrayAdapter(requireContext(), R.layout.item_spinner, regions)
                       binding.groups.adapter = groupAdapter
                       binding.groups.setSelection(-1)
                       binding.region.adapter = categoryAdapter
                       binding.region.setSelection(-1)
                   }
                   else -> {
                   }
               }

           }
       */
    }

    private fun clickReceiver() {

        binding.apply {
            back.setOnClickListener {
                navController.navigateUp()
            }
            tozalash.setOnClickListener {
                groups.setSelection(-1)
                category.setSelection(-1)
                region.setSelection(-1)
                area.setSelection(-1)
            }
            izlash.setOnClickListener {
                //viewModelga yuboriladi
            }
        }

        binding.groups.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                groupNumber = p2
//                viewModel.filtersList()
////                viewModel.getCategories(p2)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        binding.region.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                regionNumber = p2
//                viewModel.filtersList()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }
    }
}