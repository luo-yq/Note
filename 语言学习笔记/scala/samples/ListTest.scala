

//常用操作符（操作符其实也是函数）.函数是左边类型的函数操作   不带.的操作是右边值的运算符
//++ ++[B](that: GenTraversableOnce[B]): List[B] 从列表的尾部添加另外一个列表
//++: ++:[B >: A, That](that: collection.Traversable[B])(implicit bf: CanBuildFrom[List[A], B, That]): That 在列表的头部添加一个列表
//+: +:(elem: A): List[A] 在列表的头部添加一个元素
//:+ :+(elem: A): List[A] 在列表的尾部添加一个元素
//:: ::(x: A): List[A] 在列表的头部添加一个元素
//::: :::(prefix: List[A]): List[A] 在列表的头部添加另外一个列表
//:\ :[B](z: B)(op: (A, B) ⇒ B): B 与foldRight等价
val left = List(1,2,3)
val right = List(4,5,6)

//以下操作等价
left ++ right   // List(1,2,3,4,5,6)
left ++: right  // List(1,2,3,4,5,6)
right.++:(left)    // List(1,2,3,4,5,6)
right.:::(left)  // List(1,2,3,4,5,6)

//以下操作等价
0 +: left    //List(0,1,2,3)
left.+:(0)   //List(0,1,2,3)

//以下操作等价
left :+ 4    //List(1,2,3,4)
left.:+(4)   //List(1,2,3,4)

//以下操作等价
0 :: left      //List(0,1,2,3)
left.::(0)     //List(0,1,2,3)



//常用变换操作
//map[B](f: (A) ⇒ B): List[B]定义一个变换,把该变换应用到列表的每个元素中,原列表不变，返回一个新的列表数据
// 平方变换
val nums = List(1,2,3)
val square = (x: Int) => x*x   
val squareNums1 = nums.map(num => num*num)    //List(1,4,9)
//使用占位符_简写匿名函数
val squareNums2 = nums.map(math.pow(_,2))    //List(1,4,9)
val squareNums3 = nums.map(square)            //List(1,4,9)

// 保存文本数据中的某几列
val text = List("Homeway,25,Male","XSDYM,23,Female")
val usersList = text.map(_.split(",")(0))    
val usersWithAgeList = text.map(line => {
    val fields = line.split(",")
    val user = fields(0)
    val age = fields(1).toInt
    (user,age)
})


//flatten: flatten[B]: List[B] 对列表的列表进行平坦化操作 
//flatMap: flatMap[B](f: (A) ⇒ GenTraversableOnce[B]): List[B] map之后对结果进行flatten
//定义一个变换f, 把f应用列表的每个元素中，每个f返回一个列表，最终把所有列表连结起来。
val text2 = List("A,B,C","D,E,F")
val textMapped = text2.map(_.split(",").toList) // List(List("A","B","C"),List("D","E","F"))
val textFlattened = textMapped.flatten          // List("A","B","C","D","E","F")
val textFlatMapped = text2.flatMap(_.split(",").toList) // List("A","B","C","D","E","F")

//reduce[A1 >: A](op: (A1, A1) ⇒ A1): A1 定义一个变换f, f把两个列表的元素合成一个，遍历列表，最终把列表合并成单一元素
val sum1 = nums.reduce((a,b) => a+b)   //6
val sum2 = nums.reduce(_+_)            //6
val sum3 = nums.sum                 //6


//reduceLeft: reduceLeft[B >: A](f: (B, A) ⇒ B): B 从列表的左边往右边应用reduce函数
//reduceRight: reduceRight[B >: A](op: (A, B) ⇒ B): B 从列表的右边往左边应用reduce函数
val nums3 = List(2.0,2.0,3.0)
val resultLeftReduce = nums3.reduceLeft(math.pow)  // = pow( pow(2.0,2.0) , 3.0) = 64.0
val resultRightReduce = nums3.reduceRight(math.pow) // = pow(2.0, pow(2.0,3.0)) = 256.0


