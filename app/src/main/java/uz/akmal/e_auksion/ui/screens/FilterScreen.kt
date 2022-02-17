package uz.akmal.e_auksion.ui.screens

import android.database.DataSetObserver
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.akmal.e_auksion.R
import uz.akmal.e_auksion.databinding.FragmentFilterBinding

@AndroidEntryPoint
class FilterScreen : Fragment(R.layout.fragment_filter) {
    private val binding by viewBinding(FragmentFilterBinding::bind)
    private val navController by lazy { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            
            back.setOnClickListener {
                navController.navigateUp()
            }
            tozalash.setOnClickListener {
                davlatActivlari.setSelection(0)
                molMulk.setSelection(0)
                viloyat.setSelection(0)
                tuman.setSelection(0)
            }
            izlash.setOnClickListener {
                //viewModelga yuboriladi
            }
        }
    }
}