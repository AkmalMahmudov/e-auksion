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

    private var _getFiltersList = MutableLiveData<CurrencyEvent>()
    val filtersList: LiveData<CurrencyEvent> get() = _getFiltersList

    private var _getCategoriesList = MutableLiveData<CurrencyEvent>()
    val categoriesList: LiveData<CurrencyEvent> get() = _getCategoriesList

    fun getAllLots(page: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            _getAllLots.postValue(CurrencyEvent.Loading)
            viewModelScope.launch {
                _getAllLots.value = repository.getFullLots(page)
            }

            /*is CurrencyEvent.Failure->_getLots.postValue(CurrencyEvent.Failure("An error occurred. Please check the connection "/*data.message!!*/))

            is CurrencyEvent.Success<*> -> {
                CoroutineScope(Dispatchers.Default).launch {
                    _getLots.postValue(CurrencyEvent.Success(data))
                }
            }*/

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

    fun getCategories(groupNumber: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            _getCategoriesList.postValue(CurrencyEvent.Loading)
            viewModelScope.launch {
                _getCategoriesList.value = repository.getCategoriesList(groupNumber)
            }
        }
    }

}