5.2.1.3     Tuple
val t1 = ("a","b","c")

var t2 = ("a", 123, 3.14, new Date())

val (a,b,c) = (2,4,6)

最简单的Tuple：

1->"hello world"

和下面的写法是等价的：

(1, "hello world")



To access elements of a tuple, you can use method _1 to access the first element, _2 to access the second, and so on:

scala> val v = (1, "Nick", 43)

scala> v._1
res179: Int = 1

scala> v._2
res180: String = Nick
scala> v._3
res181: Int = 43
5.2.1.4     Vector
Scala2.8为了提高list的随机存取效率而引入的新集合类型（而list存取前部的元素快，越往后越慢）。

val v = Vector.empty

val v2 = 0 +: v :+ 10 :+ 20 // Vector(0, 10, 20), Vector 那一边始终有":"

v2(1) // 10

v2 updated (1,100) // Vector(0, 100, 20) 

这个例子举的不太好，scala.collection.immutable. Vector扩展、updated之后是新生成的vector，原vector保持immutable。这点和List类似。

Seq的缺省实现是List：

Seq(1,2,3) // List(1, 2, 3)

IndexSeq的缺省实现是Vector:

IndexSeq(1,2,3) // Vector(1, 2, 3)

 


// Tuple

val t1 = ("a","b","c") // t1._1="a", t1._2="b", t1._3="c"







Scala的元组结合件多个固定数量在一起，使它们可以被传来传去作为一个整体。不像一个数组或列表，元组可以容纳不同类型的对象，但它们也是不可改变的。这里是一个元组持有整数，字符串和Console，如下的一个例子：

val t = (1, "hello", Console)
这是语法修饰(快捷方式)以下：

val t = new Tuple3(1, "hello", Console)
一个元组的实际类型取决于它包含的元素和这些元素的类型的数目。因此，该类型 (99, "Luftballons") 是 Tuple2[Int, String].  ('u', 'r', "the", 1, 4, "me") 的类型是 Tuple6[Char, Char, String, Int, Int, String]

元组类型Tuple1，Tuple2，Tuple3等。至少目前为22的上限在Scala，如果需要更多，那么可以使用一个集合，而不是一个元组。对于每个TupleN类型，其中1<= N <= 22，Scala定义了许多元素的访问方法。给出了以下定义：

val t = (4,3,2,1)
要访问的元组 t 的元素，可以使用的方法t._1访问的第一个元素，t._2进入第二个，依此类推。例如，下面的表达式计算t的所有元素的总和：

val sum = t._1 + t._2 + t._3 + t._4
可以使用三元组来写一个方法，它接受一个List[Double]，并返回计数的总和，并在三个元素的元组返回平方和，Tuple3[Int, Double, Double]。它们也非常有用传递的数据值的列表并发编程之间的消息。下面是一个元组的例子显示使用：

object Test {
   def main(args: Array[String]) {
      val t = (4,3,2,1)
      val sum = t._1 + t._2 + t._3 + t._4
      println( "Sum of elements: "  + sum )
   }
}
让我们编译和运行上面的程序，这将产生以下结果：

C:/>scalac Test.scala
C:/>scala Test
Sum of elements: 10

C:/>
遍历元组：
可以使用Tuple.productIterator()方法来遍历一个元组的所有元素。下面是一个例子来连接两个图：

object Test {
   def main(args: Array[String]) {
      val t = (4,3,2,1)
      
      t.productIterator.foreach{ i =>println("Value = " + i )}
   }
}
让我们编译和运行上面的程序，这将产生以下结果：

C:/>scalac Test.scala
C:/>scala Test
Value = 4
Value = 3
Value = 2
Value = 1

C:/>
转换为字符串：
可以使用Tuple.toString()方法来连接的元组的所有元素为一个字符串。以下为例子来说明的用法：

object Test {
   def main(args: Array[String]) {
      val t = new Tuple3(1, "hello", Console)
      
      println("Concatenated String: " + t.toString() )
   }
}
让我们编译和运行上面的程序，这将产生以下结果：

C:/>scalac Test.scala
C:/>scala Test
Concatenated String: (1,hello,scala.Console$@281acd47)

C:/>
交换元素：
可以使用Tuple.swap方法来交换一个Tuple2的元素。以下为例子来说明的用法：


  

 
object Test {
   def main(args: Array[String]) {
      val t = new Tuple2("Scala", "hello")
      
      println("Swapped Tuple: " + t.swap )
   }
}
让我们编译和运行上面的程序，这将产生以下结果：

C:/>scalac Test.scala
C:/>scala Test
Swapped tuple: (hello,Scala)

C:/>




 