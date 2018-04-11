
println(1::2::4::3::Nil)

println(1)
abstract class animal(val m:Int){
	val x:Int 
	var a:Int
	def init{a=20}
}
class Person(override val m:Int) extends animal(m:Int){
	val x:Int =1
	var a:Int=2
	override def init{a=20}
	def add{println(x+a)}
	def add(m:Int){println(x+a+m);Person.now}

	def this(){
		this(1)
	}
}
object Person {
	var s=10
	def now{
		println("now")
	}
	def `return`{
		println("return")
	}
}
val person=new Person(1)
person.add
println(person.x)
person.a=100
println(person.a)
(new Person).add(30)
var s=20
val a:Int=20
println(Person.s)
Person.now
var ss:Array[String]=new Array(2)
ss(0)="first"
ss(1)="second"
println(ss(0))
var ss2= Array(2,3)
println(ss2(0))
var s1,s2=0xa2
println(s1)
println(s2)
Person.`return`
if(1>1)println("true")else println("false")

for(i <- 1 until 4) println(i)

var k=5%3 match {
	case 1 => "5%3=1"
	case 2 => "5%3=2"
}

println(k)

val yz=(1,2,"third")
println(yz._1)

