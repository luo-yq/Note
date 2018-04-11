
--[[
标准库

错误处理库，包括错误处理函数，比如 assert，error等，详见错误处理。
内存管理，包括与垃圾回收相关的自动内存管理的内容，详见垃圾回收。
dofile([filename])，打开指定文件，并将文件内容作为代码执行。如果没有传入参数，则函数执行标准输入的内容。错误会传递至函数调用者。
_G，全局变量，它存储全局的环境。Lua 本身不使用此变量。
getfenv([f])，返回指定函数使用的当前环境（作用域），可以通过函数名或栈深度值指定函数。 1 表示调用 getfenv 的函数。如果传入的参数不是函数或者 f 为 0,则返回全局环境。f 的默认值为 1。
getmetatable(object)：如果对象没有元表，则返回 nil。如果对象的元表有 __metable 域，则返回该值；否则返回对象的元表。
ipairs(t)，用于遍历表，此函数返回三个值：next 函数，表 t, 以及 0。
load(func[,chunkname])，使用函数 func 加载一个块（代码块），每次调用func必须返回与先前的结果连接后的字符串
loadfile([filename]),与 load 函数相似，此函数从文件中或标准输入（没指定文件名时）读入代码块。
loadstring(string,[,chunkname])，与 load 类似，从指定字符串中获得代码块。
next(table[,index])，此函数用于遍历表结构。第一参数为表，第二个参数是一个索引值。返回值为指定索引的下一个索引与相关的值。
pairs(t),用于遍历表，此函数返回三个值：next 函数，表 t, 以及 nil。
print(...)，打印输出传入参数。
rawequal(v1,v2)，判断 v1 与 v2 是否相等，不会调用任何元方法。返回布尔值。
rawget(table,index)，返回 table[index]，不会调用元方法。table 必须是表，索引可以是任何值。
rawset(table,index,value)，等价于 table[index] = value，但是不会调用元方法。函数返回表。
elect(index,...)，如果 index 为数字n,那么 select 返回它的第 n 个可变实参，否则只能为字符串 "#",这样select会返回变长参数的总数。
setfenv(f,table)，设置指定函数的作用域。可以通过函数名或栈深度值指定函数。 1 表示调用 setfenv 的函数。返回值为指定函数。特别地，如果 f 为 0，则改变当前线程的运行环境，这时候函数无返回值。
setmetatable(table,metatable)，设置指定表的元表（不能从 Lua 中改变其它类型的元表，其它类型的元表只能从 C 语言中修改）。如果 metatable 为 nil，则删除表的元表；如果原来的元表有 __metatable 域，则出错。函数返回 table。
tonumber(e[,base])，将参数转换为数值。如果参数本身已经是数值或者是可以转换为数值的字符串，则 tonumber 返回数值，否则返回 nil。
tostring(e)，将传递的实参以合理的格式转换为字符串。精确控制字符串的转换可以使用 string.format 函数。
type(v)，以字符串的形式返回输入参数的类型。该函数的返回值可以取字符串：nil，number，string，boolean，table，function，thread，userdata。
unpack(list[ ,i[,j] ])，从指定的表中返回元素。
_VERSION，存储当前解释器版本信息的全局变量。该变量当前存储的内容为：Lua 5.1。（译注：与解释器版本有关）
协程库，包括协程相关的函数，详见协程

]]


--Lua 语言中所有布尔真和非 nil 值都当作真；把所有的布尔假和 nil 作为假。请注意，Lua 中的零会被当作真