//fold: fold[A1 >: A](z: A1)(op: (A1, A1) ⇒ A1): A1 带有初始值的reduce,从一个初始值开始，从左向右将两个元素合并成一个，最终把列表合并成单一元素。
//foldLeft: foldLeft[B](z: B)(f: (B, A) ⇒ B): B 带有初始值的reduceLeft
//foldRight: foldRight[B](z: B)(op: (A, B) ⇒ B): B 带有初始值的reduceRight
val nums4 = List(2,3,4)
val sum = nums4.fold(1)(_+_)  // = 1+2+3+4 = 9
val nums41 = List(2.0,3.0)
val result1 = nums41.foldLeft(4.0)(math.pow) // = pow(pow(4.0,2.0),3.0) = 4096
val result2 = nums41.foldRight(1.0)(math.pow) // = pow(1.0,pow(2.0,3.0)) = 8.0


//sortBy: sortBy[B](f: (A) ⇒ B)(implicit ord: math.Ordering[B]): List[A] 按照应用函数f之后产生的元素进行排序
//sorted： sorted[B >: A](implicit ord: math.Ordering[B]): List[A] 按照元素自身进行排序
//sortWith： sortWith(lt: (A, A) ⇒ Boolean): List[A] 使用自定义的比较函数进行排序
val nums5 = List(1,3,2,4)
val sorted = nums5.sorted  //List(1,2,3,4)
val users = List(("HomeWay",25),("XSDYM",23))
val sortedByAge = users.sortBy{case(user,age) => age}  //List(("XSDYM",23),("HomeWay",25))
val sortedWith = users.sortWith{case(user1,user2) => user1._2 < user2._2} //List(("XSDYM",23),("HomeWay",25))


//filter: filter(p: (A) ⇒ Boolean): List[A] 保留列表中符合条件p的列表元素
//filterNot: filterNot(p: (A) ⇒ Boolean): List[A] 保留列表中不符合条件p的列表元素
val odd = nums5.filter( _ % 2 != 0) // List(1,3)
val even = nums5.filterNot( _ % 2 != 0) // List(2,4)


//count(p: (A) ⇒ Boolean): Int 计算列表中所有满足条件p的元素的个数，等价于 filter(p).length
val nums6 = List(-1,-2,0,1,2) 
val plusCnt1 = nums6.count( _ > 0 ) 
val plusCnt2 = nums6.filter( _ > 0 ).length 

//diff:diff(that: collection.Seq[A]): List[A] 保存列表中那些不在另外一个列表中的元素，即从集合中减去与另外一个集合的交集
//union : union(that: collection.Seq[A]): List[A] 与另外一个列表进行连结
//intersect: intersect(that: collection.Seq[A]): List[A] 与另外一个集合的交集
val nums1 = List(1,2,3)
val nums2 = List(2,3,4)
val diff1 = nums1 diff nums2   // List(1)
val diff2 = nums2.diff(nums1)   // List(4)
val union1 = nums1 union nums2  // List(1,2,3,2,3,4)
val union2 = nums2 ++ nums1        // List(2,3,4,1,2,3)
val intersection = nums1 intersect nums2  //List(2,3)


//distinct: List[A] 保留列表中非重复的元素，相同的元素只会被保留一次
val list = List("A","B","C","A","B") 
val distincted = list.distinct // List("A","B","C")

//groupBy : groupBy[K](f: (A) ⇒ K): Map[K, List[A]] 将列表进行分组，分组的依据是应用f在元素上后产生的新元素 
//grouped: grouped(size: Int): Iterator[List[A]] 按列表按照固定的大小进行分组
val data = List(("HomeWay","Male"),("XSDYM","Femail"),("Mr.Wang","Male"))
val group1 = data.groupBy(_._2) // = Map("Male" -> List(("HomeWay","Male"),("Mr.Wang","Male")),"Female" -> List(("XSDYM","Femail")))
val group2 = data.groupBy{case (name,sex) => sex} // = Map("Male" -> List(("HomeWay","Male"),("Mr.Wang","Male")),"Female" -> List(("XSDYM","Femail")))
val fixSizeGroup = data.grouped(2).toList // = Map("Male" -> List(("HomeWay","Male"),("XSDYM","Femail")),"Female" -> List(("Mr.Wang","Male")))


