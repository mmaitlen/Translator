package com.mgm.translator

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider


/* Factory for creating FeatureViewModel instances */
class ViewModelFactory(val translator: Translator) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(translator) as T
    }
}