--[[
数据类型
Lua 是动态类型编程语言，变量没有类型，只有值才有类型。值可以存储在变量中，作为参数传递或者作为返回值。

值类型	描述
nil	用于区分值是否有数据，nil 表示没有数据。
boolean	布尔值，有真假两个值，一般用于条件检查。
number	数值，表示实数(双精度浮点数)。
string	字符串。
function	函数，表示由 C 或者 Lua 写的方法。
userdata	表示任意 C 数据。
thread	线程，表示独立执行的线程，它被用来实现协程。
table	表，表示一般的数组，符号表，集合，记录，图，树等等，它还可以实现关联数组。它可以存储除了 nil 外的任何值。

type 函数 Lua 中有一个 type 函数，它可以让我们知道变量的类型。下面的代码中给出了一些例子：　　
--]]
print(type("What is my type"))   --> string
t=10
print(type(t))                   --> number
print(type(5.8*t))               --> number
print(type(true))                --> boolean
print(type(print))               --> function
print(type(nil))                 --> nil
print(type(type(ABC)))           --> string



local d , f = 5 ,10 --声明局部变量 d，f。 
d , f = 5, 10;      --声明全局变量 d，f。 
d, f = 10           --[[声明全局变量 d，f，其中 f 的值是 nil--]]
--[[
算术运算操作符
+  两个操作数相加  A + B = 30
-  从所述的第一个减去第二操作数 A - B = -10
*  两个操作数相乘  A * B = 200
/  通过分子除以反分子   B / A = 2
%  模运算和整数除法后的余数   B % A = 0
^  指数运算符需要的指数  A^2 = 100
-  一元运算 - 运算符相当于取反   -A = -10

关系运算符
下面的表列出了 Lua 支持的所有关系运算符。假设 A 的值为 真（非零），B 的值为 假（零），则：
==	判断两个操作数是否相等，若相等则条件为真，否则为假。	(A == B) 为假。
~=	判断两个操作数是否相等，若不相等则条件为真，否则为假。	(A ~= B) 为真。
>	如果左操作数大于右操作数则条件为真，否则条件为假。	(A > B) 为假。
<	如果左操作数小于右操作数则条件为真，否则条件为假。	(A < B) 为真。
>=	如果左操作数大于或等于右操作数则条件为真，否则条件为假。	(A >= B) 为假。
<=	如果左操作数小于或等于右操作数则条件为真，否则条件为假。	(A <= B) 为真。

逻辑运算符
下面的表列出了 Lua 支持的所有逻辑运算符。假设 A 的值为 真（非零），B 的值为 假（零），则：
and	逻辑与运算符。如果两个操作数都非零，则条件为真。	(A and B) 为假。
or	逻辑或运算符。如果两个操作数中其中有一个非零，则条件为真。	(A or B) 为真。
not	逻辑非运算符。翻转操作数的逻辑状态。如果条件是真，则逻辑非运算符会将其变成假。	!(A and B) 为真。

其它操作符
..	连接两个字符串。	若 a 为 "Hello"，b 为 "World",则 a..b 返回 "Hello World"。
#	一元运算符，返回字符串或者表的长度。	#"Hello" 返回 5。

操作符优先级
下面的表中，从上到下优先级递减。在每个表达式中，高优先级操作数先运算。
一元运算符类	not # -	从右至左
连接运算符	..	从右至左
乘除运算符类	* / %	从左至右
加减运算符类	+ -	从左至右
关系运算符类	< > <= >= == ~=	从左至右
逻辑与运算符	and	从左至右
逻辑或运算符	or	从左至右
--]]

--[[
条件选择
--]]
if 2>1 then
	print ("2>1")
else
	print("2<1")
end

--[[
循环控制
while 循环	先检测条件，条件为真时再执行循环体，直到条件为假时结束。
	while(condition)
	do
	   statement(s)
	end

for 循环	执行一个语句序列多次，可以简化管理循环变量的代码。
	for init,max/min value, increment
	do
	   statement(s)
	end

repeat...until 循环	重复执行一组代码语句，直到 until 条件为真为止。
	repeat
	   statement(s)
	until( condition )

break 语句结束循环，并立即跳转至循环或 switch 语句后的第一条语句处开始执行。
--]]


a=10
while( a < 20 )
do
   print("value of a:", a)
   a = a+1
end

for i=10,1,-1 
do 
   print(i) 
end

--[ local variable definition --]
a = 10
--[ repeat loop execution --]
repeat
   print("value of a:", a)
   a = a + 1
until( a > 15 )


