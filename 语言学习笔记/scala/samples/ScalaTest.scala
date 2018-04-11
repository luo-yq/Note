var map=Map("key1"->"v1","key2"->"v2")
map+=("key3"->"v3")
println(map)
println(map("key2"))

import  scala.collection.mutable.HashMap
 
import  scala.collection.mutable.ArrayBuffer



var hashMap1:HashMap[String,Int]=new HashMap()
hashMap1+=("key3"->234)
println(hashMap1)


var a=Array(1,2,3)
a.mkString("<",",",">")


println(List(1,2,3):::List(5,6,7))
println(List(1,2,3).::(4))
println(4::List(1,2,3))
 