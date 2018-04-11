
5.3.     Map
5.3.1.  定义Map
var m = Map[Int, Int]()

var m = Map(1->100, 2->200)

或者

var m = Map((1,100), (2,200))

相加：

val m = Map(1->100, 2->200) ++ Map(3->300)  // Map((1,100), (2,200), (3,300))

 

可以用zip()生成Map：

(1 to 10) zip (11 to 20)

List(1,2,3).zip(List(100,200,300)).toMap // Map((1,100), (2,200), (3,300))

注解：zip有“拉拉链”的意思，就是把两排链扣完全对应扣合在一起，非常形象。

5.3.2.  不可变Map(缺省)
l  定义：

val m2 = Map()

val m3 = Map(1->100, 2->200, 3->300) 

指定类型：

val m1:Map[Int,String] = Map(1->"a",2->"b")

注：如果import java.util._后发生冲突，可指明：scala.collection.immutable.Map

保持循序的Map可以使用：

collection.immutable.ListMap

 

l  读取元素：

// m3(1)=100, m3(2)=200, m3(3)=300

// m3.get(1)=Some(100), m3.get(3)=Some(300), m3.get(4)=None

val v = m3.get(4).getOrElse(-1) // -1

或者简化成：

m3.getOrElse(4, -1) // -1

 

l  增加、删除、更新：

Map本身不可改变，即使定义为var，update也是返回一个新的不可变Map：

var m4 = Map(1->100)

m4 += (2->200) // m4指向新的(1->100,2->200), (1->100)应该被回收

另一种更新方式：

m4.updated(1,1000)

增加多个元素：

Map(1->100,2->200) + (3->300, 4->400) // Map((1,100), (2,200), (3,300), (4,400))

删除元素：

  Map(1->100,2->200,3->300) - (2,3) // Map((1,100))

Map(1->100,2->200,3->300) -- List(2,3) // Map((1,100))

 

l  合并Mpa：

Map(1->100,2->200) ++ Map(3->300) // Map((1,100), (2,200), (3,300))

 

5.3.3.  可变Map
val map = scala.collection.mutable.Map[String, Any]()

map("k1")=100     // 增加元素，方法1

map += "k2"->"v2" // 增加元素，方法2

// map("k2")=="v2", map.get("k2")==Some("v2"), map.get("k3")==None

有则取之，无则加之：

val mm = collection.mutable.Map(1->100,2->200,3->300)

mm getOrElseUpdate (3,-1) // 300, mm不变

mm getOrElseUpdate (4,-1) // 300, mm= Map((2,200), (4,-1), (1,100), (3,300))

删除：

val mm = collection.mutable.Map(1->100,2->200,3->300)

mm -= 1 // Map((2,200), (3,300))

mm -= (2,3) // Map()

mm += (1->100,2->200,3->300) // Map((2,200), (1,100), (3,300))

mm --= List(1,2) // Map((3,300))

mm remove 1 // Some(300), mm=Map()

mm += (1->100,2->200,3->300)

mm.retain((x,y) => x>1) // mm = Map((2,200), (3,300))

mm.clearn // mm = Map()

改变value：

mm transform ((x,y)=> 0) // mm = Map((2,0), (1,0), (3,0))

mm transform ((x,y)=> x*10) // Map((2,20), (1,10), (3,30))

mm transform ((x,y)=> y+3) // Map((2,23), (1,13), (3,33))

 

5.3.4.  Java的HashMap
使用Java的HashMap：

val m1:java.util.Map[Int, String] = new java.util.HashMap

5.3.5.  读取所有元素
上面说过，Map(1->100,2->200,3->300) 和 Map((1,100),(2,200),(3,300))的写法是一样的，可见Map中的每一个entry都是一个Tuple，所以：

for(e<-map) println(e._1 + ": " + e._2)

或者

map.foreach(e=>println(e._1 + ": " + e._2))

或者（最好）

for ((k,v)<-map) println(k + ": " + v)

 

也可以进行filter、map操作：

