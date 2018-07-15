package com.iliyangermanov.modernmvp

import android.content.Context
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class MVPFragment<P : BasePresenter> : Fragment(), BaseView {
    override var UIActive: Boolean = false
        get() = isAdded


    protected lateinit var presenter: P

    @LayoutRes
    protected abstract fun getLayout(): Int

    protected abstract fun initPresenter(applicationContext: Context): P

    @CallSuper
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayout(), container, false)
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onSetupUI()
    }

    @CallSuper
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = initPresenter(activity!!.applicationContext)
        onSetupListeners()
    }

    @CallSuper
    override fun onStart() {
        super.onStart()
        onReady()
    }

    /**
     * Empty stub
     */
    protected open fun onSetupUI() {
    }

    /**
     * Empty stub
     */
    protected open fun onSetupListeners() {
    }

    /**
     * Empty stub
     */
    protected open fun onReady() {
    }


    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroy()
    }
}