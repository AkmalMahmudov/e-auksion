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
import uz.akmal.e_auksion.model.recyclerData.MainRvData
import uz.akmal.e_auksion.ui.adapters.MainRecyclerAdapter
import uz.akmal.e_auksion.ui.adapters.VPAdapter
import uz.akmal.e_auksion.uitl.toPx

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.fragment_main) {
    private val binding by viewBinding(FragmentMainBinding::bind)
    private val navController by lazy { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapterVP = VPAdapter(
            listOf(
                "https://files.e-auksion.uz/files-worker/api/v1/images?file_hash=13e827bc5966e7419495d7ce4530547a8b058fb2",
                "https://files.e-auksion.uz/files-worker/api/v1/images?file_hash=5b8ae0b97376b52ede4cf3abbfeef5c5c0d653b2",
                "https://files.e-auksion.uz/files-worker/api/v1/images?file_hash=393bb25ba9c8c14685900a206ded9f72cefa63a5",
                "https://files.e-auksion.uz/files-worker/api/v1/images?file_hash=43d1bd01f427b052d2e45208eae6a4b25905d4b5"
            )
        )
        val adapterR = MainRecyclerAdapter(
            listOf(
                MainRvData(
                    "https://files.e-auksion.uz/files-worker/api/v1/group-icon?cgroup_id=5",
                    "Davlat aktivlari"
                ),
                MainRvData(
                    "https://files.e-auksion.uz/files-worker/api/v1/group-icon?cgroup_id=11",
                    "davlat mulkini ijaraga berish"
                ),
                MainRvData(
                    "https://files.e-auksion.uz/files-worker/api/v1/group-icon?cgroup_id=24",
                    "Dehqon xo'jaligi yerlar ijarasi"
                ),
                MainRvData(
                    "https://files.e-auksion.uz/files-worker/api/v1/group-icon?cgroup_id=1",
                    "Ko'chmas mulk"
                ),
                MainRvData(
                    "https://files.e-auksion.uz/files-worker/api/v1/group-icon?cgroup_id=2",
                    "Avtotransport"
                ),
                MainRvData(
                    "https://files.e-auksion.uz/files-worker/api/v1/group-icon?cgroup_id=6",
                    "Yer uchastkalari"
                ),
                MainRvData(
                    "https://files.e-auksion.uz/files-worker/api/v1/group-icon?cgroup_id=10",
                    "Yer qari uchastkasidan foydalanish"
                ),
                MainRvData(
                    "https://files.e-auksion.uz/files-worker/api/v1/group-icon?cgroup_id=17",
                    "Tashqi reklama obyeltlari"
                ),
                MainRvData(
                    "https://files.e-auksion.uz/files-worker/api/v1/group-icon?cgroup_id=9",
                    "Daryolar o'zanlarini tozzalash"
                ),
                MainRvData(
                    "https://files.e-auksion.uz/files-worker/api/v1/group-icon?cgroup_id=12",
                    "Ko'llar tizimi"
                ),
                MainRvData(
                    "https://files.e-auksion.uz/files-worker/api/v1/group-icon?cgroup_id=3",
                    "Maxsus texnikalar"
                ),
                MainRvData(
                    "https://files.e-auksion.uz/files-worker/api/v1/group-icon?cgroup_id=14",
                    "Buyurtmachi mulklari"
                ),
                MainRvData(
                    "https://files.e-auksion.uz/files-worker/api/v1/group-icon?cgroup_id=15",
                    "Bankrotlik bo'yicha"
                ),
                MainRvData(
                    "https://files.e-auksion.uz/files-worker/api/v1/group-icon?cgroup_id=7",
                    "Xududlarni tozalash"
                ),
                MainRvData(
                    "https://files.e-auksion.uz/files-worker/api/v1/group-icon?cgroup_id=18",
                    "O'zpaxtasanoat AJ mol-mulklar"
                ),
                MainRvData(
                    "https://files.e-auksion.uz/files-worker/api/v1/group-icon?cgroup_id=4",
                    "Boshqa mulklar"
                ),
                MainRvData(
                    "https://files.e-auksion.uz/files-worker/api/v1/group-icon?cgroup_id=19",
                    "Radar va kamealar o'rnatish joylari"
                ),
                MainRvData(
                    "https://files.e-auksion.uz/files-worker/api/v1/group-icon?cgroup_id=20",
                    "Statsionar shaxpbchalar o'rnatish"
                ),
                MainRvData(
                    "https://files.e-auksion.uz/files-worker/api/v1/group-icon?cgroup_id=21",
                    "Havo kemalari"
                ),
                MainRvData(
                    "https://files.e-auksion.uz/files-worker/api/v1/group-icon?cgroup_id=23",
                    "Ko'chmas savdo joylari"
                ),
                MainRvData(
                    "https://files.e-auksion.uz/files-worker/api/v1/group-icon?cgroup_id=25",
                    "Jamoatchilik nazorat maskanlari"
                )
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