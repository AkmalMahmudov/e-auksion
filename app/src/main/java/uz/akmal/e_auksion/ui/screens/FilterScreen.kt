package uz.akmal.e_auksion.ui.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import uz.akmal.e_auksion.R
import uz.akmal.e_auksion.databinding.FragmentFilterBinding
import uz.akmal.e_auksion.model.data.response.filtersList.FiltersListResponse
import uz.akmal.e_auksion.uitl.CurrencyEvent
import uz.akmal.e_auksion.uitl.EventBus.areaLiveData
import uz.akmal.e_auksion.uitl.EventBus.categoryLiveData
import uz.akmal.e_auksion.uitl.EventBus.groupLiveData
import uz.akmal.e_auksion.uitl.EventBus.isFilterLiveData
import uz.akmal.e_auksion.uitl.EventBus.lotLiveData
import uz.akmal.e_auksion.uitl.EventBus.regionLiveData
import uz.akmal.e_auksion.viewmodel.MainViewModel

@AndroidEntryPoint
class FilterScreen : Fragment(R.layout.fragment_filter) {
    private val binding by viewBinding(FragmentFilterBinding::bind)
    private val navController by lazy { findNavController() }
    private val viewModel: MainViewModel by viewModels()

    private var lotNumber: Long = 0
    private var groupNumber = 0
    private var categoryNumber = 0
    private var regionNumber = 0
    private var areaNumber = 0
    private val groups = ArrayList<String>()
    private val regions = ArrayList<String>()
    private val categories = ArrayList<String>()
    private val areas = ArrayList<String>()
    private var selected = 0

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
                    groups.clear()
                    regions.clear()

                    for (group in list.groups) {
                        groups.add(group.name)
                    }
                    for (region in list.regions) {
                        regions.add(region.name)
                    }
                    binding.apply {
                        // dialogga list yuboriladi
                    }
                }
                else -> {}
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
                    categories.clear()
                    list.categories.forEach { t ->
                        if (t.confiscant_groups_id == groupNumber) {
                            categories.add(t.name)
                        }
                    }
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
                    areas.clear()
                    list.areas.forEach { t ->
                        if (t.regions_id == regionNumber) {
                            areas.add(t.name)
                        }
                    }
                    //dialogga list yuboriladi
                }
                else -> {}
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun clickReceiver() {
        binding.apply {
            back.setOnClickListener {
                navController.navigateUp()
            }
            tozalash.setOnClickListener {
                groupsText.text = "Davlat aktivlari"
                categoryText.text = "Mol mulk toifasi"
                regionText.text = "Viloyat"
                areaText.text = "Tuman"
            }
            izlash.setOnClickListener {
                if (edittext.text.isNotEmpty()) {
                    lotNumber = binding.edittext.text.toString().toLong()
                }
                val map = mutableMapOf<String, String>()
                if (lotNumber.toString().isNotEmpty()) {
                    map["lot_number"] = "$lotNumber"
                }
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
                isFilterLiveData.postValue(true)
//                lotLiveData.postValue(lotNumber)
//                groupLiveData.postValue(groupNumber)
//                categoryLiveData.postValue(categoryNumber)
//                regionLiveData.postValue(regionNumber)
//                areaLiveData.postValue(areaNumber)
                navController.navigateUp()
            }

            groupsText.setOnClickListener {
                dialog("Mulk guruhlari", groups.toTypedArray(), 0)

            }
            categoryText.setOnClickListener {
                dialog("Mol-mulk toifasi", categories.toTypedArray(), 1)
            }
            regionText.setOnClickListener {
                dialog("Viloyat", regions.toTypedArray(), 2)
            }
            areaText.setOnClickListener {
                dialog("Tuman", areas.toTypedArray(), 3)
            }
        }
    }

    private fun dialog(title: String, list: Array<String>, dialogNumber: Int) {
        MaterialAlertDialogBuilder(requireContext(), R.style.MyDialogTheme).setTitle(title)
            .setSingleChoiceItems(list, selected) { _, which ->
                when (dialogNumber) {
                    0 -> {
                        groupNumber = which + 1
                        viewModel.getCategories()
                        binding.groupsText.text = groups[groupNumber - 1]
                    }
                    1 -> {
                        categoryNumber = which + 1
                        binding.categoryText.text = categories[categoryNumber - 1]
                    }
                    2 -> {
                        regionNumber = which + 1
                        viewModel.getAreas()
                        binding.regionText.text = regions[regionNumber - 1]
                    }
                    3 -> {
                        areaNumber = which + 1
                        binding.areaText.text = areas[areaNumber - 1]
                    }
                }
            }.setPositiveButton("Select") { _, _ ->
            }.setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }.show()
    }
}