//scan[B >: A, That](z: B)(op: (B, B) ⇒ B)(implicit cbf: CanBuildFrom[List[A], B, That]): That 由一个初始值开始，从左向右，进行积累的op操作，这个比较难解释，具体的看例子吧。
val result = nums.scan(10)(_+_)   // List(10,10+1,10+1+2,10+1+2+3) = List(10,11,12,13)


//scanLeft: scanLeft[B, That](z: B)(op: (B, A) ⇒ B)(implicit bf: CanBuildFrom[List[A], B, That]): That
//scanRight: scanRight[B, That](z: B)(op: (A, B) ⇒ B)(implicit bf: CanBuildFrom[List[A], B, That]): That
//scanLeft: 从左向右进行scan函数的操作，scanRight：从右向左进行scan函数的操作
val nums7 = List(1.0,2.0,3.0)
val result21 = nums7.scanLeft(2.0)(math.pow)   // List(2.0,pow(2.0,1.0), pow(pow(2.0,1.0),2.0),pow(pow(pow(2.0,1.0),2.0),3.0) = List(2.0,2.0,4.0,64.0)
val result31 = nums7.scanRight(2.0)(math.pow)  // List(2.0,pow(3.0,2.0), pow(2.0,pow(3.0,2.0)), pow(1.0,pow(2.0,pow(3.0,2.0))) = List(1.0,512.0,9.0,2.0)


//take : takeRight(n: Int): List[A] 提取列表的前n个元素 
//takeRight: takeRight(n: Int): List[A] 提取列表的最后n个元素 
//takeWhile: takeWhile(p: (A) ⇒ Boolean): List[A] 从左向右提取列表的元素，直到条件p不成立
val nums8 = List(1,1,1,1,4,4,4,4)
val left1 = nums8.take(4)   // List(1,1,1,1)
val right1 = nums8.takeRight(4) // List(4,4,4,4)
val headNums = nums8.takeWhile( _ == nums8.head)  // List(1,1,1,1)

//drop: drop(n: Int): List[A] 丢弃前n个元素，返回剩下的元素 
//dropRight: dropRight(n: Int): List[A] 丢弃最后n个元素，返回剩下的元素 
//dropWhile: dropWhile(p: (A) ⇒ Boolean): List[A] 从左向右丢弃元素，直到条件p不成立
val left2 = nums8.drop(4)   // List(4,4,4,4)
val right3 = nums8.dropRight(4) // List(1,1,1,1)
val tailNums = nums8.dropWhile( _ == nums8.head)  // List(4,4,4,4)

//span : span(p: (A) ⇒ Boolean): (List[A], List[A]) 从左向右应用条件p进行判断，直到条件p不成立，此时将列表分为两个列表
//splitAt: splitAt(n: Int): (List[A], List[A]) 将列表分为前n个，与，剩下的部分
//partition: partition(p: (A) ⇒ Boolean): (List[A], List[A]) 将列表分为两部分，第一部分为满足条件p的元素，第二部分为不满足条件p的元素
val nums9 = List(1,1,1,2,3,2,1)
val (prefix,suffix) = nums9.span( _ == 1) // prefix = List(1,1,1), suffix = List(2,3,2,1)
val (prefix2,suffix2) = nums9.splitAt(3)  // prefix = List(1,1,1), suffix = List(2,3,2,1)
val (prefix3,suffix3) = nums9.partition( _ == 1) // prefix = List(1,1,1,1), suffix = List(2,3,2)

//padTo(len: Int, elem: A): List[A] 将列表扩展到指定长度，长度不够的时候，使用elem进行填充，否则不做任何操作。
val nums10 = List(1,1,1)
val padded = nums10.padTo(6,2)   // List(1,1,1,2,2,2)

//combinations: combinations(n: Int): Iterator[List[A]] 取列表中的n个元素进行组合，返回不重复的组合列表，结果一个迭代器
//permutations: permutations: Iterator[List[A]] 对列表中的元素进行排列，返回不重得的排列列表，结果是一个迭代器
val nums11 = List(1,1,3)
val combinations = nums11.combinations(2).toList //List(List(1,1),List(1,3))
val permutations = nums11.permutations.toList        // List(List(1,1,3),List(1,3,1),List(3,1,1))

