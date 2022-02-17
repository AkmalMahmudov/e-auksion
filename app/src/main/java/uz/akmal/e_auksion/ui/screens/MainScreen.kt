package uz.akmal.e_auksion.ui.screens

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.akmal.e_auksion.R
import uz.akmal.e_auksion.databinding.FragmentMainBinding
import uz.akmal.e_auksion.ui.adapters.MainRecyclerAdapter
import uz.akmal.e_auksion.ui.adapters.VPAdapter
import uz.akmal.e_auksion.uitl.toPx

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.fragment_main) {
    private val binding by viewBinding(FragmentMainBinding::bind)
    private val navController by lazy { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapterVP = VPAdapter(listOf("", "", "", ""))
        val adapterR = MainRecyclerAdapter(
            listOf(
                "Davlat aktivlari",
                "davlat mulkini ijaraga berish",
                "Dehqon xo'jaligi yerlar ijarasi",
                "Ko'chmas mulk",
                "Avtotransport",
                "Yer uchastkalari",
                "Yer qari uchastkasidan foydalanish",
                "Tashqi reklama obyeltlari",
                "Daryolar o'zanlarini tozzalash",
                "Ko'llar tizimi",
                "Maxsus texnikalar",
                "Buyurtmachi mulklari",
                "Bankrotlik bo'yicha",
                "Xududlarni tozalash",
                "O'zpaxtasanoat AJ mol-mulklar",
                "Boshqa mulklar",
                "Radar va kamealar o'rnatish joylari",
                "Statsionar shaxpbchalar o'rnatish",
                "Havo kemalari",
                "Ko'chmas savdo joylari",
                "Jamoatchilik nazorat maskanlari"
            ), width()
        )
        binding.apply {
            viewPager.adapter = adapterVP
            dots.setViewPager2(viewPager)
            viewPager.offscreenPageLimit = 3

            recycler.adapter = adapterR
            recycler.layoutManager = GridLayoutManager(requireActivity(), 2)
        }

        adapterR.itemClickListener {
            if (it == 0) {
                navController.navigate(MainScreenDirections.openDavActivsScreen())
            }
        }
    }

    fun width(): Int {
        val displayMetrics = DisplayMetrics()
        val windowsManager =
            requireActivity().getSystemService(AppCompatActivity.WINDOW_SERVICE) as WindowManager
        windowsManager.defaultDisplay.getMetrics(displayMetrics)
        val deviceWidth = displayMetrics.widthPixels
        val margin = 32.toPx

        return ((deviceWidth - margin) / 2).toInt()
    }
}