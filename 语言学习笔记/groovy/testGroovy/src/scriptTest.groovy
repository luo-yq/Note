/**
 * Created by lyt1987 on 15/4/16.
 */
//groovy中一切皆对象，有优点也有缺点
//优点：普通数据类型可以方便的执行对象操作，
//缺点：普通数据类型的部分特有行为被抹杀：如   1+‘’ 会执行 integer的plus操作导致报错，
//初步认为在实现复杂行为上groovy可能存在诸多缺陷，在性能上应该低于java
//
//groovy 与 java 分别通过 gdk/sdk 将代码编译为 jvm 上可执行的 class 文件
//
//断言的好处在于能准确的表达代码块的意图
//
//
//
//
//
//
//
//
//
//
//
println(System.properties.'user.home')
println(System.properties.'groovy.home')
println(System.properties.'tools.jar')


def ClassType(String o){println(o+"  string");return o}
def ClassType(Integer o){println(o);return o}
def ClassType(Object o){println(o);return o}
println(ClassType(1))
println(ClassType("1"))
println(ClassType(true))




def k='1';

println(1==k)
println(k==1)

println(k>1)

println(k)

//自定义一个断言
def assertHost(candidate,regex,expected){
    candidate.eachMatch(regex){
        println("match string :"+it)
        assert it[1]==expected
    }
}

def regex=/\/\/([0-9a-zA-Z-]+(\.[0-9a-zA-Z-])*?)(:|\/)/
assertHost("http://localhost:8080/portal",regex,"localhost")



//ClassTest t = new ClassTest(4)
//println(t.getK())
//t.setK(2)
//println(t.k)

//ClassTest t2=[5]
//println(t2.getK())
//ClassTest t3=[6] as ClassTest
//println(t3.getK())
//def t4=new NoConstructer(key1: 7)
//println(t4.getKey1())

//same.ClassTest t5=new same.ClassTest();

//println(t5.getK())

//def t7=new ClassTest();

//t7.metaClass.dynaticM = {time ->
//    println(time)
//        return time
//}
//println(t7.dynaticM(7980))


(0..10).each {tmp->
    print(tmp)
}
println()
(0..<10).each {tmp->
    print(tmp)
}
println()
for(tmp in (0..10)){
    print(tmp)
}
println()
def list = ['sajfla',1,false]
list.each {tmp->
    print(tmp.toString()+"\t" + tmp.getClass())
    println()
}
def lists = ['sajfla',1,false]
lists.each {tmp->
    print(''+tmp+"\t" + tmp.getClass())
    println()
}
assert 1==1

def sumvalue=list.inject(6) { sum,tmp ->
   // println(sum)
   // println(tmp)
    sum = sum + tmp.toString()
   // println(sum)
}
assert sumvalue != 'sajfla1false'
println(sumvalue)

def list2 = [1,2,3]
sumvalue=list2.inject(2){sum,tmp->
    sum=sum+tmp

}

println(sumvalue)
println(list2.reverse())

def map = [
        key1 : 'v1',
        key2 : 2
]

println([(map.key1):1])
println('map.key1':1)

map.each {key,value->
    println(key+' : '+value)
}


println("$map.key1")
println("\$map.key1")
println('$map.key1')
println('\$map.key1')

println('''
afla
afa


''')


println list2


printf("%s\n",k)
println ''.matches(/\s*/)
println ''.matches(/\S*/)

println(getInt())

int getInt(){
    return 10
}

java.io.File f=new java.io.File("/Users/lyt1987/Desktop/tmp")

// 创建文件
if (!f.exists()) {
    f.mkdirs();
}

f=new java.io.File("/Users/lyt1987/Desktop/tmp/tmp.txt")

// 创建文件
if (!f.exists()) {
    f.createNewFile();
}

new File('/Users/lyt1987/Desktop/tmp/tmp.txt').write('''

af
af
afagasg
''')
new File('/Users/lyt1987/Desktop/tmp/tmp.txt').eachLine {println(it)}

def file=new File('/Users/lyt1987/Desktop/tmp/tmp.txt')
for(line in file){
    println(line)
}
def matcher='123ksa45'=~/[0-9]*/    //*返回数组5  +返回数组2  ?返回数组8
for(macth in matcher){
    println(macth)
}

def classtype1=new SwitchClass1()
println(classtype1)
//switch (t){
//    case SwitchClass1: println(classtype1); break
//    default: break
//}

def arr=[];
arr<<"rr"
arr<<234
println(arr)

// ？？？ what to do???
for(testC in null){
    println("$testC is null ")  //不打印
}
for(testC in new Object()){
    println("$testC is Object")  //打印一次
}
//println(testC)
def testC=""
println(testC)


def range=(1..3)
def newrange=[0,*range]
assert [0,1,2,3]==newrange
println(newrange)
def range1=[1..3]
def newrange1=[0,*range]
println(newrange)
def newrange2=[0,range]
println(newrange2)
def map1=[d:1,k:2]
def newmap1=[s:3,*:map1]
println(newmap1)


println(range.toString())
List<String> list1=this.class.methods.name
list1=["ddd",'vbsaf']

println this.class.methods.name
println(list1*.substring(0,2))

def numList=[1,2,3]
println(sum(*numList))
def sum(a,b,c){
    return a+b+c
}

class MyOperation{
    static def plus(String self,String plus2){
        return self.toInteger()+plus2.toInteger()
    }
}
println("1"+"2")
use(MyOperation){
    println("1"+"2")

}