//zip: zip[B](that: GenIterable[B]): List[(A, B)] 与另外一个列表进行拉链操作，将对应位置的元素组成一个pair，返回的列表长度为两个列表中短的那个
//zipAll: zipAll[B](that: collection.Iterable[B], thisElem: A, thatElem: B): List[(A, B)] 与另外一个列表进行拉链操作，将对应位置的元素组成一个pair，若列表长度不一致，自身列表比较短的话使用thisElem进行填充，对方列表较短的话使用thatElem进行填充
//zipWithIndex：zipWithIndex: List[(A, Int)] 将列表元素与其索引进行拉链操作，组成一个pair
//unzip: unzip[A1, A2](implicit asPair: (A) ⇒ (A1, A2)): (List[A1], List[A2]) 解开拉链操作
//unzip3: unzip3[A1, A2, A3](implicit asTriple: (A) ⇒ (A1, A2, A3)): (List[A1], List[A2], List[A3]) 3个元素的解拉链操作
val alphabet = List("A","B","C")
val nums12 = List(1,2)
val zipped = alphabet zip nums12   // List(("A",1),("B",2))
val zippedAll = alphabet.zipAll(nums12,"*",-1)   // List(("A",1),("B",2),("C",-1))
val zippedIndex = alphabet.zipWithIndex  // List(("A",0),("B",1),("C",3))
val (list1,list2) = zipped.unzip        // list1 = List("A","B"), list2 = List(1,2)
val (l1,l2,l3) = List((1, "one", '1'),(2, "two", '2'),(3, "three", '3')).unzip3   // l1=List(1,2,3),l2=List("one","two","three"),l3=List('1','2','3')


//slice(from: Int, until: Int): List[A] 提取列表中从位置from到位置until(不含该位置)的元素列表
val nums13 = List(1,2,3,4,5)
val sliced = nums13.slice(2,4)  //List(3,4)


//sliding(size: Int, step: Int): Iterator[List[A]] 将列表按照固定大小size进行分组，步进为step，step默认为1,返回结果为迭代器
val nums14 = List(1,1,2,2,3,3,4,4)
val groupStep2 = nums14.sliding(2,2).toList  //List(List(1,1),List(2,2),List(3,3),List(4,4))
val groupStep1 = nums14.sliding(2).toList //List(List(1,1),List(1,2),List(2,2),List(2,3),List(3,3),List(3,4),List(4,4))

//updated(index: Int, elem: A): List[A] 对列表中的某个元素进行更新操作
val nums15 = List(1,2,3,3)
val fixed = nums15.updated(3,4)  // List(1,2,3,4)





5.2.1.2     List
val list:List[Int] = List(1,3,4,5,6) // 或者 List(1 to 6:_*)

val list1 = List("a","b","c","d") // 或者 List('a' to 'd':_*) map (_.toString)

元素合并进List用::

val list2 = "a"::"b"::"c"::Nil // Nil是必须的

val list3 = "begin" :: list2 // list2不变，只能加在头，不能加在尾

多个List合并用++，也可以用:::(不如++)

val list4 = list2 ++ "end" ++ Nil

val list4 = list2 ::: "end" :: Nil // 相当于 list2 ::: List("end")

 

当 import Java.util._ 之后会产生冲突，需要指明包

scala.List(1,2,3)

 

List对应的可变ListBuffer：



val lb = scala.collection.mutable.ListBuffer(1,2,3)

lb.append(4) // ListBuffer(1, 2, 3, 4)


val lb = collection.mutable.ListBuffer[Int]()

lb += (1,3,5,7)

lb ++= List(9,11) // ListBuffer(1, 3, 5, 7, 9, 11)

lb.toList // List(1, 3, 5, 7, 9, 11)

lb.clear // ListBuffer()

建议定义方式：

val head::body = List(4,"a","b","c","d")

// head: Any = 4

// body: List[Any] = List(a, b, c, d)

val a::b::c = List(1,2,3)

// a: Int = 1

// b: Int = 2

// c: List[Int] = List(3)

 

定义固定长度的List：

