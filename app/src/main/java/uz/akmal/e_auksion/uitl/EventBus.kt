package uz.akmal.e_auksion.uitl

import androidx.lifecycle.MutableLiveData

object EventBus {
    var isFilterLiveData = MutableLiveData(false)
    var lotLiveData = MutableLiveData<Long>(-1)
    var groupLiveData = MutableLiveData(-1)
    var categoryLiveData = MutableLiveData(-1)
    var regionLiveData = MutableLiveData(-1)
    var areaLiveData = MutableLiveData(-1)

}