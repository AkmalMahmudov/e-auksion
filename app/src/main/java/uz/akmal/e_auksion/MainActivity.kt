package uz.akmal.e_auksion

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.akmal.e_auksion.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private val binding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.nav_host_fragment)
        drawerLayout = binding.drawerLayout
        binding.navDrawer.setupWithNavController(navController)
    }
    fun openDrawer() {
        if (binding.drawerLayout.isOpen) {
            binding.drawerLayout.close()
        } else {
            binding.drawerLayout.open()
        }
    }
}