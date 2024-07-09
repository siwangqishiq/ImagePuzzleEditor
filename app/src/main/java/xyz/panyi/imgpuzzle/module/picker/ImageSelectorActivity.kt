package xyz.panyi.imgpuzzle.module.picker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import xyz.panyi.imgpuzzle.R

class ImageSelectorActivity : AppCompatActivity() {//end class
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_selector)
        readParams()
        initUI()

        fillData()
    }

    private fun fillData(){
        val list = MediaQuery.queryImageFile(this)
    }

    private fun readParams(){
        mOption = intent.getSerializableExtra(INTENT_SELECTOR_OPTION) as SelectorOption
    }

    private fun initUI(){
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        setTitle(R.string.select_images)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            onBackPressedDispatcher.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}