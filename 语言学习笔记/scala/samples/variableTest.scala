// val 不可变，相当于const/final，但如果val为数组或者List，val的元素可以赋值；

val pi=3.0//val pi=3.0f 
val x,y = 0 // 赋同一初始值
val (x1,y1) = (10, "hello") // 同时定义多个变量，注意：val x,y=10,"hello" 是错误的
val x2::y2 = List(1,2,3,4)  // x 2= 1, y2 = List(2,3,4)
val List(a1,b1,c1) = List(1,2,3) // a1 = 1, b1 = 2, c1 = 3
val Array(a, b, _, _, c @ _*) = Array(1, 2, 3, 4, 5, 6, 7)  // 也可以用List，Seq
val empty_? = true;val + = "hello";val `yield` = 10;val ** = "power"//注意：可用但可读性不好


//var 可变，可重新赋值，赋值为"_"表示缺省值(0, false, null)，仅 val可用"_"表示缺省值赋值。例如：

var d:Double = _ // d = 0.0
var i:Int = _ // i = 0
var s:String = _ // s = null

//var t:T = _  // 泛型T对应的默认值



//尽量使用大写形式： Int, Long, Double, Byte, Short, Char, Float, Double, Boolean
//Unit对应java的void
//用 asInstanseOf[T]方法来强制转换类型：
def i2 = 10.asInstanceOf[Double] // i: Double = 10.0
List('A','B','C').map(c=>(c+32).asInstanceOf[Char]) // List('a','b','c')
//用isInstanceOf[T]方法来判断类型：
val b2 = 10.isInstanceOf[Int] // true



//Int  无++，--操作，但可以+=, -=,
println(-3 abs) // 3
println(-3 max -2) // -2
println(-3 min -2) // -3
println(1.4 round) // 1 四舍五入
println(1.6 round) // 2 四舍五入
println(1.1 ceil) // 2.0 天花板
println(1.1 floor) // 1.0 地板
def even(n:Int) = 0==(n & 1)
//def odd(n:Int) = !even(n)


//Char String可以转化为List[Char]
//在String上做循环，其实就是对String中的每一个Char做操作，如：
println("jamesqiu" max )// 'u'
println("jamesqiu" min )// 'a'
('a' to 'f') map (_.toString*3) // (aaa, bbb, ccc, ddd, eee, fff)


//BigInt可以表示很大的整数：
BigInt("10000000000000000000000000") // scala.math.BigInt = 10000000000000000000000000
def  fac(n:Int):BigInt = if (n==0) 1 else fac(n-1)*n
fac(1000)
//或者写成：
def fac2(n:Int) = ((1:BigInt) to n).product


//字符串 "..." 或者 """..."""
println("""|Welcome to Ultamix 3000.  
           |Type "HELP" for help.""".stripMargin)
//scala中，字符串除了可以+，也可以*
println("abc" * 3)// "abcabcabc"
println("abc" * 0) // ""
println("google".reverse) // "elgoog"
println("abc".reverse.reverse=="abc") // true
println("Hello" map (_.toUpper)) // 相当于 "Hello".toUpperCase


//类型转换
println("101".toInt) // 101，无需 Integer.parseInt("101");
println("3.14".toFloat) // 3.14f
println(101.toString)
println(3.14.toString)
//转换整个列表：
List("1","2","3") map (_.toInt) // List(1,2,3)
//或者
List("1","2","3") map Integer.parseInt // List(1,2,3)


//StringBuilder
val sb = new StringBuilder
sb += 'H'
sb ++= "ello"
sb.toString // "Hello"
sb clear // StringBuilder()

//文本格式化 使用java.text.MessageFormat.format:

val msg = java.text.MessageFormat.format(
    "At {1,time} on {1,date}, there was {2} on planet {0}.",
    "Hoth", new java.util.Date(), "a disturbance in the Force")

"my name is %s, age is %d." format ("james", 30) // my name is james, age is 30.

//注意：format还可以这么用
"%s-%d：%1$s is %2$d." format ("james", 30) // james-30：james is 30.
"%2$d age's man %1$s: %2$d" format ("james", 30) // 30 age's man james: 30

 

// Null, None, Nil, Nothing
//Null  Trait，其唯一实例为null，是AnyRef的子类，*不是* AnyVal的子类
//Nothing  Trait，所有类型（包括AnyRef和AnyVal）的子类，没有实例
//None  Option的两个子类之一，另一个是Some，用于安全的函数返回值
//Unit  无返回值的函数的类型，和java的void对应
//Nil 长度为0的List

 

//==和eq
//Scala的==很智能，他知道对于数值类型要调用Java中的==，ref类型要调用Java的equals()
"hello"=="Hello".toLowerCase()//在java中为false，在scala中为true
//Scala的==总是内容对比
//eq才是引用对比
val s1,s2 = "hello"
val s3 = new String("hello")
s1==s2 // true
s1 eq s2 // true
s1==s3 // true 值相同
s1 eq s3 // false 不是同一个引用