List.fill(10)(2) // List(2, 2, 2, 2, 2, 2, 2, 2, 2, 2)

Array.fill(10)(2) // Array(2, 2, 2, 2, 2, 2, 2, 2, 2, 2)

又如：

List.fill(10)(scala.util.Random.nextPrintableChar)

// List(?, =, ^, L, p, <, \, 4, 0, !)

List.fill(10)(scala.util.Random.nextInt(101))

// List(80, 45, 26, 75, 24, 72, 96, 88, 86, 15)




5.2.2.  使用(map, flatMap, filter, exists等)
5.2.2.1     map
// 类型可以混合：

import java.util._

val list3 = Array("a", 123, 3.14, new Date())


List("a","b","c").map(s=> s.toUpperCase()) // 方式1

List("a","b","c").map(_.toUpperCase())     // 方式2, 类似于Groovy的it

// = List(A, B, C)

 

5.2.2.2     filter filterNot
List(1,2,3,4,5).filter(_%2==0) // List(2, 4)

也可以写成：

for (x<-List(1,2,3,4,5) if x%2==0) yield x

 

List(1,2,3,4,5).filterNot(_%2==0) // List(1, 3, 5)

 

5.2.2.3     partition span splitAt groupBy
注：val (a,b) = List(1,2,3,4,5).partition(_%2==0) // (List(2,4), List(1,3,5))

可把Collection分成：满足条件的一组，其他的另一组。

和partition相似的是span，但有不同：

List(1,9,2,4,5).span(_<3)       // (List(1),List(9, 2, 4, 5))，碰到不符合就结束

List(1,9,2,4,5).partition(_<3) // (List(1, 2),List(9, 4, 5))，扫描所有

 

List(1,3,5,7,9) splitAt 2 // (List(1, 3),List(5, 7, 9))

List(1,3,5,7,9) groupBy (5<) // Map((true,List(7, 9)), (false,List(1, 3, 5)))

5.2.2.4     foreach
打印：

Array("a","b","c","d").foreach(printf("[%s].",_))

// [a].[b].[c].[d].

 

5.2.2.5     exists
// 集合中是否存在符合条件的元素

List(1,2,3,4,5).exists(_%3==0) // true

 

5.2.2.6     find
返回序列中符合条件的第一个。

例子：查找整数的第一个因子（最小因子、质数）

def fac1(n:Int) = if (n>= -1 && n<=1) n else (2 to n.abs) find (n%_==0) get

5.2.2.7     sorted sortWith sortBy
例子（排序）：

List(1,3,2,0,5,9,7).sorted //  List(0, 1, 2, 3, 5, 7, 9)

List(1,3,2,0,5,9,7).sortWith(_>_) // List(9, 7, 5, 3, 2, 1, 0)

List("abc", "cb", "defe", "z").sortBy(_.size) // List(z, cb, abc, defe)

List((1,'c'), (1,'b'), (2,'a')) .sortBy(_._2) // List((2,a), (1,b), (1,c))

 

5.2.2.8     distinct
例子：（去除List中的重复元素）

def uniq[T](l:List[T]) = l.distinct

uniq(List(1,2,3,2,1)) // List(1,2,3)

 

5.2.2.9     flatMap
flatMap的作用：把多层次的数据结构“平面化”，并去除空元素（如None）。

可用于：得到xml等树形结构的所有节点名称，去除None等

 

例子1a：（两个List做乘法）

List(1,2,3) * List(10,20,30) = List(10, 20, 30, 20, 40, 60, 30, 60, 90)

val (a,b) = (List(1,2,3), List(10,20,30))

a flatMap (i=> b map (j=> i*j))

等同于：

for (i<-a; i<-b) yield i*j // 这个写法更清晰

例子1b：

如果不用flatMap而是用map，结果就是：

a map (i=> b map (j=> i*j)) // List(List(10, 20, 30), List(20, 40, 60), List(30, 60, 90))

等同于：

for (i<-a) yield { for (j<-b) yield i*j } // 不如上面的清晰

 

例子2：

List("abc","def") flatMap (_.toList) // List(a, b, c, d, e, f)

而

List("abc","def") map (_.toList) // List(List(a, b, c), List(d, e, f))

 

