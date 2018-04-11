/**
*List: 
*定义list：def list = [] 
*list = [1,2,3,4,5] 
*
*list操作： 
**/
def list = [1,2,3,4,5] 
list[1]        //Result: 2 
list[-2]       //Result: 4 
list[1..3]     //Result: [2, 3, 4] 
list[1..<3]    //Result: [2, 3] 
list + [6,7]   //Result: [1, 2, 3, 4, 5, 6, 7] 
list - [4,5,6] //Result: [1, 2, 3] 
list << 6      //Result: [1, 2, 3, 4, 5, 6] 
list << [6,7]  //Result: [1, 2, 3, 4, 5, 6, [6, 7]] 

//list方法： 
[2,5].add(7)               //Result: true; list = [2, 5, 7] 
[2,5].add(1,9)             //list = [2, 7, 5] 
[2,5].add([7,9])           //Result: [2, 5, [7, 9]] 
[2, 5, [7, 9]].flatten()   //Result: [2, 5, 7, 9]；克隆并解开下层list 
[2,5].get(1)               //Result: 5 
[2,5].size()               //Result: 2 
[2,5].isEmpty()            //Result: false 
[2,5].getAt(1)             //Result: 5 
[2,5,7].getAt(1..2)        //Result: [5, 7] 
[2,5,7].getAt(-1)          //Result: 7；get()不支持负数参数，getAt()支持 
[2,5,7].getAt([1,2])       //Result: [5, 7] 
[2,5,7].intersect([5,9,2]) //Result: [5, 2]；交集 
[2,5,7].pop()              //Result: 7 
[2,5,7].plus([3,6])        //Result: [2, 5, 7, 3, 6] 
[2,5,7,2].minus(2)         //Result: [5, 7] 
[2,5,7].remove(1)          //Result: 5; list = [2, 7] 
[2,7,5].reverse()          //Result: [5, 7, 2] 
[2,7,5].sort()             //Result: [2, 5, 7] 
/**
*Map: 
*定义Map：def map = [:] 
*map = ['name':'Bruce', 'age':27] 
*
*键被解释成字符串：
**/ 
def x = 3 
def y = 5 
def map = [x:y, y:x] //Result: ["x":5, "y":3] 

//如果要把值作为键，像下面这样： 
def city = 'shanghai' 
map."${city}" = 'china' 
map.shanghai //Result: "china" 

//map操作： 
def map2 = [3:56, 'name':'Bruce'] 
def a = 'name' 
map2.name    //Result: "Bruce" 
map2['name'] //Result: "Bruce" 
map2[a]      //Result: "Bruce" 
map2[3]      //Result: 56 
//以下访问是错误的，会抛出异常 
//map2[name] 
//map2.3 

//map方法： 
def map3 = ['name':'Bruce', 'age':27] 
map3.containsKey('name')   //Result: true 
map3.get('name')           //Result: "Bruce" 
map3.get('weight', '60kg') //Result: "60kg"；会把key：value加进去 
map3.getAt('age')          //Result: 27 
map3.keySet()              //Result: [name, age, weight] 
map3.put('height', '175')  //Result: ["name":"Bruce", "age":27, "weight":"60kg", "height":"175"] 
map3.values().asList()     //Result: ["Bruce", 27, "60kg", "175"] 
map3.size()                //Result: 4 

/**下列方法可以应用于范围、List和Map(inject和reverseEach方法只适合List和范围) 
each             void each(Closure clos)迭代集合中每个元素。 
find             List find(Closure clos)返回集合中第一个符合条件的元素。 
findAll          List findAll(Closure clos)返回集合中所有符合条件的元素。  
collect          List collect(Closure clos)返回计算后的列表。 
collect          List collect(Collection col, Closure clos)返回计算后的列表，同时把返回值保存到col集合里。 
any              boolean any(Closure clos)集合中有一个符合条件即返回true，否则返回false。 
every            boolean every(Closure clos)集合中所有都符合条件即返回true，否则返回false。 
findIndexOf      int findIndexOf(Closure clos)返回第一个符合条件元素在集合中的索引值(从0开始计算)。 
findLastIndexOf  int findLastIndexOf(Closure clos)返回最后一个符合条件元素在集合中的索引值(从0开始计算)。 
inject           Object inject(Object value, Closure clos)返回调用列表和参数的计算值。 
**/
[1,2,3,4].inject(5) {x2,y2-> 
    x2 + y2 
} 
//Result: 15 
//reverseEach      void reverseEach(Closure clos)反响迭代集合中每个元素。 
[1,2,3,4].reverseEach {x3-> 
    print x3 + '-' 
} 
//4-3-2-1- 
//sort             List sort(Closure clos)按照闭包的返回条件排序。