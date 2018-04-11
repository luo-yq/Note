

5.4.     Set
注：BitSet（collection.immutable.BitSet）和Set类似，但操作更快

5.4.1.  定义
var s = Set(1,2,3,4,5) // scala.collection.immutable.Set

var s2 = Set[Int]() // scala.collection.immutable.Set[Int]

// 增加元素：

s2 += 1  // Set(1)

s2 += 3  // Set(1,3)

s2 += (2,4) // Set(1,3,2,4)

// 删除元素

Set(1,2,3) - 2 // Set(1,3)

Set(1,2,3) - (1,2) // Set(3)

Set(1,2,3).empty // Set() 全部删除

// 判断是否包含某元素

s(3) // true, 集合中有元素3

s(0) // false, 集合中没有元素0

// 合并

Set(1,2,3) ++ Set(2,3,4) // Set(1, 2, 3, 4)

Set(1,2,3) -- Set(2,3,4) // Set(1)

 

5.4.2.  逻辑运算
运算

例子

交集

Set(1,2,3) & Set(2,3,4) // Set(2,3)

Set(1,2,3) intersect Set(2,3,4)

并集

Set(1,2,3) | Set(2,3,4) // Set(1,2,3,4)

Set(1,2,3) union Set(2,3,4) // Set(1,2,3,4)

差集

Set(1,2,3) &~ Set(2,3,4) // Set(1)

Set(1,2,3) diff Set(2,3,4) // Set(1)

 

5.4.3.  可变BitSet
val bs = collection.mutable.BitSet()

bs += (1,3,5) // BitSet(1, 5, 3)

bs ++= List(7,9) // BitSet(1, 9, 7, 5, 3)

bs.clear // BitSet()





5.4.4.  Common operations for sets
What it is	
What it does
val nums = Set(1, 2, 3)	Creates an immutable set (nums.toString returns Set(1, 2, 3))
nums + 5	Adds an element (returns Set(1, 2, 3, 5))
nums - 3	Removes an element (returns Set(1, 2))
nums ++ List(5, 6)	Adds multiple elements (returns Set(1, 2, 3, 5, 6))
nums -- List(1, 2)	Removes multiple elements (returns Set(3))
nums ** Set(1, 3, 5, 7)	Takes the intersection of two sets (returns Set(1, 3))
nums.size	Returns the size of the set (returns 3)
nums.contains(3)	Checks for inclusion (returns true)
import scala.collection.mutable	Makes the mutable collections easy to access
val words =                      
mutable.Set.empty[String]	Creates an empty, mutable set (words.toString returns Set())
words += "the"	Adds an element (words.toString returns Set(the))
words -= "the"	Removes an element, if it exists (words.toString returns Set())
words ++= List("do", "re", "mi")	Adds multiple elements (words.toString returns Set(do, re, mi))
words --= List("do", "re")	Removes multiple elements (words.toString returns Set(mi))
words.clear	Removes all elements (words.toString returns Set())





集
不重复元素的集合，以哈希集实现，元素根据hashCode方法的值进行组织  
Set(2,0,1) + 1 // (2,0,1)  
  
LinkedHashSet，链式哈希集 记住元素被插入的顺序  
val weekdays = scala.collection.mutable.LinkedHashSet(1,2,3,4)  
  
排序的集  
scala.collection.immutable.SortedSet(1,2,3,4) // 用红黑树实现的  
Scala 2.9没有可变的已排序集，用java.util.TreeSet  
  
位集(bit set), 以一个字位序列的方式存放非负整数，如果集中有i，则第i个字位是1  
高效的实现，只要最大元素不是特别大。  
Scala提供 可变和不可变的两个 BitSet类  
  
contains 检查是否包含， subsetOf 检查集的所有元素是否被另一个集包含  
val digits = Set(1,7,2,9)  
digits contains 0 // false  
Set(1,2) subsetOf digits // true  
[java] view plain copy 在CODE上查看代码片派生到我的代码片
union intersect diff 方法，也可写作| ,&, &~  
union 还可以写成 ++, diff 写作 --  
val primes = Set(2, 3, 5, 7)  
digits union primes // Set(1,2,3,5,7,9)  
digits & primes // Set (2,7)  
digits -- primes // Set(1,9)  








Scala集合为相同类型的配对的不同元素的集合。换句话说，集合是不包含重复元素的集合。有两种集合，不可改变的和可变的。可变和不可变的对象之间的区别在于，当一个对象是不可变的，对象本身不能被改变。