map filter (e=>e._1>1) // Map((2,200), (3,300))

map filterKeys (_>1) // Map((2,200), (3,300))

map.map(e=>(e._1*10, e._2)) // Map(10->100,20->200,30->300)

map map (e=>e._2) // List(100, 200, 300)

相当于：

map.values.toList


5.3.6.  多值Map
结合Map和Tuple，很容易实现一个key对应的value是组合值的数据结构：

val m = Map(1->("james",20), 2->("qh",30), 3->("qiu", 40))

m(2)._1 // "qh"

m(2)._2 // 30

 

for( (k,(v1,v2)) <- m ) printf("%d: (%s,%d)\n", k, v1, v2)



5.3.7. Common operations for maps

What it is	
What it does
val nums = Map("i" -> 1, "ii" -> 2)	Creates an immutable map (nums.toString returns Map(i -> 1, ii -> 2))
nums + ("vi" -> 6)	Adds an entry (returns Map(i -> 1, ii -> 2, vi -> 6))
nums - "ii"	Removes an entry (returns Map(i -> 1))
nums ++ List("iii" -> 3, "v" -> 5)	Adds multiple entries (returns Map(i -> 1, ii -> 2, iii -> 3, v -> 5))
nums -- List("i", "ii")	Removes multiple entries (returns Map())
nums.size	Returns the size of the map (returns 2)
nums.contains("ii")	Checks for inclusion (returns true)
nums("ii")	Retrieves the value at a specified key (returns 2)
nums.keys	Returns the keys (returns an Iterator over the strings "i" and "ii")
nums.keySet	Returns the keys as a set (returns Set(i, ii))
nums.values	Returns the values (returns an Iterator over the integers 1 and 2)
nums.isEmpty	Indicates whether the map is empty (returns false)
import scala.collection.mutable	Makes the mutable collections easy to access
val words =  
mutable.Map.empty[String, Int]	Creates an empty, mutable map
words += ("one" -> 1)	Adds a map entry from "one" to 1 (words.toString returns Map(one -> 1))
words -= "one"	Removes a map entry, if it exists (words.toString returns Map())
words ++= List("one" -> 1, 
"two" -> 2, "three" -> 3)	Adds multiple map entries (words.toString returns Map(one -> 1, two -> 2, three -> 3))
words --= List("one", "two")	Removes multiple objects (words.toString returns Map(three -> 3))








Scala中的映射是键/值对的集合。任何值可以根据它的键进行检索。键是在映射唯一的，但值不一定是唯一的。映射也被称为哈希表。有两种类型的映射，不可变以及可变的。可变和不可变的对象之间的区别在于，当一个对象是不可变的，对象本身不能被改变。

默认情况下，Scala中使用不可变的映射。如果想使用可变集，必须明确地导入scala.collection.mutable.Map类。如果想在同一个同时使用可变和不可变的映射，那么可以继续参考不可变的映射作为映射，但可以参考可变集合为mutable.Map。以下是该示例声明不可变的映射如下：

// Empty hash table whose keys are strings and values are integers:
var A:Map[Char,Int] = Map()

// A map with keys and values.
val colors = Map("red" -> "#FF0000", "azure" -> "#F0FFFF")
在定义空映射，类型注释是必要的，因为系统需要指定一个具体的类型变量。如果我们要一个键值对添加到映射，我们可以使用运算符+如下：

A += ('I' -> 1)
A += ('J' -> 5)
A += ('K' -> 10)
A += ('L' -> 100)
映射的基本操作：
在映射上的所有操作可被表示在下面的三种方法：

方法	描述
keys	这个方法返回一个包含映射中的每个键的迭代。
values	这个方法返回一个包含映射中的每个值的迭代。
isEmpty	如果映射为空此方法返回true，否则为false。
以下是上述方法中的例子显示的用法：

