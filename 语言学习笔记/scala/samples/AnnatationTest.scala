

注解
注解在Java中广泛使用，我们可以对Scala类使用Java注解  
@Test (timeout = 100) def testSomeFeature(){...}  
@Entity class Credentials{  
  @Id @BeanProperty var username: String = _  
  @BeanProperty var password: String= _  
}  
除了@BeanProperty ， 都是JUnit和JPA的注解，这量个Java框架并不知道我们用的是Scala。  
我们也可以用Scala注解，这些注解会由Scala编译器或编译器插件处理。  
Java注解并不影响编译器如何将源码编译成字节码，他们仅仅是往字节码中添加数据，以便外部工具可以利用它们。  
而在Scala中，注解可以影响编译过程，比如@BeanProperty 注解可以触发getter和setter的生成。  

什么可以被注解
[java] view plain copy 在CODE上查看代码片派生到我的代码片
像Java一样，可以为 类 方法 字段 局部变量 参数添加注解  
@Entity class Credentials //类  
@Test def testSomeFeature(){} //方法  
@BeanProperty var username = _ //字段  
def doSomething (@NotNull message: String){} // 参数  
可以添加多个注解，先后次序无影响  
@BeanProperty @Id var username = _  
  
给主构造器添加注解时，需要将注解放置在构造器之前，并加上一对圆括号（如果注解不带参数的话）  
class Credentials @Inject() (var username: String, var password: String)  
  
为表达式添加注解，需要在表达式后加上冒号，然后是注解本身  
(myMap.get(key) : @unchecked) match {...}  
//我们为表达式myMap.get(key)添加了注解  
  
可以为类型参数添加注解  
class MyContainer[@specialized T]  
  
针对实际类型的注解应放置在类型名称之后，  
String @cps[Unit] // @cps带一个类型参数  

注解参数
Java注解可以有带名参数，  
@Test(timeout = 100, expected = classOf[IOException])  
  
如果参数名为value,该名称可以省略  
@Named("creds") var credentials: Credentials = _ //value参数的值为"creds"  
  
如果注解不带参数，圆括号可以略去  
@Entity class Credentials  
  
大多数注解参数都有缺省值  
@Test def test SomeFeature(){...}  
等同于  
@Test(timeout = 0, expected = classOf[org.junit.Test.None]) def test SomeFeature(){...}  
  
Java注解的参数类型，只能是  
数值型的字面量  
字符串  
类字面量  
Java枚举  
其他注解  
上述类型的数组（但不能是数组的数组）  
  
Scala注解的参数可以是任何类型  

注解实现
注解必须扩展Annotation特质  
class unchecked extends annotation.Annotation  
注解类可以选择扩展StatcAnnotation 或 ClassfileAnnotation特质  
前者在编译单元中可件，它将放置Scala特有的元数据到类文件中  
后者的本意是在类文件中生成Java注解元数据，不过Scala2.9并未支持该特性。  
Scala的构造器注解仅会被应用到参数自身，字段注解只能应用到字段  
class Credentials(@NotNull @BeanProperty var username: String)  
默认情况下，这里注解只作用到参数  
那些由于Scala特性产生的，字段，取值器/改值器，getter/setter都不会影响。  
  
元注解（注解的注解）  
@param @field @getter @setter @beanGetter @beanSetter使得注解被附在别处  
@getter @setter @beanGetter @beanSetter  
class deprecated(message:String = "", since:String= "") extends annotation.StaticAnnotation //当前注解会被自动应用到相应的目标上  
  
也可以临时根据需要应用这些元注解：  
@Entity class Credentials {  
  @(Id @beanGetter) @BeanProperty var id = 0  
}//@Id 注解被应用到Java的getId方法，这个JPA要求的方法。  

Java修饰符
一些不常用的Java特性，Scala使用注解，而不是修饰符  
@volatile注解  
@volatile var done = false // 在JVM中将成为volatile的字段  
可以被多个线程同时更新。  
  
@transient注解  
@transient var recentLookups = new HashMap[String,String] // 在JVM中为transient字段，非序列化的一部分，常用语临时保存的缓存数据，或易于重新计算的数据。  
  
@strictfp注解， 对应Java的strictfp修饰符  
@strictfp def calculate(x:Double) = ...  
该方法使用IEEE的double值来进行浮点计算  
而不是使用80位扩展精度（Intel处理器默认使用的实现）  
计算结果会更慢，但代码可移植性更高。  
  
@native注解，来标记那些在C C++中实现的方法，对应 Java的 native修饰符  
@native def win32Regkeys(root: Int, path: String):Array[String]  

标记接口
[java] view plain copy 在CODE上查看代码片派生到我的代码片
Scala用注解@cloneable和@remote ，代替Cloneable和java.rmi.Remote标记接口来标记可被克隆的和远程的对象  
@cloneable class Employee  
  
序列化  
@SerialVersioinUID(43534534L) class Employee extends Person with Serializable  

受检异常
@throws  
由于Scala不检查受检异常，如果从Java代码中调用Scala方法，其签名应包含可能被抛出的受检异常  
class Book{  
  @throws (classOf[IOException]) def read(filename: String) {...}  
}  
转换到Java的签名为  
viod read(String filename) throws IOException  
  
如果没有@throws 注解，以下在Java中运行的代码会拒绝捕获该异常。  
try{ // java code  
  book.read("...")  
}catch(IOException ex){  
}  

