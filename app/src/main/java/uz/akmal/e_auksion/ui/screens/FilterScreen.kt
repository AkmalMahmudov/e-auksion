package uz.akmal.e_auksion.ui.screens

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
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
                        this.groups.setSelection(groupNumber)
                        region.setSelection(regionNumber)
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
                    list.categories.forEach {
                        if (it.confiscant_groups_id == groupNumber + 1) {
                            categories.add(it.name)
                        }
                    }
                    val categoryAdapter =
                        ArrayAdapter(requireContext(), R.layout.item_spinner, categories)
                    binding.category.adapter = categoryAdapter
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
                    list.areas.forEach {
                        if (it.regions_id == regionNumber + 1) {
                            areas.add(it.name)
                        }
                    }
                    val areaAdapter =
                        ArrayAdapter(requireContext(), R.layout.item_spinner, areas)
                    binding.area.adapter = areaAdapter
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
                groups.setSelection(-1)
                category.setSelection(-1)
                region.setSelection(-1)
                area.setSelection(-1)
            }
            izlash.setOnClickListener {
                //viewModelga yuboriladi
                Toast.makeText(
                    requireContext(), "${groups.selectedItemPosition}", Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.groups.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                groupNumber = p2
                viewModel.getCategories()
                binding.category.setSelection(-1)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        binding.category.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                categoryNumber = p2
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }

        binding.region.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                regionNumber = p2
                viewModel.getAreas()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        binding.area.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                areaNumber = p2
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
    }
}