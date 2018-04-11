--[[
1. 数组索引从1开始
2. 访问数组中一个不存在的索引时，会得到 nil 值。 
--]]

--[[ 一维数组，索引默认从1开始，但可以设置0索引和负索引--]]
array = {"Lua", "Tutorial"}
print("访问数组中一个不存在的索引时，会得到 nil 值 : array[0] is ",array[0])
print("数组索引默认从1开始 : array index start from 1")
array = {}
for i= -2, 2 do
   array[i] = i *2
end
print(string.format("数组索引可以设置负值或0索引 : array[-1]=%s, array[0]=%s ",array[-1],array[0]))
--Lua 提供的默认迭代器函数 ipairs
print("Lua 提供的默认迭代器函数 ipairs： for key,value in iterator")
for key,value in ipairs(array) 
do
   print(key, value)
end



--自定义迭代器
--自定义无状态迭代器 ，每次调用迭代器函数时，函数基于传入函数的第二个变量访问集合的下一个元素。
function square(iteratorMaxCount,currentNumber)
   if currentNumber<iteratorMaxCount
   then
      currentNumber = currentNumber+1
   return currentNumber, currentNumber*currentNumber
   end
end

for i,n in square,3,0
do
   print(i,n)
end
--进一步修改
function squares(iteratorMaxCount)
   return square,iteratorMaxCount,0
end  

for i,n in squares(3)
do 
    print(i,n)
end


--有状态迭代器， 
--使用闭包来存储当前元素的状态。闭包通过函数调用得到变量的值。为了创建一个新的闭包，我们需创建两个函数，包括闭包函数本身和一个工厂函数，其中工厂函数用于创建闭包。
array = {"Lua", "Tutorial"}
function elementIterator (collection)
   local index = 0
   local count = #collection
   -- 返回闭包函数
   return function ()
      index = index + 1
      if index <= count
      then
         -- 返回迭代器的当前元素
         return collection[index]
      end
   end
end

for element in elementIterator(array)
do
   print(element)
end