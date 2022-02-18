package uz.akmal.e_auksion.ui.screens

import android.os.Bundle
import android.util.Log
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
    var groupNumber = 0
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
                    val regions = ArrayList<String>()

                    groups.add("Mulk guruhlari")
                    regions.add("Viloyat")
                    for (group in list.groups) {
                        groups.add(group.name)
                    }
                    for (region in list.regions) {
                        regions.add(region.name)
                    }
                    val groupAdapter = ArrayAdapter(
                        requireContext(), R.layout.item_spinner, groups
                    )
                    val regionAdapter =
                        ArrayAdapter(requireContext(), R.layout.item_spinner, regions)

                    binding.apply {
                        this.groups.adapter = groupAdapter
                        region.adapter = regionAdapter
                    }
                }
                else -> {
                }
            }
        }
        viewModel.categoriesList.observe(viewLifecycleOwner) {
            when (it) {
                is CurrencyEvent.Failure -> {
                    Snackbar.make(binding.root, it.errorText, Snackbar.LENGTH_SHORT).show()
                }
                is CurrencyEvent.Loading -> {
                }
                is CurrencyEvent.Success<*> -> {
                    val list = it.data as FiltersListResponse
                    val categories = ArrayList<String>()
                    categories.add("Mol-mulk toifasi")
                    list.categories.forEach {
                        if (it.confiscant_groups_id == groupNumber) {
                            categories.add(it.name)
                        }
                    }
                    val categoryAdapter =
                        ArrayAdapter(requireContext(), R.layout.item_spinner, categories)
                    binding.category.adapter = categoryAdapter
                    categoryAdapter.setDropDownViewResource(R.layout.item_spinner)
                }
                else -> {}
            }
        }
        viewModel.areasList.observe(viewLifecycleOwner) {
            when (it) {
                is CurrencyEvent.Failure -> {
                    Snackbar.make(binding.root, it.errorText, Snackbar.LENGTH_SHORT).show()
                }
                is CurrencyEvent.Loading -> {
                }
                is CurrencyEvent.Success<*> -> {
                    val list = it.data as FiltersListResponse
                    val areas = ArrayList<String>()
                    areas.add("Tumanlar")
                    list.areas.forEach {
                        if (it.regions_id == regionNumber) {
                            areas.add(it.name)
                        }
                    }
                    val areaAdapter =
                        ArrayAdapter(requireContext(), R.layout.item_spinner, areas)
                    binding.area.adapter = areaAdapter
                    areaAdapter.setDropDownViewResource(R.layout.item_spinner)
                }
                else -> {}
            }
        }
    }

    private fun clickReceiver() {
        binding.apply {
            back.setOnClickListener {
                navController.navigateUp()
            }
            tozalash.setOnClickListener {
                groups.setSelection(0)
                category.setSelection(0)
                region.setSelection(0)
                area.setSelection(0)
                groupNumber=0
                categoryNumber=0
                regionNumber=0
                areaNumber=0
            }
            izlash.setOnClickListener {
                val map = mutableMapOf<String, String>()
                if (groupNumber != 0) {
                    map["confiscant_groups_id"] = "$groupNumber"
                }
                if (categoryNumber != 0) {
                    map["confiscant_categories_id"] = "$categoryNumber"
                }
                if (regionNumber != 0) {
                    map["regions_id"] = "$regionNumber"
                }
                    if (areaNumber != 0) {
                        map["areas_id"] = "$areaNumber"
                    }

                val page = 1
                viewModel.sortByFilter(map, page)
                Log.d(
                    "map",
                    "clickReceiver: map ${map["regions_id"]} ${map["confiscant_categories_id"]} ${map["areas_id"]} ${map["confiscant_groups_id"]}"
                )
                navController.navigate(FilterScreenDirections.actionFilterScreenToDavActivsScreen(true,groupNumber,categoryNumber,regionNumber, areaNumber))
            }

            groups.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    viewModel.getCategories()
                    if (p2 != 0) {
                        groupNumber = p2
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }

            category.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    if (p2 != 0) {
                        categoryNumber = p2
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }

            region.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    viewModel.getAreas()
                    if (p2 != 0) {
                        regionNumber = p2
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }

            area.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    if (p2 != 0) {
                        areaNumber = p2
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
        }
    }
}