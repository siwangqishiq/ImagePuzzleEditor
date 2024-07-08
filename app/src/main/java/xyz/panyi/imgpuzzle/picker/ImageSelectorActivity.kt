package xyz.panyi.imgpuzzle.picker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toolbar
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import xyz.panyi.imgpuzzle.R

class ImageSelectorActivity : ComponentActivity() {
    companion object{
        const val TAG = "ImageSelectorActivity"
        const val INTENT_SELECTOR_OPTION = "intent_selector_option"

        fun start(context: Activity, option: SelectorOption , requestCode:Int){
            val intent = Intent(context , ImageSelectorActivity::class.java).apply {
                putExtra(INTENT_SELECTOR_OPTION , option)
            }
            context.startActivityForResult(intent , requestCode)
        }
    }

    private lateinit var mOption : SelectorOption

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_selector)
        readParams()
        initUI()
    }

    private fun readParams(){
        mOption = intent.getSerializableExtra(INTENT_SELECTOR_OPTION) as SelectorOption
    }

    private fun initUI(){
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setActionBar(toolbar)
        setTitle(R.string.select_images)
    }

}//end class