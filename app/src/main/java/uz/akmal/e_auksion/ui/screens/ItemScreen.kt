package uz.akmal.e_auksion.ui.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import uz.akmal.e_auksion.R
import uz.akmal.e_auksion.databinding.FragmentLotDatasBinding
import uz.akmal.e_auksion.model.data.response.LotItemResponse
import uz.akmal.e_auksion.ui.adapters.VPAdapter
import uz.akmal.e_auksion.uitl.CurrencyEvent
import uz.akmal.e_auksion.viewmodel.MainViewModel
import java.text.SimpleDateFormat
import java.util.*


@AndroidEntryPoint
class ItemScreen : Fragment(R.layout.fragment_lot_datas) {
    private val binding by viewBinding(FragmentLotDatasBinding::bind)
    private val viewModel: MainViewModel by viewModels()
    private val navArgs: ItemScreenArgs by navArgs()
    private lateinit var adaterVP: VPAdapter

    private val navController by lazy { findNavController() }
    private lateinit var countDownTimer: CountDownTimer

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapterVP = VPAdapter(listOf("url"))
        binding.apply {
            viewPager.adapter = adapterVP
            dots.setViewPager2(viewPager)
            viewPager.offscreenPageLimit = 3
        }
        binding.apply {
            menu.setOnClickListener {
                navController.navigateUp()
            }
        }
        viewModel.getLotItem(navArgs.lotId)
        observe()
    }

    @SuppressLint("SetTextI18n")
    private fun observe() {
        binding.menu.setOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.getLotItem.observe(viewLifecycleOwner) {
            when (it) {
                is CurrencyEvent.Failure -> {
                    Snackbar.make(binding.root, it.errorText, Snackbar.LENGTH_SHORT).show()
                }
                is CurrencyEvent.Loading -> {
                    binding.progressBar.isVisible = true
                }
                is CurrencyEvent.Success<*> -> {
                    binding.progressBar.isVisible = false
                    val data = it.data as LotItemResponse
                    val item = data.lotBean
                    binding.apply {
                        lotName.text = item.name
                        lotNumber.text = "№ ${item.lot_number}"
                        amount.text = item.zaklad_summa.toString()
                        startAmount.text = item.start_price.toString()
                        isFavourite.text = item.is_favourite.toString()
                        favourite.text = item.count_favourite.toString()
                        view.text = item.view_count.toString()
                        category.text = item.confiscant_categories_name
                        date.text = item.order_end_time
                        time.text = "Auksion ${item.start_time}dan ${item.end_time}gacha"
                        address.text = item.joylashgan_manzil
                        printDifferenceDateForHours(item.order_end_time)

                        adaterVP =
                            VPAdapter(
                                listOf(
                                    "https://files.e-auksion.uz/files-worker/api/v1/images?file_hash=" + item.file_hash,
                                    "https://files.e-auksion.uz/files-worker/api/v1/images?file_hash=" + item.file_hash
                                )
                            )
                        viewPager.adapter = adaterVP
                        dots.setViewPager2(viewPager)
                        viewPager.offscreenPageLimit = 3

                    }
                }
                else -> {
                }
            }
        }
    }

    private fun printDifferenceDateForHours(date: String) {

        val currentTime = Calendar.getInstance().time
        val format1 = SimpleDateFormat("dd.MM.yyyy hh:mm:ss", Locale.getDefault())
        val endDate = format1.parse(date)

        val different = endDate.time - currentTime.time
        countDownTimer = object : CountDownTimer(different, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                var diff = millisUntilFinished
                val secondsInMilli: Long = 1000
                val minutesInMilli = secondsInMilli * 60
                val hoursInMilli = minutesInMilli * 60
                val daysInMilli = hoursInMilli * 24

                val elapsedDays = diff / daysInMilli
                diff %= daysInMilli

                val elapsedHours = diff / hoursInMilli
                diff %= hoursInMilli

                val elapsedMinutes = diff / minutesInMilli
                diff %= minutesInMilli

                val elapsedSeconds = diff / secondsInMilli

                binding.textDay.text = elapsedDays.toString()
                binding.textHour.text = elapsedHours.toString()
                binding.textMinut.text = elapsedMinutes.toString()
                binding.textSecond.text = elapsedSeconds.toString()
            }

            override fun onFinish() {
                binding.textDay.text = "0"
                binding.textHour.text = "0"
                binding.textMinut.text = "0"
                binding.textSecond.text = "0"
            }
        }.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        countDownTimer.cancel()
    }
}