默认情况下，Scala中使用不可变的集。如果想使用可变集，必须明确地导入scala.collection.mutable.Set类。如果想在同一个同时使用可变和不可变的集合，那么可以继续参考不变的集合，但可以参考可变设为mutable.Set。以下是声明不变集合示例：

// Empty set of integer type
var s : Set[Int] = Set()

// Set of integer type
var s : Set[Int] = Set(1,3,5,7)

or 

var s = Set(1,3,5,7)
在定义空集，类型注释是必要的，因为系统需要指定一个具体的类型变量。

集合基本操作：
集合所有操作可以体现在以下三个方法：

方法	描述
head	此方法返回集合的第一个元素。
tail	该方法返回集合由除第一个以外的所有元素。
isEmpty	如果设置为空，此方法返回true，否则为false。
以下是上述方法中的例子显示的用法：

object Test {
   def main(args: Array[String]) {
      val fruit = Set("apples", "oranges", "pears")
      val nums: Set[Int] = Set()

      println( "Head of fruit : " + fruit.head )
      println( "Tail of fruit : " + fruit.tail )
      println( "Check if fruit is empty : " + fruit.isEmpty )
      println( "Check if nums is empty : " + nums.isEmpty )
   }
}


串联集合：
可以使用++运算符或集。++()方法来连接两个或多个集，但同时增加了集它会删除重复的元素。以下是这个例子来连接两个集合：

object Test {
   def main(args: Array[String]) {
      val fruit1 = Set("apples", "oranges", "pears")
      val fruit2 = Set("mangoes", "banana")

      // use two or more sets with ++ as operator
      var fruit = fruit1 ++ fruit2
      println( "fruit1 ++ fruit2 : " + fruit )

      // use two sets with ++ as method
      fruit = fruit1.++(fruit2)
      println( "fruit1.++(fruit2) : " + fruit )
   }
}


查找集合中最大，最小的元素：
可以使用Set.min方法找出最小元素，Set.max方法找出一组可用最大元素。以下为例子来说明的用法：

object Test {
   def main(args: Array[String]) {
      val num = Set(5,6,9,20,30,45)

      // find min and max of the elements
      println( "Min element in Set(5,6,9,20,30,45) : " + num.min )
      println( "Max element in Set(5,6,9,20,30,45) : " + num.max )
   }
}

查找集合的共同值：
可以使用Set.&方法或Set.intersect方法找出两个集合之间的共同值。以下的例子来说明的用法：


object Test {
   def main(args: Array[String]) {
      val num1 = Set(5,6,9,20,30,45)
      val num2 = Set(50,60,9,20,35,55)

      // find common elements between two sets
      println( "num1.&(num2) : " + num1.&(num2) )
      println( "num1.intersect(num2) : " + num1.intersect(num2) )
   }
}


Scala集合方法：
以下是可以同时使用集合的重要方法。有关可用方法的完整列表，请Scala官方文档。

