package xyz.panyi.imgpuzzle.module

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import xyz.panyi.imgpuzzle.R
import xyz.panyi.imgpuzzle.common.LogUtil
import xyz.panyi.imgpuzzle.module.picker.ImageSelectorActivity
import xyz.panyi.imgpuzzle.module.picker.SelectorOption

/**
 *   MainActivity
 *
 *
 */
class MainActivity : AppCompatActivity() {
    companion object{
        const val TAG = "MainActivity"
        const val REQUEST_CODE_READ_IMAGES_PERMISSION= 100
        const val REQUEST_CODE_SELECT_IMAGES= 101
    }

    private lateinit var mPuzzleBtn: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        mPuzzleBtn = findViewById(R.id.imgs_puzzle_btn)
        mPuzzleBtn.setOnClickListener {
            openImageSelector()
        }
    }

    private fun openImageSelector(){
        LogUtil.i(TAG , "try open image selector")
        if(ContextCompat.checkSelfPermission(this,  Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED){
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_CODE_READ_IMAGES_PERMISSION)
        }else{
            doOpenImageSelector()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == REQUEST_CODE_READ_IMAGES_PERMISSION
            && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            doOpenImageSelector()
        }
    }

    private fun doOpenImageSelector(){
        LogUtil.i(TAG , "have permission can open image selector")
        val option = SelectorOption()
        option.selectImageCount = 2
        ImageSelectorActivity.start(this , option , REQUEST_CODE_SELECT_IMAGES)
    }
}//end class
