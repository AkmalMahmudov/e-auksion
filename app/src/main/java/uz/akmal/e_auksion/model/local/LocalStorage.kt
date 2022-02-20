package uz.akmal.e_auksion.model.local

import android.content.Context

class LocalStorage(context: Context) {
    fun clear() {
        filterRegion = 0
        filterArea = 0
        filterCategory = 0
        filterGroup = 0
    }

    private val pref = context.getSharedPreferences("LocalStorage", Context.MODE_PRIVATE)

    var filterGroup: Int by IntPreference(pref, 0)
    var filterCategory: Int by IntPreference(pref, 0)
    var filterRegion: Int by IntPreference(pref, 0)
    var filterArea: Int by IntPreference(pref, 0)
}