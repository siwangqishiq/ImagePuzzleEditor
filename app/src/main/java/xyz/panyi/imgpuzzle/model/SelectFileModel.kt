package xyz.panyi.imgpuzzle.model

data class SelectFileModel(
    var name : String? = null,
    var path: String? = null,
    var width:Int = 0,
    var height:Int = 0,
    var size:Long = 0,
    var album: String? = null
)
