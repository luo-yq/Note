-- 声明空表
mytable = {}
print("Type of mytable is ",type(mytable))

mytable[1]= "Lua"
mytable["wow"] = "Tutorial"
print("mytable Element at index 1 is ", mytable[1])
print("mytable Element at index wow is ", mytable["wow"])

-- alternatetable 与 mytable 引用相同的表
alternatetable = mytable

print("alternatetable Element at index 1 is ", alternatetable[1])
print("mytable Element at index wow is ", alternatetable["wow"])

alternatetable["wow"] = "I changed it"

print("mytable Element at index wow is ", mytable["wow"])


-- 只是变量被释放，表本身没有被释放
mytable = nil
print("mytable is ", mytable)


-- mytable 仍然可以访问
print("alternatetable Element at index wow is ", alternatetable["wow"])

-- 只有所有的引用都设为nil后，表才被回收
alternatetable = nil
print("alternatetable is ", alternatetable)


--1	table.concat(table[, sep [, i[,j]]]): 根据指定的参数合并表中的字符串。具体用法参考下面的示例。
--2	table.insert(table,[pos,]value):在表中指定位置插入一个值。
--3	table.maxn(table)：返回表中最大的数值索引。
--4	table.remove(table[,pos]):从表中移出指定的值。
--5	table.sort(table[,comp]):根据指定的（可选）比较方法对表进行排序操作。


--表连接操作
fruits = {"banana","orange","apple"}
-- 返回表中字符串连接后的结果
print("Concatenated string ",table.concat(fruits))

--用字符串连接
print("Concatenated string ",table.concat(fruits,", "))

--基于索引连接 fruits 
print("Concatenated string ",table.concat(fruits,", ", 2,3))



--插入与移出操作
fruits = {"banana","orange","apple"}

-- 在 fruits 的末尾插入一种水果
table.insert(fruits,"mango")
print("Fruit at index 4 is ",fruits[4])

-- 在索引 2 的位置插入一种水果
table.insert(fruits,2,"grapes")
print("Fruit at index 2 is ",fruits[2])

print(fruits[1])
--table.maxn总报错
--print("The maximum elements in table is",table.maxn(fruits))

print("The last element is",fruits[5])
table.remove(fruits)
print("The previous last element is",fruits[5])

--表排序操作
fruits = {"banana","orange","apple","grapes"}
for k,v in ipairs(fruits) do
print(k,v)
end
table.sort(fruits)
print("sorted table")
for k,v in ipairs(fruits) do
	print(k,v)
end 





--[[
元表 MetaTable  包含表的操作方法的定义

setmetatable(table,metatable):此方法用于为一个表设置元表。
etmetatable(table)：此方法用于获取表的元表对象。
首先，让我们看一下如何将一个表设置为另一个表的元表。示例如下：

mytable = {}
mymetatable = {}
setmetatable(mytable,mymetatable)
上面的代码可以简写成如下的一行代码：

mytable = setmetatable({},{})
__index 改变索引的行为
rawset 函数设置值时不会使用元表中的 newindex 元方法。同样的，Lua 中也存的一个 rawget 方法，该方法访问表中键值时也不会调用 index 的元方法。

使用 + 操作符完成两个表组合

__add	改变加法操作符的行为。
__sub	改变减法操作符的行为。
__mul	改变乘法操作符的行为。
__div	改变除法操作符的行为。
__mod	改变模除操作符的行为。
__unm	改变一元减操作符的行为。
__concat	改变连接操作符的行为。
__eq	改变等于操作符的行为。
__lt	改变小于操作符的行为。
__le	改变小于等于操作符的行为。
__call
使用 __call 可以使表具有像函数一样可调用的特性。下面的例子中涉及两个表，主表 mytable 和 传入的实参表结构 newtable，程序完成两个表中值的求和。




--]]


--下面的例子中，我们实现了在表中查找键不存在时转而在元表中查找该键的功能：
mytable = setmetatable({key1 = "value1"}, {
  __index = function(mytable, key)
    if key == "key2" then
      return "metatablevalue"
    else
      return mytable[key]
    end
  end
})

print(mytable.key1,mytable.key2)




mymetatable = {}
mytable = setmetatable({key1 = "value1"}, { __newindex = mymetatable })

print(mytable.key1)

mytable.newkey = "new value 2"
print(mytable.newkey,mymetatable.newkey)

mytable.key1 = "new  value 1"
print(mytable.key1,mymetatable.newkey1)

mytable = setmetatable({key1 = "value1"}, {
  __newindex = function(mytable, key, value)
        rawset(mytable, key, "\""..value.."\"")

  end
})

mytable.key1 = "new value"
mytable.key2 = 4

print(mytable.key1,mytable.key2)





mytable = setmetatable({ 1, 2, 3 }, {
  __add = function(mytable, newtable)
    for i = 1, 3 do
    --for i = 1, table.maxn(newtable) do
      --table.insert(mytable, table.maxn(mytable)+1,newtable[i])
      table.insert(mytable, i+1,newtable[i])
    end
    return mytable
  end
})

secondtable = {4,5,6}

mytable = mytable + secondtable
for k,v in ipairs(mytable) do
print(k,v)
end




mytable = setmetatable({10}, {
  __call = function(mytable, newtable)
    sum = 0
    --for i = 1, table.maxn(mytable) do
    for i = 1, 1 do
        sum = sum + mytable[i]
    end
    --for i = 1, table.maxn(newtable) do
    for i = 1, 1 do
        sum = sum + newtable[i]
    end
    return sum
  end
})
newtable = {10,20,30}
print(mytable(newtable))




--改变print  只需修改 __tostring函数
mytable = setmetatable({ 10, 20, 30 }, {
  __tostring = function(mytable)
    sum = 0
    for k, v in pairs(mytable) do
        sum = sum + v
    end
    return "The sum of values in the table is " .. sum
  end
})
print(mytable)

