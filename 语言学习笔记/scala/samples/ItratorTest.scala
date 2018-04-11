

5.5.     Iterator
Iterator不属于集合类型，只是逐个存取集合中元素的方法：

val it = Iterator(1,3,5,7) // Iterator[Int] = non-empty iterator

it foreach println // 1 3 5 7

it foreach println // 无输出

 

三种常用的使用模式：

// 1、使用while

val it = Iterator(1,3,5,7) 或者 val it = List(1,3,5,7).iterator

while(it.hasNext) println(it.next)

// 2、使用for

for(e<- Iterator(1,3,5,7)) println(e)

// 3、使用foreach

Iterator(1,3,5,7) foreach println

 

Iterator也可以使用map的方法：

Iterator(1,3,5,7) map (10*) toList // List(10, 30, 50, 70)

Iterator(1,3,5,7) dropWhile (5>) toList // List(5,7)

 

由于Iterator用一次后就消失了，如果要用两次，需要toList或者使用duplicate：

val (a,b) = Iterator(1,3,5,7) duplicate // a = b = non-empty iterator

又如：

val it = Iterator(1,3,5,7)

val (a,b) = it duplicate

// 在使用a、b前，不能使用it，否则a、b都不可用了。

a toList // List(1,3,5,7)

b toList // List(1,3,5,7)

// 此时it也不可用了

5.6.     Paralllel collection
Scala 2.9+引入：

(1 to 10).par foreach println

多运行几次，注意打印顺序会有不同





迭代器不是集合，而是一种由一个访问的集合之一的元素。在一个迭代的两种基本操作：next和hasNext。调用 it.next()将返回迭代器的下一个元素，推进迭代器的状态。可以找出是否有更多的元素使用迭代器的it.hasNext方法返回。

最简单的方法是使用while循环“单步”将迭代器返回所有的元素。来看一个真实的例子：

object Test {
   def main(args: Array[String]) {
      val it = Iterator("a", "number", "of", "words")
      
      while (it.hasNext){
         println(it.next())
      }
   }
}
当上述代码被编译和执行时，它产生了以下结果：

C:/>scalac Test.scala
C:/>scala Test
a
number
of
words

C:/>
查找最小和最大值的元素：
可以使用 it.min 和 it.max 方法在一个迭代中找出最小和最大值元素。下面是使用示例：

object Test {
   def main(args: Array[String]) {
      val ita = Iterator(20,40,2,50,69, 90)
      val itb = Iterator(20,40,2,50,69, 90)
      
      println("Maximum valued element " + ita.max )
      println("Minimum valued element " + itb.min )

   }
}
这里，我们用ita和itb执行两个不同的操作，因为迭代可以被通过一次。

找出迭代器的长度：
可以使用以下任一方法：it.size 或 it.length 找出在一个迭代可用元素的数目。下面是使用：


 
object Test {
   def main(args: Array[String]) {
      val ita = Iterator(20,40,2,50,69, 90)
      val itb = Iterator(20,40,2,50,69, 90)
      
      println("Value of ita.size : " + ita.size )
      println("Value of itb.length : " + itb.length )

   }
}
这里，我们用ita和itb执行两个不同的操作，因为迭代可以被通过一次。

Scala迭代器方法：
以下是可以使用迭代器的一些重要方法。有关可用方法的完整列表，请Scala的的官方文档。