object Test {
   def main(args: Array[String]) {
      val colors = Map("red" -> "#FF0000",
                       "azure" -> "#F0FFFF",
                       "peru" -> "#CD853F")

      val nums: Map[Int, Int] = Map()

      println( "Keys in colors : " + colors.keys )
      println( "Values in colors : " + colors.values )
      println( "Check if colors is empty : " + colors.isEmpty )
      println( "Check if nums is empty : " + nums.isEmpty )
   }
}
当上述代码被编译和执行时，它产生了以下结果：

C:/>scalac Test.scala
C:/>scala Test
Keys in colors : Set(red, azure, peru)
Values in colors : MapLike(#FF0000, #F0FFFF, #CD853F)
Check if colors is empty : false
Check if nums is empty : true

C:/>
串联映射
可以使用++运算符或映射。++()方法来连接两个或更多的映射，但同时增加了映射，将删除重复的键。下面是一个例子来连接两个映射：

object Test {
   def main(args: Array[String]) {
      val colors1 = Map("red" -> "#FF0000",
                        "azure" -> "#F0FFFF",
                        "peru" -> "#CD853F")
      val colors2 = Map("blue" -> "#0033FF",
                        "yellow" -> "#FFFF00",
                        "red" -> "#FF0000")

      // use two or more Maps with ++ as operator
      var colors = colors1 ++ colors2
      println( "colors1 ++ colors2 : " + colors )

      // use two maps with ++ as method
      colors = colors1.++(colors2)
      println( "colors1.++(colors2)) : " + colors )

   }
}
当上述代码被编译和执行时，它产生了以下结果：

C:/>scalac Test.scala
C:/>scala Test
colors1 ++ colors2 : Map(blue -> #0033FF, azure -> #F0FFFF, 
                     peru -> #CD853F, yellow -> #FFFF00, red -> #FF0000)
colors1.++(colors2)) : Map(blue -> #0033FF, azure -> #F0FFFF, 
                     peru -> #CD853F, yellow -> #FFFF00, red -> #FF0000)

C:/>
打印映射的键和值：
可以通过使用foreach循环重复Map的键和值。以下为例子来说明的用法：

object Test {
   def main(args: Array[String]) {
      val colors = Map("red" -> "#FF0000",
                       "azure" -> "#F0FFFF",
                       "peru" -> "#CD853F")

      colors.keys.foreach{ i =>  
                           print( "Key = " + i )
                           println(" Value = " + colors(i) )}
   }
}
在这里，我们使用迭代器相关的foreach遍历键方法。当上述代码被编译和执行时，它产生了以下结果：

C:/>scalac Test.scala
C:/>scala Test
Key = red Value = #FF0000
Key = azure Value = #F0FFFF
Key = peru Value = #CD853F

C:/>
检查映射中的键：
可以使用 Map.contains 方法来测试，如果给定的键存在于映射或没有。以下为例子来说明的用法：


  

 
object Test {
   def main(args: Array[String]) {
      val colors = Map("red" -> "#FF0000",
                       "azure" -> "#F0FFFF",
                       "peru" -> "#CD853F")

      if( colors.contains( "red" )){
           println("Red key exists with value :"  + colors("red"))
      }else{
           println("Red key does not exist")
      }
      if( colors.contains( "maroon" )){
           println("Maroon key exists with value :"  + colors("maroon"))
      }else{
           println("Maroon key does not exist")
      }
   }
}
当上述代码被编译和执行时，它产生了以下结果：

C:/>scalac Test.scala
C:/>scala Test
Red key exists with value :#FF0000
Maroon key does not exist

C:/>
Scala映射的方法：
以下是可以使用映射的重要方法。有关可用方法的完整列表，请Scala的官方文件。

SN	方法及描述
1	def ++(xs: Map[(A, B)]): Map[A, B]返回包含此映射的映射和那些xs提供了一个新的映射。
2	def -(elem1: A, elem2: A, elems: A*): Map[A, B]返回包含除具有一个键等于映射elem1，elem2时或任何元素此映射的所有映射的新映射。
3	def --(xs: GTO[A]): Map[A, B]返回此映射，除映射一键等于从遍历对象xs的一个键所有键/值映射的新映射。
4	def get(key: A): Option[B]可选择返回一个包含键关联的值。
5	def iterator: Iterator[(A, B)]创建一个新的迭代器的所有键/值对在此映射
6	def addString(b: StringBuilder): StringBuilder追加可收缩集合到一个字符串生成器的所有元素。
7	def addString(b: StringBuilder, sep: String): StringBuilder追加可收缩集合到使用分隔字符串一个字符串生成器的所有元素。
8	def apply(key: A): B返回给定键或者映射的默认方法的结果相关联的，如果不存在值。
9	def clear(): Unit从映射中删除所有绑定。在此之后操作已完成时，映射将是空的。
10	def clone(): Map[A, B]创建接收器对象的副本。
11	def contains(key: A): Boolean如果有一个绑定在该映射的键返回true，否则为false。
12	def copyToArray(xs: Array[(A, B)]): Unit复制这个可收缩集合值的数组。填充给定的数组xs与此可收缩集合值。
13	def count(p: ((A, B)) => Boolean): Int计算满足谓词在可收缩集合元素的数量。
14	def default(key: A): B定义默认值计算为映射，当找不到一个键返回。
15	def drop(n: Int): Map[A, B]返回除了第n个的所有元素。
16	def dropRight(n: Int): Map[A, B]返回除了最后n个的所有元素
17	def dropWhile(p: ((A, B)) => Boolean): Map[A, B]丢弃满足谓词的元素最长前缀。
18	def empty: Map[A, B]返回相同类型的，因为这映射的空映射。
19	def equals(that: Any): Boolean返回true，如果两个映射包含完全相同的键/值，否则为false。
20	def exists(p: ((A, B)) => Boolean): Boolean返回true如果给定的断言p成立了一些这方面可收缩集合的元素，否则为false。
21	def filter(p: ((A, B))=> Boolean): Map[A, B]返回此可收缩集合满足谓词的所有元素。
22	def filterKeys(p: (A) => Boolean): Map[A, B]返回一个不可变的映射只包含那些键值对这个映射，重点满足谓词p
23	def find(p: ((A, B)) => Boolean): Option[(A, B)]查找可收缩集合满足谓词，任何的第一要素
24	def foreach(f: ((A, B)) => Unit): Unit应用一个函数f这种可收缩集合中的所有元素
25	def init: Map[A, B]返回除了最后的所有元素
26	def isEmpty: Boolean测试映射是否为空
27	def keys: Iterable[A]返回迭代所有键
28	def last: (A, B)返回最后一个元素
29	def max: (A, B)查找最大的元素
30	def min: (A, B)查找最小元素
31	def mkString: String显示此可收缩集合字符串中的所有元素
32	def product: (A, B)返回此可收缩集合相对于所述运算符*在num所有元素的乘积
33	def remove(key: A): Option[B]移除此映射一个键，返回先前与该键作为一个选项相关联的值
34	def retain(p: (A, B) => Boolean): Map.this.type只保留那些映射其中谓词p返回true
35	def size: Int返回在此映射的元素的数量。
36	def sum: (A, B)返回此可收缩集合中的所有元素的总和使用+运算符在num
37	def tail: Map[A, B]返回除了第一元素外的所有元素
38	def take(n: Int): Map[A, B]返回前n个元素
39	def takeRight(n: Int): Map[A, B]返回最后n个元素
40	def takeWhile(p: ((A, B)) => Boolean): Map[A, B]满足谓词的元素最长前缀
41	def toArray: Array[(A, B)]这个可收缩集合转换成数组
42	def toBuffer[B >: A]: Buffer[B]返回包含此映射中的所有元素的缓冲区
43	def toList: List[A]返回包含此映射中的所有元素的列表
44	def toSeq: Seq[A]返回包含此映射中的所有元素的序列
45	def toSet: Set[A]返回一组包含此映射中的所有元素
46	def toString(): String返回对象的字符串表示
