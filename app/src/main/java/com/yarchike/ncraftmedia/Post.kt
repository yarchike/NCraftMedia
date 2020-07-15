package com.yarchike.ncraftmedia


data class Post(
    val id: Int,
    val data: Long,
    val autor: String,
    val postText: String,
    var like: Int = 0,
    val comments: Int = 0,
    val share: Int = 0,
    var isLike: Boolean,
    val isComment: Boolean,
    val isShare: Boolean,
    val adress:String,
    val coordinates: Pair<Double, Double>

) {


}