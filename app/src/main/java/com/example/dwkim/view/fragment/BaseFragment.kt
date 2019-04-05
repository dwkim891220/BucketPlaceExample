package com.example.dwkim.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dwkim.util.MainActivityListener

abstract class BaseFragment : Fragment() {
    var listener: MainActivityListener? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listener = context as? MainActivityListener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(createLayout(), container, false)
    }

    abstract fun createLayout(): Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setLayouts()
    }

    abstract fun setLayouts()

    protected fun init(bundle: Bundle = Bundle()): BaseFragment = this.apply { arguments = bundle }
}