package xyz.panyi.imgpuzzle.module.picker

import android.content.Context
import android.provider.MediaStore
import xyz.panyi.imgpuzzle.common.LogUtil
import xyz.panyi.imgpuzzle.model.SelectFileModel


object MediaQuery {
    private const val TAG = "MediaQuery"

    /**
     * 查询
     * @param context
     * @return
     */
    fun queryImageFile(context: Context): List<SelectFileModel> {
        LogUtil.i(TAG,"query image file start")
        val t1 = System.currentTimeMillis()

        val result= ArrayList<SelectFileModel>(16)
        val projection = arrayOf(
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.SIZE,
            MediaStore.Images.Media.WIDTH,
            MediaStore.Images.Media.HEIGHT,
            MediaStore.Images.Media.DATE_MODIFIED,
            MediaStore.Images.Media.BUCKET_ID,
            MediaStore.Images.Media.ALBUM,
            MediaStore.Images.Media.MIME_TYPE
        )
        val cur = context.contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection,
            null,
            null, MediaStore.Video.Media.DATE_MODIFIED + " DESC")
        if (cur != null) {
            if (cur.moveToFirst()) {
                while (!cur.isAfterLast) {
                    val item = SelectFileModel().apply {
                        name = cur.getString(cur.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))
                        path = cur.getString(cur.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                        width = cur.getInt(cur.getColumnIndexOrThrow(MediaStore.Images.Media.WIDTH))
                        height = cur.getInt(cur.getColumnIndexOrThrow(MediaStore.Images.Media.HEIGHT))
                        size = cur.getLong(cur.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE))
                        album = cur.getString(cur.getColumnIndexOrThrow(MediaStore.Images.Media.ALBUM))
                        mime = cur.getString(cur.getColumnIndexOrThrow(MediaStore.Images.Media.MIME_TYPE))
                    }
                    cur.moveToNext()

                    //先过滤掉gif图片
                    if(item.mime?.lowercase()?.endsWith("gif") == true){
                        continue
                    }

                    result.add(item)
                }
            }
            cur.close()
        }

        val t2 = System.currentTimeMillis()
        LogUtil.i(TAG,"query image file end cost time ${t2 - t1} ms    image count : ${result.size}")
        return result
    }
}