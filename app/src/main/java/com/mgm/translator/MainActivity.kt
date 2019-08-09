package com.mgm.translator

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as TranslatorApp).appComponent.inject(this)

        val viewModel = ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]

        viewModel.getTranslation().observe(this, Observer {
            am_output.text = it
        })

        am_btn_translate.setOnClickListener {
            viewModel.translate(am_input.text.toString())
        }
    }
}
