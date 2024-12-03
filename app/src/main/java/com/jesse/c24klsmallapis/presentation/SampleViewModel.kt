package com.jesse.c24klsmallapis.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesse.c24klsmallapis.domain.GetDataUC
import com.jesse.c24klsmallapis.domain.model.SampleModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SampleViewModel @Inject constructor(private val getDataUC: GetDataUC) : ViewModel() {

    private val _dataList = MutableLiveData<List<SampleModel>>()
    val dataList: LiveData<List<SampleModel>> = _dataList

    private val _selected = MutableLiveData<SampleModel>()
    val selected: LiveData<SampleModel> = _selected

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            _dataList.postValue(getDataUC())
        }

    }

    fun updateSelected(item: SampleModel) {
        _selected.postValue(item)
    }
}