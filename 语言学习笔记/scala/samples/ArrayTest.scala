

5.2.1.  定义和初始化
5.2.1.1     Array
val list1 = new Array[String](0) // Array()

val list2 = new Array[String](3) // Array(null, null, null)

val list3:Array[String] = new Array(3) // // Array(null, null, null)

val list1 = Array("a","b","c","d") // 相当于Array.apply("a","b","c","d")

定义一个类型为Any的Array：

val aa = Array[Any](1, 2)

或：

val aa: Array[Any] = Array(1, 2)

或：

val aa: Array[_] = Array(1, 2)

 

定义：

Array (1,3,5,7,9,11)

也可以用

Array[Int](1 to 11 by 2:_*)

暂时还没有找到Range（例如 1 to 11 by 2)之后跟:_*的依据

 

Array对应的可变ArrayBuffer：

val ab = collection.mutable.ArrayBuffer[Int]()

ab += (1,3,5,7)

ab ++= List(9,11) // ArrayBuffer(1, 3, 5, 7, 9, 11)

ab toArray // Array (1, 3, 5, 7, 9, 11)

ab clear // ArrayBuffer()


5.2.3.  数组元素定位
统一使用()，而不是[]，()就是apply()的简写，a(i)===a.apply(i)

// Array

val a = Array(100,200,300) // a(0)=100, a(1)=200, a(3)=300

a(0) // 100, 相当于a.apply(0)

a(0)=10 // Array(10, 200, 300)，相当于a.update(0, 10)

 


scala中的数组比java的数组强大很多   
    1、定长数组：长度不变的数组Array，如：声明一个长度为10的整形数组，val arr = Array[Int](10)；声明并初始化一个字符串数组： val arrStr = Array(“wo”,”cha”,”yo”)。访问数组方式:访问arrStr第一个元素，arrStr(1)即可
    2、变长数组（即数组缓冲）：java中有ArrayList和scala中的ArrayBuffer等效；但是ArrayBuffer更加强大，通过下面的事列来熟悉ArrayBuffer：
import collection.mutable.ArrayBuffer
    val arrbuff1 = ArrayBuffer[Int]()
    val arrBuff2 = ArrayBuffer(1,3,4,-1,-4)
    arrbuff1 += 23    //用+=在尾端添加元素
    arrbuff1 += (2,3,4,32) //同时在尾端添加多个元素
    arrbuff1 ++= arrBuff2 //可以用 ++=操作符追加任何集合
    arrbuff1 ++= Array(2,43,88,66)
    arrbuff1.trimEnd(2) //移除最后的2个元素
    arrbuff1.remove(2)  //移除arr(2+1)个元素
    arrbuff1.remove(2,4) //从第三个元素开始移除4个元素
    val arr = arrbuff1.toArray //将数组缓冲转换为Array
    val arrbuff2 = arr.toBuffer //将Array转换为数组缓冲
    3、遍历数组和数组缓冲：在java中数组和数组列表/向量上语法有些不同。scala则更加统一，通常情况，我们可以用相同的代码处理这两种数据结构，for(…) yield 循环创建一个类型和原集合类型相同的新集合。for循环中还可以带守卫：在for中用if来实现。
    for(i <- 0 until arrbuff1.length) yield arrbuff1(i) * 2 //将得到ArrayBuffer(2,6,4,-2,-4)
    for(i <- 0 until (arrbuff1.length,2)) yield arrbuff1(i) * 2 //将得到ArrayBuffer(12,-4)
    for(elem <-0 arrbuff1) print(elem) //如果不需要使用下标，用这种方式最简单了
    for(i <- arrbuff1 if arrbuff1 > 0) print i //打印出arrbuff1中为整数的值
    arrbuff1.filter( _ > 0).map{ 2 * _} //生成arrbuff1中的正数的两倍的新集合
    arrbuff1.filter {_ > 0} map {2 * _} //另一种写法
    4、常用算法：scala有很多便捷内建函数，如
    arrbuff1.sum //对arrbuff1元素求和
    Array("asd","sdf","ss").max //求最大元素
    arrbuff1.sorted(_ < _)  //将arrbuff1元素从小到大排序
    arrbuff1.sorted(_ > _)  //从大到小排序
    util.Sorting.quickSort(Array) //针对数组排序，单不能对数组缓冲排序
    val arr = Array(1,23,4,2,45)
    arr.mkString(",") //指定分割符
    arr.mkString("(",",",")") //指定前缀、分隔符、后缀
更多函数参见Scaladoc
    5、多维数组：
val matrix = Array.ofDim[Int](5,4) //三行四列的二维数组
matrix(2)(3) //访问第二行、第三个元素
    6、scala数组和java互操作：由于scala数组是用java数组实现的，所以可以在java和scala之间来回传递，引入scala.collectin.JavaConversion ，可以在代码中使用scala缓冲，在调用java方法时，这些对象会被自动包装成java列表。反过来讲，引入scala.collection.asScalaBuffer时，当java方法返回java.util.List时，我们可以让它转换成一个Buffer
	
	