例子3：flatMap结合Option

def toint(s:String) =

try { Some(Integer.parseInt(s)) } catch { case e:Exception => None }

List("123", "12a", "45") flatMap toint // List(123, 45)

List("123", "12a", "45") map toint // List(Some(123), None, Some(45))

5.2.2.10   indices，zipWithIndex, slice
得到indices：

val a = List(100,200,300)

a indices // (0,1,2)

a zipWithIndex // ((100,0), (200,1), (300,2))

(a indices) zip a // ((0,100), (1,200), (2,300))

 

截取一部分,相当于String的substring

List(100,200,300,400,500) slice (2,4) // (300,400), 取l(2), l(3)

 

5.2.2.11   take drop splitAt
List(1,3,5,7) take 2 // List(1,3)

List(1,3,5,7) drop 2 // List(5,7)

5.2.2.12   count
满足条件的元素数目：

例如1000内质数的个数：

def prime(n:Int) = if (n<2) false else 2 to math.sqrt(n).toInt forall (n%_!=0)

1 to 1000 count prime  // 168

 

5.2.2.13   updated patch
对于immutable的数据结构，使用updated返回一个新的copy：

val v1 = List(1,2,3,4)

v1.updated(3,10) // List(1, 2, 3, 10), v1还是List(1, 2, 3, 4)

 

对于可变的数据结构，直接更改：

val mseq = scala.collection.mutable.ArraySeq(1, 2, 4, 6)

mseq(3) = 10 // mseq = ArraySeq(1, 2, 4, 10)

 

批量替换，返回新的copy：

val v1 = List(1,2,3,4,5)

val v2 = List(10,20,30)

v1 patch (0, v2, 3) // List(10,20,30,4,5), 但v1,v2不变

5.2.2.14   reverse reverseMap
1 to 5 reverse // Range(5, 4, 3, 2, 1)

"james".reverse.reverse // "james"

reverseMap就是revese + map

  1 to 5 reverseMap (10*) // Vector(50, 40, 30, 20, 10)

相当于：

  (1 to 5 reverse) map (10*)

 

5.2.2.16   集合运算
List(1,2,3,4) intersect List(4,3,6) // 交集 = List(3, 4)

List(1,2,3,4) diff List(4,3,6) // A-B = List(1, 2)

List(1,2,3,4) union List(4,3,6) // A+B = List(1, 2, 3, 4, 4, 3, 6)

// 相当于

List(1,2,3,4) ++ List(4,3,6) // A+B = List(1, 2, 3, 4, 4, 3, 6)

 
// List

val list = List("a","b","c")

// list(0)=="a", list(1)=="b", list(2)=="c"

由于List不是index sequence，定位访问成本高，不建议使用。同样不建议使用的还有List 的 length

 


列表
列表要么是Nil(空表)，要么是一个head元素和一个tail，tail又是一个列表。  
val digits = List(4,2)  
digits.head <span style="white-space:pre">        </span>//4  
digits.tail <span style="white-space:pre">        </span>// List(2)  
digits.tail.head <span style="white-space:pre">   </span>// 2  
digits.tail.tail <span style="white-space:pre">   </span>//Nil  
  

迭代， 除了遍历外，可以用 递归 模式匹配  
  
def sum(lst : List[Int]): Int =   
  if( lst == Nil) 0 else lst.head + sum(lst.tail)  
  
def sum(lst:List[Int]): Int = lst match{  
  case Nil => 0  
  case h :: t => h+sum(t) // h 是 lst.head， 而t是lst.tail, ::将列表“析构”成头部和尾部  
}  
直接使用List的方法  
List(9,4,2).sum  

可变列表
LinkedList, elem指向当前值，next指向下一个元素  
DoubleLinkedList多带一个prev  
val lst = scala.collection.mutable.LinkedList(1,-2,7,-9)  
var cur = lst  
while(cur != Nil){  
  if(cur.elem<0) cur.elem = 0  
  cur = cur.next  
} // (1,0,7,0) ，将所有负值改为0  
var cur = lst   
while(cur != Nil && cur.next != Nil){  
  cur.next = cur.next.next  
  cur = cur.next  
}// 去除每两个元素中的一个  
注：当要把某个节点变为列表中的最后一个节点，不能讲next 设为Nil 或 null, 而将它设为LinkedList.empty。  