SN	方法及描述
1	def +(elem: A): Set[A] 创建一组新的具有附加元件，除非该元件已经存在
2	def -(elem: A): Set[A] 创建一个新的从这个集合中删除一个给定的元素
3	def contains(elem: A): Boolean 如果elem包含在这个集合返回true，否则为false。
4	def &(that: Set[A]): Set[A] 返回新的集合组成在这个集合，并在给定的集合，所有的元素。
5	def &~(that: Set[A]): Set[A] 返回此集合和另一个集合的差异
6	def +(elem1: A, elem2: A, elems: A*): Set[A] 创建一个新的不可变的集合与来自传递集合额外的元素
7	def ++(elems: A): Set[A] 连接此不可变的集合使用另一个集合到这个不可变的集合的元素。
8	def -(elem1: A, elem2: A, elems: A*): Set[A] 返回包含当前不可变的集合，除了每一个给定参数的元素之一，较少出现的所有元素的不可变的集合。
9	def addString(b: StringBuilder): StringBuilder 这追加不可变的集到一个字符串生成器的所有元素。
10	def addString(b: StringBuilder, sep: String): StringBuilder 这追加不可变的集合使用分隔字符串一个字符串生成器的所有元素。
11	def apply(elem: A) 测试如果一些元素被包含在这个集合。
12	def count(p: (A) => Boolean): Int 计算在不可变的集合满足谓词的元素数。
13	def copyToArray(xs: Array[A], start: Int, len: Int): Unit 这种不可变的集合到一个数组的副本元素。
14	def diff(that: Set[A]): Set[A] 计算这组和另一组的差异。
15	def drop(n: Int): Set[A]] 返回除了第n个的所有元素。
16	def dropRight(n: Int): Set[A] 返回除了最后的n个的所有元素。
17	def dropWhile(p: (A) => Boolean): Set[A] 丢弃满足谓词的元素最长前缀。
18	def equals(that: Any): Boolean equals方法的任意序列。比较该序列到某些其他对象。
19	def exists(p: (A) => Boolean): Boolean 测试谓词是否持有一些这种不可变的集合的元素。
20	def filter(p: (A) => Boolean): Set[A] 返回此不可变的集合满足谓词的所有元素。
21	def find(p: (A) => Boolean): Option[A] 找到不可变的集合满足谓词，如有第一个元素
22	def forall(p: (A) => Boolean): Boolean 测试谓词是否持有这种不可变的集合中的所有元素。
23	def foreach(f: (A) => Unit): Unit 应用一个函数f这个不可变的集合中的所有元素。
24	def head: A 返回此不可变的集合的第一个元素。
25	def init: Set[A] 返回除了最后的所有元素。
26	def intersect(that: Set[A]): Set[A] 计算此set和另一组set之间的交叉点。
27	def isEmpty: Boolean 测试此集合是否为空。
28	def iterator: Iterator[A] 创建一个新的迭代器中包含的可迭代对象中的所有元素。
29	def last: A 返回最后一个元素。
30	def map[B](f: (A) => B): immutable.Set[B] 通过应用函数这一不可变的集合中的所有元素构建一个新的集合。
31	def max: A 查找最大的元素。
32	def min: A 查找最小元素。
33	def mkString: String 显示此不可变的集合字符串中的所有元素。
34	def mkString(sep: String): String 显示此不可变的集合在一个字符串使用分隔字符串的所有元素。
35	def product: A 返回此不可变的集合相对于*操作在num的所有元素的产物。
36	def size: Int 返回此不可变的集合元素的数量。
37	def splitAt(n: Int): (Set[A], Set[A]) 返回一对不可变的集合组成这个不可变的集的前n个元素，以及其他元素。
38	def subsetOf(that: Set[A]): Boolean 返回true，如果此set就是一个子集，也就是说，如果这集合的每个元素也是一个元素。
39	def sum: A 返回此不可变的集合的所有元素的总和使用对于+运算符在num。
40	def tail: Set[A] 返回一个不可变的集合组成这个不可变的集合的所有元素，除了第一个。
41	def take(n: Int): Set[A] 返回前n个元素。
42	def takeRight(n: Int):Set[A] 返回最后n个元素。
43	def toArray: Array[A] 返回一个包含此不可变的集合的所有元素的数组。
44	def toBuffer[B >: A]: Buffer[B] 返回一个包含此不可变的集合中的所有元素的缓冲区。
45	def toList: List[A] 返回一个包含此不可变的集合中的所有元素的列表。
46	def toMap[T, U]: Map[T, U] 这种不可变的集合转换为映射
47	def toSeq: Seq[A] 返回一个包含此不可变的集的所有元素的序列。
48	def toString(): String 返回对象的字符串表示。






Vector是ArrayBuffer的不可变版本，一个带下标的序列，支持快捷的随机访问，以树形结构的形式实现。  
Range表示已个整数序列，只存储 起始值，结束值和增值， 用 to 和 until 方法来构造Range对象。  


一般而言，+用于将元素添加到无先后次序的集合，而+:和:+则是将元素添加到有先后次序的集合的开头或末尾。  
Vector(1,2,3) :+ 5 //Vector(1,2,3,5)  
1 +: Vector(1,2,3) //Vector(1,1,2,3)   
以冒号结尾的操作符，+:是右结合的，这些操作符都返回新的集合  
  
可变集合有 +=操作符 用于修改左侧操作元  
val numbers = ArrayBuffer(1,2,3)  
numbers += 5 // 将 5 添加到 numbers  
  
不可变集合，可以在var上使用+=或:+=  
var numbers = Set(1,2,3)  
numbers += 5 // numbers 设为不可变的集numbers + 5  
var numberVector = Vector(1,2,3)  
numbersVector :+= 5 // 向量没有+操作符，只有:+  
  
移除元素  
Set(1,2,3) -2 // Set(1,3)  
  
++来一次添加多个元素， -- 一次移除多个元素  
col1 ++ col2   
