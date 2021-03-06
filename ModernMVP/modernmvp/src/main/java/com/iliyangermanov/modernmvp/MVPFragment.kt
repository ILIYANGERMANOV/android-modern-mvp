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

    /**
     * @return layout resource's id for the fragment's layout
     * e.g. R.layout.fragment_main
     */
    @LayoutRes
    protected abstract fun getLayout(): Int

    /**
     * Instantiate the presenter here. Called when activity is created (@see Fragment#onActivityCreated()).
     * @param applicationContext application's context, use it if 'Model' needs it
     * @param arguments arguments passed to the fragment
     * @return new instance of the presenter
     */
    protected abstract fun initPresenter(applicationContext: Context, arguments: Bundle?): P

    @CallSuper
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayout(), container, false)
    }

    @CallSuper
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = initPresenter(activity!!.applicationContext, arguments)
        onSetupUI()
        onSetupListeners()
    }

    @CallSuper
    override fun onStart() {
        super.onStart()
        onReady()
    }

    /**
     * Empty method. Called once in onActivityCreated() after presenter in initialized.
     * Setup programmatically UI here - RecyclerView, TextView, background colors and et.
     * e.g. textView.paintFlags = textView.paintFlags or Paint.UNDERLINE_TEXT_FLAG
     */
    protected open fun onSetupUI() {
    }

    /**
     * Empty method. Called once immediately after @see MVPFragment#onSetupUI().
     * Attach your UI listeners here, e.g. myButton.setOnClickListener {...}
     */
    protected open fun onSetupListeners() {
    }

    /**
     * Empty method. Called once in onStart() after presenter, UI and listeners are setup.
     * Execute your business logic that initializes the screen here.
     * E.g. presenter.start(); presenter.loadData(); presenter.initScreen() and etc
     */
    protected open fun onReady() {
    }


    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroy()
    }
}