Scala中列表是非常类似于数组，这意味着，一个列表的所有元素都具有相同的类型，但有两个重要的区别。首先，列表是不可变的，这意味着一个列表的元素可以不被分配来改变。第二，列表表示一个链表，而数组平坦的。

具有T类型的元素的列表的类型被写为List[T]。例如，这里有各种数据类型定义的一些列表：

// List of Strings
val fruit: List[String] = List("apples", "oranges", "pears")

// List of Integers
val nums: List[Int] = List(1, 2, 3, 4)

// Empty List.
val empty: List[Nothing] = List()

// Two dimensional list
val dim: List[List[Int]] =
   List(
      List(1, 0, 0),
      List(0, 1, 0),
      List(0, 0, 1)
   )
所有的列表可以使用两种基本的构建模块来定义，一个无尾Nil和::，这有明显的缺点。Nil也代表了空列表。所有上述列表可以定义如下：

// List of Strings
val fruit = "apples" :: ("oranges" :: ("pears" :: Nil))

// List of Integers
val nums = 1 :: (2 :: (3 :: (4 :: Nil)))

// Empty List.
val empty = Nil

// Two dimensional list
val dim = (1 :: (0 :: (0 :: Nil))) ::
          (0 :: (1 :: (0 :: Nil))) ::
          (0 :: (0 :: (1 :: Nil))) :: Nil
列表的基本操作：
上列出了所有的操作都可以体现在以下三个方法来讲：

方法	描述
head	此方法返回的列表中的第一个元素。
tail	此方法返回一个由除了第一个元素外的所有元素的列表。
isEmpty	如果列表为空，此方法返回true，否则为false。
以下是上述方法中的例子显示用法：

object Test {
   def main(args: Array[String]) {
      val fruit = "apples" :: ("oranges" :: ("pears" :: Nil))
      val nums = Nil

      println( "Head of fruit : " + fruit.head )
      println( "Tail of fruit : " + fruit.tail )
      println( "Check if fruit is empty : " + fruit.isEmpty )
      println( "Check if nums is empty : " + nums.isEmpty )
   }
}


串联列表：
可以使用:::运算符或列表List.:::()方法或List.concat()方法来添加两个或多个列表。下面是一个例子：

object Test {
   def main(args: Array[String]) {
      val fruit1 = "apples" :: ("oranges" :: ("pears" :: Nil))
      val fruit2 = "mangoes" :: ("banana" :: Nil)

      // use two or more lists with ::: operator
      var fruit = fruit1 ::: fruit2
      println( "fruit1 ::: fruit2 : " + fruit )
      
      // use two lists with Set.:::() method
      fruit = fruit1.:::(fruit2)
      println( "fruit1.:::(fruit2) : " + fruit )

      // pass two or more lists as arguments
      fruit = List.concat(fruit1, fruit2)
      println( "List.concat(fruit1, fruit2) : " + fruit  )
      

   }
}


创建统一列表：
可以使用List.fill()方法创建，包括相同的元素如下的零个或更多个拷贝的列表：

object Test {
   def main(args: Array[String]) {
      val fruit = List.fill(3)("apples") // Repeats apples three times.
      println( "fruit : " + fruit  )

      val num = List.fill(10)(2)         // Repeats 2, 10 times.
      println( "num : " + num  )
   }
}


制成表格一个功能：
可以使用一个函数连同List.tabulate()方法制表列表之前的列表中的所有元素以应用。它的参数是一样List.fill：第一个参数列表给出的列表的尺寸大小，而第二描述列表的元素。唯一的区别在于，代替的元素被固定，它们是从一个函数计算：

object Test {
   def main(args: Array[String]) {
      // Creates 5 elements using the given function.
      val squares = List.tabulate(6)(n => n * n)
      println( "squares : " + squares  )

      // 
      val mul = List.tabulate( 4,5 )( _ * _ )      
      println( "mul : " + mul  )
   }
}


