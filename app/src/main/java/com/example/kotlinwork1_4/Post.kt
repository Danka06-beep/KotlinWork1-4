package com.example.kotlinwork1_4

data class Post(val id: Int, val author: String, val data: Long, val txt: String,val like: Boolean, val comment: Boolean, val share: Boolean, val likeTxt : Int, val commentTxt : Int, val shareTxt : Int, val adress : String, val coordinates : Pair<Double,Double>) {


}