package xyz.panyi.imgpuzzle.module.picker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import xyz.panyi.imgpuzzle.R
import xyz.panyi.imgpuzzle.model.SelectFileWrap
import xyz.panyi.imgpuzzle.module.picker.adapter.SelectorAdapter

class ImageSelectorActivity : AppCompatActivity() {
    companion object{
        const val TAG = "ImageSelectorActivity"
        const val INTENT_SELECTOR_OPTION = "intent_selector_option"

        fun start(context: Activity, option: SelectorOption, requestCode:Int){
            val intent = Intent(context , ImageSelectorActivity::class.java).apply {
                putExtra(INTENT_SELECTOR_OPTION , option)
            }
            context.startActivityForResult(intent , requestCode)
        }
    }

    private lateinit var mOption : SelectorOption
    private val imageList = ArrayList<SelectFileWrap>()

    private lateinit var adapter: SelectorAdapter
    private lateinit var listView:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_selector)
        readParams()
        initUI()

        fillData()
    }

    private fun fillData(){
        val list = MediaQuery.queryImageFile(this)
        imageList.clear()
        list.forEach {
            imageList.add(SelectFileWrap(it))
        }

        adapter.notifyDataSetChanged()
    }

    @Suppress("DEPRECATION")
    private fun readParams(){
        mOption = intent.getSerializableExtra(INTENT_SELECTOR_OPTION) as SelectorOption
    }

    private fun initUI(){
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        setTitle(R.string.select_images)

        listView = findViewById(R.id.main_list)
        listView.layoutManager = GridLayoutManager(this , 3 , RecyclerView.VERTICAL , false)
        adapter = SelectorAdapter(this , imageList)
        listView.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            onBackPressedDispatcher.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    fun onItemSelected(selectWrap: SelectFileWrap , pos :Int){
        selectWrap.apply {
            selected = true
            selectedCount++
        }
        adapter.notifyItemChanged(pos)
    }
}//end activity class
