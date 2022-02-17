package uz.akmal.e_auksion.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import uz.akmal.e_auksion.R

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private val navController by lazy { findNavController() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scope.launch {
            delay(1000)
            navController.navigate(SplashFragmentDirections.openMainScreen())
        }
    }
}