变长参数
@varargs注解，从Java调用scala的带有变长参数的方法。  
def process(args: String*)  
Scala编译器把变长参数翻译成序列  
def process(args: Seq[String]) // 这样的签名在Java中使用起来比较麻烦  
  
@varargs def process(args: String*)  
编译器生成的Java代码  
void process(String ... args) // java桥接方法  
JavaBeans
[java] view plain copy 在CODE上查看代码片派生到我的代码片
class Person{  
  @BeanProperty var name : String = _ // 会产生setter和getter  
}  
@BooleanBeanProperty 生成带有is前缀的getter方法，用于Boolean.  

用于优化的注解

尾递归
递归调用有时被转化为虚幻，这样能节省栈控件  
obejct Utill{  
  def sum(xs: Seq[Int]:BigInt =   
    if ( xs.isEmpty) 0 else xs.head + sum(xs.tail) // 该方法无法被优化，因为最后一步步是递归调用而是加法  
}  
修改后  
def sum2(xs: Seq[Int], partial:BigInt) : BigInt =   
  if(xs.isEmpty) partial else sum2( xs.tail, xs.head + partial)   
// 由于计算过程的最后一步是地柜调用同一个方法，因此可以被变换成调回到方法顶部的循环  
//Scala编译器会自动将这个方法应用“尾递归”优化  
  
sum( 1 to 1000000) // 栈溢出，压了太多方法栈  
sum2( 1 to 1000000, 0) // 返回序列之和，方法被转化为一个循环  
  
虽然编译器会常识使用尾递归优化，但某些不太明显的原因会造成它无法优化。  
如果加@tailrec，当无法优化时会自动报错  
class Util{  
  @tailrec def sum2(xs :Seq[Int], partial:BigInt): BigInt = ....  
}  
程序编译会失败，提示错误：“could not optimize @tailrec annotated method sum2: it is neither private nor final so can be overriden”  
在这种情况下，可以将方法挪到对象中，或将它声明为private或final  

跳转表生成与内联
switch语句通常可以被编译成跳转表，这比一些列if/else表达式更加高效。  
@switch注解让Scala的match语句是不是真的被编译成了跳转表  
(n : @swith) match {  
  case 0 => "Zero"  
  case 1 => "One"  
  case _ => "?"  
}  
  
方法内联，将方法调用语句替换为被调用的方法体。  
@inline，用来建议编译器做内联  
@noinline，告诉编译器不要内联  

可省略方法
@elidable(500)  def dump(prop: Map[String, String]) { ... }  
如果用如下命令编译  
scalac -Xelide-below 800 myprop.scala //则上述方法不会别生成  
elidable对象定义了很多常量  
MAXIMUM 或 OFF = Int.MaxValue  
ASSERTION = 2000  
SEVERE = 1000  
WARNING = 900  
INFO = 800  
CONFIG = 700  
FINE = 500  
FINER = 400  
FINEST = 300  
MINIMUM 或 ALL = Int.MinValue  
可以在注解中使用常量  
import scala.annotation.elidable._  
@elidable(FINE) def ...  
scalac -Xelide-below INFO myprog.scala  
注：如果不指定-Xelide-below标志，低于1000的方法会被省略。  
-Xelide-below OFF 省略所有方法  
-Xelide-below  ALL 什么都不要省略  
  
Prede模块定义了可被忽略的assert方法  
禁用断言 -Xelide-below 2001 或 -Xelide-below MAXIMUM   
  
对被省略的方法调用，编译器会替换成Unit对象，如果使用了被省略方法的返回值，抛出ClassCastException  
最好只对那么没有返回值的方法使用@elidable注解。  

基本类型的特殊化
打包和解包基本类型的值是不高效的，特别是在泛型代码中  
def different[T](x:T, y:T) = x!=y  
如果调用 different(3, 4)，每个整数值都被包装成一个java.lang.Integer。  
def different[T](x:Int, y:Int) = x!=y // 不存在包装过程  
再提供的七个重载方法，分别对应其他基本类型  
  
@specialized注解，，让编译器自动生成这些方法  
def different[@specialized T](x:Int, y:Int) = x!=y  
可以将特殊化限定在所有可选类型的子集：  
def different[@specialized(Long, Double) T](x:Int, y:Int) = x!=y  
在注解构造器中，可以指定如下类型的任意子集：  
Unit Boolean Byte Short Char Int Long Float Double  

用于错误和警告的注解
@deprecated， 标注方法弃用  
@deprecated(message= "..." , since = "...")  
def factorial(n : Int) : Int = ...  
  
@deprecatedName，标记弃用的参数名  
def draw(@deprecatedName（'sz) size :int) //这里的构造器阐述是一个单引号开头的名称  
当调用draw(sz=12)，会得到一个该名称已过时的警告  
  
@implicitNodeFound，用于某个隐式参数不存在的时候生成有意义的错误提示  
  
@unchecked ， 用于在匹配不完整时，取消警告信息  
(lst : @unchecked) match{  
 case head :: tail => ...  
}  
  
@uncheckedVariance ， 取消与型变相关的错误提示  
java.util.Comparator按理应该是 型变的  
如果Student是Person的子类型，那么需要Comparator[Student]时，可以用Comparator[Person]，  
但Java泛型不支持型变  
通过@uncheckedVariance来解决  
trait Comparator [-T] extends java.lang.Comparator[T @uncheckedVariance]  



