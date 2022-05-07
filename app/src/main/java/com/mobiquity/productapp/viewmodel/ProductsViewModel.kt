package com.mobiquity.productapp.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobiquity.productapp.data.Section
import com.mobiquity.productapp.data.SourceRepository
import com.mobiquity.productapp.util.NetworkUtil.Companion.hasInternetConnection
import com.mobiquity.productapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject internal constructor(
    private val sourceRepository: SourceRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    var responseLiveData: MutableLiveData<Resource<List<Section>>> = MutableLiveData()

    init {
        getSections()
    }


    private fun getSections() = viewModelScope.launch {
        safeExchangeRatesCall()
    }

    private suspend fun safeExchangeRatesCall() {
        responseLiveData.postValue(Resource.Loading())
        try {
            if (hasInternetConnection(context)) {
                val response = sourceRepository.getSections()
                responseLiveData.postValue(handleExchangeRatesResponse(response))
            } else {
                responseLiveData.postValue(Resource.Error("No Internet Connection"))
            }
        } catch (ex: Exception) {
            when (ex) {
                is IOException -> responseLiveData.postValue(Resource.Error("Network Failure"))
                else -> responseLiveData.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    private fun handleExchangeRatesResponse(response: Response<List<Section>>): Resource<List<Section>> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}