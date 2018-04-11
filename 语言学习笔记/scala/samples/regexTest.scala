//使用正则表达式定义：

val regex = "(\\d+)/(\\d+)/(\\d+)".r

val regex(year, month, day) = "2010/1/13"

// year: String = 2010

// month: String = 1

// day: String = 13

// lazy, val, def的区别
// val 定义时就一次求值完成，保持不变

val f11 = 10+20 // 30 

//lazy 定义时不求值，第一次使用时完成求值，保持不变

lazy f12= 10+20 // <lazy>
 

//def 定义时不求值，每次使用时都重新求值 (无参，缺省返回值类型的函数定义）

def f = 10+20 // 30

def t = System. currentTimeMillis // 每次不一样

val f1 = System.currentTimeMillis // 马上求值, 之后保持不变

lazy val f2 = System.currentTimeMillis // 定义时不求值 第一次使用时求值，注意：6545 > 4297  之后保持不变

def f3 = System.currentTimeMillis
 



 
9.3.     正则表达式regex
例子1：完全匹配
//将字符串转为scala.util.matching.Regex
val pattern = "^[a-z0-9._%\\-+]+@(?:[a-z0-9\\-]+\\.)+[a-z]{2,4}$"
val emailRegex = pattern.r // 或者 new scala.util.matching.Regex(pattern)
//emailRegex.pattern=>java.util.regex.Pattern 使用Java的Pattern
emailRegex.pattern.matcher("tt@16.cn").matches // true

 

例子2：查找匹配部分
val p = "[0-9]+".r  
p.findAllIn("2 ad 12ab ab21 23").toList // List(2, 12, 21, 23)
p.findFirstMatchIn("abc123xyz").get // scala.util.matching.Regex.Match = 123

 

更多例子如下：

定义：
val r1 = "(\\d+) lines".r  // syntactic sugar val r2 = """(\d+) lines""".r  // using triple-quotes to preserve backslashes
或者
import scala.util.matching.Regex val r3 = new Regex("(\\d+) lines")  // standard val r4 = new Regex("""(\d+) lines""", "lines") // with named groups
 

search和replace（java.lang.String的方法）：

"99 lines" matches "(\\d+) lines" // true
"99 Lines" matches "(\\d+) lines" // false
"99 lines" replace ("99", "98") // "98 lines"
"99 lines lines" replaceAll ("l", "L") // 99 Lines Lines
 

search（regex的方法）：

"\\d+".r findFirstIn "99 lines" // Some(99)
"\\w+".r findAllIn "99 lines" // iterator(长度为2)
"\\s+".r findPrefixOf "99 lines" // None
"\\s+".r findPrefixOf "  99 lines" // Some(  )
val r4 = new Regex("""(\d+) lines""", "g1") // with named groups
r4 findFirstMatchIn "99 lines-a" // Some(99 lines)
r4 findPrefixMatchOf "99 lines-a" // Some(99 lines)
val b = (r4 findFirstMatchIn "99 lines").get.group("g1") // "99"
 

match（regex的方法）：

val r1 = "(\\d+) lines".r
val r4 = new Regex("""(\d+) lines""", "g1")
val Some(b) = r4 findPrefixOf "99 lines" // "99 lines" for {   line <- """|99 lines-a              |99 lines              |pass              |98 lines-c""".stripMargin.lines } line match {   case r1(n) => println("Has " + n + " Lines.") // "Has 99 Lines."
  case _ => }
 
for (matched <- "(\\w+)".r findAllIn "99 lines" matchData)
  println("Matched from " + matched.start + " to " + matched.end)
输出：

Matched from 0 to 2
Matched from 3 to 8

 
replace（regex的方法）：

val r2 = """(\d+) lines""".r  // using triple-quotes to preserve backslashes
r2 replaceFirstIn ("99 lines-a", "aaa") // "aaa-a"
r2 replaceAllIn ("99 lines-a, 98 lines-b", "bbb") // "bbb-a, bbb-b"

 

其他：使用正则表达式定义变量
val regex = "(\\d+)/(\\d+)/(\\d+)".r
val regex(year, month, day) = "2010/1/13"
// year: String = 2010
// month: String = 1
// day: String = 13

 