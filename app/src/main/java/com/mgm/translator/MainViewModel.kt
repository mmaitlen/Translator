package com.mgm.translator

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers.computation
import javax.inject.Inject

/**
 * ViewModel to manage translation for View and moving computation off Android main thread
 */
class MainViewModel @Inject constructor(val translator: Translator): ViewModel() {
    private val translatedOutput = MutableLiveData<String>()

    private val translationEngine = TranslationEngine(translator)
    private val disposable = CompositeDisposable()

    fun getTranslation() = translatedOutput

    fun translate(input: String) {
        disposable.add(
            translationEngine
                .translate(input)
                .subscribeOn(computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    translatedOutput.value = translator.translate(input)
                }
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}

/**
 * Return Observable wrapping functionality for translation.  This way
 * the translator focuses on translation and doesn't need the overhead
 * of the Observable framework.  Also, if we move to a different threading
 * strategy, ie Coroutines, the Translator object doesn't need to change.
 */
class TranslationEngine(val translator: Translator) {
    fun translate(input: String): Observable<String> {
        return Observable.just(translator.translate(input))
    }
}