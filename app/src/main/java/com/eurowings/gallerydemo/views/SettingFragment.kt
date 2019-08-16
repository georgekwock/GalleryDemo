package com.eurowings.gallerydemo.views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.eurowings.gallerydemo.*
import com.eurowings.gallerydemo.data.RxBus
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.settings_dialog.*

/**
 * @author  Qing Guo
 */
class SettingFragment(context: Context) : BottomSheetDialogFragment() {
    private var galleryRequestInfo = GalleryRequestInfo()
    private var ctx: Context

    init {
        ctx = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillWindowSpinner()
        fillSectionSpinner()
        fillSortSpinner()
        search_btn.setOnClickListener { doFilter() }
    }

    private fun doFilter() {
        galleryRequestInfo.showViral = showViral_Switch.isChecked
        this.dismiss()
        RxBus.publish(galleryRequestInfo)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.settings_dialog, container, false)
    }

    private fun fillWindowSpinner() {
        val windowList = arrayListOf<String>()
        Window.values().forEach { windowList.add(it.value) }
        val windowAdapter = ArrayAdapter<String>(
            ctx, android.R.layout.simple_spinner_item, windowList
        )
        window_spinner.adapter = windowAdapter

        window_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                galleryRequestInfo.window = windowList.get(position)
            }
        }
    }

    private fun fillSortSpinner() {
        val sortList = arrayListOf<String>()
        Sort.values().forEach { sortList.add(it.value) }
        val sortAdapter = ArrayAdapter<String>(
            ctx, android.R.layout.simple_spinner_item, sortList
        )
        sort_spinner.adapter = sortAdapter
        sort_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                galleryRequestInfo.sort = sortList.get(position)
            }
        }
    }

    private fun fillSectionSpinner() {
        val sections = arrayListOf<String>()
        Section.values().forEach { sections.add(it.value) }
        val sectionAdapter = ArrayAdapter<String>(
            ctx, android.R.layout.simple_spinner_item, sections
        )
        section_spinner.adapter = sectionAdapter
        section_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                galleryRequestInfo.section = sections.get(position)
            }
        }
    }
}