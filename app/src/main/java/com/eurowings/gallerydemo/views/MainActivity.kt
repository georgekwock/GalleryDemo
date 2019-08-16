package com.eurowings.gallerydemo.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.eurowings.gallerydemo.GalleryRequestInfo
import com.eurowings.gallerydemo.Image
import com.eurowings.gallerydemo.R
import com.eurowings.gallerydemo.adapter.GalleryAdapter
import com.eurowings.gallerydemo.data.RxBus
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_gallery.*

class MainActivity : AppCompatActivity(), ImageInterface {
    private lateinit var staggeredGridLayoutManager: StaggeredGridLayoutManager
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var settingFragment = SettingFragment(this)
    private var galleryRequestInfo = GalleryRequestInfo()
    private lateinit var imageViewModel: ImageViewModel
    private lateinit var adapter: GalleryAdapter
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
        initView()
        setListener()
    }

    override fun initView() {
        toolbar.inflateMenu(R.menu.menu)
        staggeredGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        imageViewModel = ViewModelProviders.of(this).get(ImageViewModel::class.java)
        adapter = GalleryAdapter(this)
        imageViewModel.itemPagedList.observe(this,
            Observer<PagedList<Image>> { pagedList ->
                gallery_recyclerview.visibility = View.VISIBLE
                adapter.submitList(pagedList)
            })
        gallery_recyclerview.layoutManager = staggeredGridLayoutManager
        gallery_recyclerview.adapter = adapter
    }

    private fun setListener() {
        changeLayout_btn.setOnClickListener {
            changeView()
        }
        val disposable = RxBus.listen(GalleryRequestInfo::class.java).subscribe {
            galleryRequestInfo = it
            imageViewModel.updatePageList(galleryRequestInfo)
        }
        compositeDisposable.add(disposable)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_filter -> {
                    settingFragment.show(supportFragmentManager, "Settings Dialog")
                }
                R.id.action_about -> {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Author Information").setMessage("Qing Guo\naguoqing0829@foxmail.com")
                        .setNegativeButton("Cancel") { dialog, which ->

                        }.show()
                }
                else -> {
                    super.onOptionsItemSelected(it)
                }
            }
            true
        }
    }

    private fun changeView() {
        if (gallery_recyclerview.layoutManager == staggeredGridLayoutManager) {
            gallery_recyclerview.layoutManager = linearLayoutManager
            changeLayout_btn.setImageDrawable(getDrawable(R.drawable.grid_small))
        } else {
            gallery_recyclerview.layoutManager = staggeredGridLayoutManager
            changeLayout_btn.setImageDrawable(getDrawable(R.drawable.list_menu))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