SN	方法及描述
1	def hasNext: Boolean测试此迭代器是否能提供另一个元素。
2	def next(): A产生这个迭代的下一个元素。
3	def ++(that: => Iterator[A]): Iterator[A]此迭代器与另一个连接。
4	def ++[B >: A](that :=> GenTraversableOnce[B]): Iterator[B]此迭代器与另一个连接。
5	def addString(b: StringBuilder): StringBuilder返回字符串生成器b到元素被追加。
6	def addString(b: StringBuilder, sep: String): StringBuilder返回字符串生成器b到元素被追加使用分隔字符串。
7	def buffered: BufferedIterator[A]从缓冲迭代器创建此迭代器。
8	def contains(elem: Any): Boolean测试此迭代器是否包含给定值作为一个元素。
9	def copyToArray(xs: Array[A], start: Int, len: Int): Unit将选定这个迭代器数组产生的值。
10	def count(p: (A) => Boolean): Int计算在遍历或迭代满足谓词的元素数。
11	def drop(n: Int): Iterator[A]这个推进过去迭代的前n个元素，或迭代器中的较小值长度。
12	def dropWhile(p: (A) => Boolean): Iterator[A]跳过此迭代器满足给定谓词P元素的最长序列，并返回剩余的元素的迭代器。
13	def duplicate: (Iterator[A], Iterator[A])创建两个新的迭代，这两个迭代相同的元件，因为这迭代（以相同的顺序）。
14	def exists(p: (A) => Boolean): Boolean返回true如果给定的断言p成立一些由该迭代器所产生的值，否则为false。
15	def filter(p: (A) => Boolean): Iterator[A]返回一个迭代在这个迭代器，满足谓词p的所有元素。元素的顺序被保留。
16	def filterNot(p: (A) => Boolean): Iterator[A]创建一个迭代器在这个迭代器不符合谓词p的所有元素。
17	def find(p: (A) => Boolean): Option[A]查找由该迭代器满足谓词，如果任意所产生的第一个值。
18	def flatMap[B](f: (A) => GenTraversableOnce[B]): Iterator[B]创建一个新的迭代器通过应用功能，通过这个迭代器产生的所有值和串联的结果。
19	def forall(p: (A) => Boolean): Boolean返回true如果给定的断言p成立了由该迭代器所产生的所有值，否则为false。
20	def foreach(f: (A) => Unit): Unit应用一个函数f通过这个迭代器生成的所有值。
21	def hasDefiniteSize: Boolean返回true如果是空迭代器，否则为false。
22	def indexOf(elem: B): Int返回此迭代的对象的第一个出现的指定对象的索引。
23	def indexWhere(p: (A) => Boolean): Int返回第一个生产值满足谓词的索引，或-1。
24	def isEmpty: Boolean如果hasNext是false返回true，否则为false。
25	def isTraversableAgain: Boolean测试这个迭代器是否可以反复运行。
26	def length: Int返回此迭代的元素数。迭代器就是在这个方法返回后结束
27	def map[B](f: (A) => B): Iterator[B]返回一个新的迭代器，其将通过这个迭代器生产应用函数 f 给它的每一个值
28	def max: A查找最大元素。迭代器就是在这个方法返回后结束
29	def min: A查找最小元素，迭代器就是在这个方法返回后结束
30	def mkString: String显示此遍历的迭代器在一个字符串的所有元素
31	def mkString(sep: String): String使用分隔字符串显示在一个字符串这个遍历的迭代器的所有元素
32	def nonEmpty: Boolean测试可遍历迭代器是不是为空
33	def padTo(len: Int, elem: A): Iterator[A]追加的元素值到这个迭代器的一个给定目标长度
34	def patch(from: Int, patchElems: Iterator[B], replaced: Int): Iterator[B]返回此迭代器补丁值
35	def product: A这个集合中的元素相乘
36	def sameElements(that: Iterator[_]): Boolean返回true，如果两个迭代器产生相同的顺序相同的元素，否则为false
37	def seq: Iterator[A]返回集合的顺序视图
38	def size: Int返回此遍历或迭代的元素数
39	def slice(from: Int, until: Int): Iterator[A]创建一个迭代器返回由这个迭代器所产生的值区间
40	def sum: A返回此遍历的迭代器的所有元素的总和使用对于+运算符在num
41	def take(n: Int): Iterator[A]返回一个迭代只生产这个迭代器，否则整个迭代器的前n个值，如果它产生少于n个值
42	def toArray: Array[A]返回包含此遍历的迭代器的所有元素的数组
43	def toBuffer: Buffer[B]返回包含此遍历的迭代器的所有元素的缓冲区
44	def toIterable: Iterable[A]返回包含此遍历的迭代器的所有元素一个可迭代，这不会终止无限迭代器
45	def toIterator: Iterator[A]返回包含此遍历的迭代器的所有元素的迭代器，这不会终止无限迭代器
46	def toList: List[A]返回包含此遍历的迭代器的所有元素的列表
47	def toMap[T, U]: Map[T, U]返回包含此遍历的迭代器的所有元素的映射
48	def toSeq: Seq[A]返回包含此遍历的迭代器的所有元素的序列
49	def toString(): String此迭代器转换为字符串
50	def zip[B](that: Iterator[B]): Iterator[(A, B)返回一个新的包含迭代器对由此迭代器和相应的元素。由新的迭代器返回的元素数为最小值，通过此迭代器和返回元素的数目
