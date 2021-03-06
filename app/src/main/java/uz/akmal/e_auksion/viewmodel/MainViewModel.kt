package uz.akmal.e_auksion.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.akmal.e_auksion.model.MainRepository
import uz.akmal.e_auksion.uitl.CurrencyEvent
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
    private var _getAllLots = MutableLiveData<CurrencyEvent>()
    val getAllLots: LiveData<CurrencyEvent> get() = _getAllLots

    private var _getOrderByLots = MutableLiveData<CurrencyEvent>()
    val orderByLots: LiveData<CurrencyEvent> get() = _getOrderByLots

    private var _getLotItem = MutableLiveData<CurrencyEvent>()
    val getLotItem: LiveData<CurrencyEvent> get() = _getLotItem

    private var _getFiltersList = MutableLiveData<CurrencyEvent>()
    val filtersList: LiveData<CurrencyEvent> get() = _getFiltersList

    private var _getCategoriesList = MutableLiveData<CurrencyEvent>()
    val categoriesList: LiveData<CurrencyEvent> get() = _getCategoriesList

    private var _getAreasList = MutableLiveData<CurrencyEvent>()
    val areasList: LiveData<CurrencyEvent> get() = _getAreasList

    private var _getFiltered = MutableLiveData<CurrencyEvent>()
    val filteredList: LiveData<CurrencyEvent> get() = _getFiltered

//    var isFilterLiveData = MutableLiveData(false)
//    var lotLiveData = MutableLiveData<Long>(-1)
//    var groupLiveData = MutableLiveData(-1)
//    var categoryLiveData = MutableLiveData(-1)
//    var regionLiveData = MutableLiveData(-1)
//    var areaLiveData = MutableLiveData(-1)


    fun getAllLots(page: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            _getAllLots.postValue(CurrencyEvent.Loading)
            viewModelScope.launch {
                _getAllLots.value = repository.getFullLots(page)
            }
        }
    }

    fun orderBy(orderby_: String, order_type: String, page: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            _getOrderByLots.postValue(CurrencyEvent.Loading)
            viewModelScope.launch {
                _getOrderByLots.value = repository.getOrderByLots(orderby_, order_type, page)
            }
        }
    }

    fun filtersList() {
        CoroutineScope(Dispatchers.IO).launch {
            _getFiltersList.postValue(CurrencyEvent.Loading)
            viewModelScope.launch {
                _getFiltersList.value = repository.getFiltersList()
            }
        }
    }

    fun getCategories() {
        CoroutineScope(Dispatchers.IO).launch {
            _getCategoriesList.postValue(CurrencyEvent.Loading)
            viewModelScope.launch {
                _getCategoriesList.value = repository.getFiltersList()
            }
        }
    }

    fun getAreas() {
        CoroutineScope(Dispatchers.IO).launch {
            _getAreasList.postValue(CurrencyEvent.Loading)
            viewModelScope.launch {
                _getAreasList.value = repository.getFiltersList()
            }
        }
    }

    fun getLotItem(lot_id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            _getLotItem.postValue(CurrencyEvent.Loading)
            viewModelScope.launch {
                _getLotItem.value = repository.getLotItem(lot_id)
            }
        }
    }

    fun sortByFilter(map: Map<String, String>, page: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            _getFiltered.postValue(CurrencyEvent.Loading)
            viewModelScope.launch {
                _getFiltered.value = repository.getFiltered(map, page)
            }
        }
    }

}