反向列表顺序：
可以使用List.reverse方法来扭转列表中的所有元素。以下为例子来说明的用法：


  

 
object Test {
   def main(args: Array[String]) {
      val fruit = "apples" :: ("oranges" :: ("pears" :: Nil))
      println( "Before reverse fruit : " + fruit )

      println( "After reverse fruit : " + fruit.reverse )
   }
}


Scala列表方法：
以下是重要的方法，可以在使用列表时。有关可用方法的完整列表，请Scala的官方文件。

SN	方法及描述
1	def +(elem: A): List[A]前置一个元素列表
2	def ::(x: A): List[A]在这个列表的开头添加的元素。
3	def :::(prefix: List[A]): List[A]增加了一个给定列表中该列表前面的元素。
4	def ::(x: A): List[A]增加了一个元素x在列表的开头
5	def addString(b: StringBuilder): StringBuilder追加列表的一个字符串生成器的所有元素。
6	def addString(b: StringBuilder, sep: String): StringBuilder追加列表的使用分隔字符串一个字符串生成器的所有元素。
7	def apply(n: Int): A选择通过其在列表中索引的元素
8	def contains(elem: Any): Boolean测试该列表中是否包含一个给定值作为元素。
9	def copyToArray(xs: Array[A], start: Int, len: Int): Unit列表的副本元件阵列。填充给定的数组xs与此列表中最多len个元素，在位置开始。
10	def distinct: List[A]建立从列表中没有任何重复的元素的新列表。
11	def drop(n: Int): List[A]返回除了第n个的所有元素。
12	def dropRight(n: Int): List[A]返回除了最后的n个的元素
13	def dropWhile(p: (A) => Boolean): List[A]丢弃满足谓词的元素最长前缀。
14	def endsWith[B](that: Seq[B]): Boolean测试列表是否使用给定序列结束。
15	def equals(that: Any): Boolean equals方法的任意序列。比较该序列到某些其他对象。
16	def exists(p: (A) => Boolean): Boolean测试谓词是否持有一些列表的元素。
17	def filter(p: (A) => Boolean): List[A]返回列表满足谓词的所有元素。
18	def forall(p: (A) => Boolean): Boolean测试谓词是否持有该列表中的所有元素。
19	def foreach(f: (A) => Unit): Unit应用一个函数f以列表的所有元素。
20	def head: A选择列表的第一个元素
21	def indexOf(elem: A, from: Int): Int经过或在某些起始索引查找列表中的一些值第一次出现的索引。
22	def init: List[A]返回除了最后的所有元素
23	def intersect(that: Seq[A]): List[A]计算列表和另一序列之间的多重集交集。
24	def isEmpty: Boolean测试列表是否为空
25	def iterator: Iterator[A]创建一个新的迭代器中包含的可迭代对象中的所有元素
26	def last: A返回最后一个元素
27	def lastIndexOf(elem: A, end: Int): Int之前或在一个给定的最终指数查找的列表中的一些值最后一次出现的索引
28	def length: Int返回列表的长度
29	def map[B](f: (A) => B): List[B]通过应用函数以g这个列表中的所有元素构建一个新的集合
30	def max: A查找最大的元素
31	def min: A查找最小元素
32	def mkString: String显示列表的字符串中的所有元素
33	def mkString(sep: String): String显示的列表中的字符串中使用分隔串的所有元素
34	def reverse: List[A]返回新列表，在相反的顺序元素
35	def sorted[B >: A]: List[A]根据排序对列表进行排序
36	def startsWith[B](that: Seq[B], offset: Int): Boolean测试该列表中是否包含给定的索引处的给定的序列
37	def sum: A概括这个集合的元素
38	def tail: List[A]返回除了第一的所有元素
39	def take(n: Int): List[A]返回前n个元素
40	def takeRight(n: Int): List[A]返回最后n个元素
41	def toArray: Array[A]列表以一个数组变换
42	def toBuffer[B >: A]: Buffer[B]列表以一个可变缓冲器转换
43	def toMap[T, U]: Map[T, U]此列表的映射转换
44	def toSeq: Seq[A]列表的序列转换
45	def toSet[B >: A]: Set[B]列表到集合变换
46	def toString(): String列表转换为字符串


