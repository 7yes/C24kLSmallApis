package com.jesse.c24klsmallapis.presentaion.uistate

import com.jesse.c24klsmallapis.domain.model.SmModel

sealed class UIState {
        data object Loading:UIState()
        data class Error(val error:String):UIState()
        data class Success(val mySuccessList:List<SmModel>):UIState()
    }