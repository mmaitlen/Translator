package com.mgm.translate.di

import com.mgm.translator.Translator
import com.mgm.translator.TranslatorImpl
import com.mgm.translator.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideTranslator(): Translator = TranslatorImpl()

    @Provides
    fun provideViewModelFactory(translator: Translator) = ViewModelFactory(translator)
}
