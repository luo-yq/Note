--[[
语法
optional_function_scope function function_name( argument1, argument2, argument3..., argumentn)
	function_body
	return result_params_comma_separated
end
可选的函数作用域：你可以使用关键字 local 限制函数的作用域，你也可以忽略此部分而使用默认值。函数作用域默认是全局。
函数名：函数的真正名称。函数名与函数的参数列表一起被称为函数签名。
参数：一个参数就一个占位符一样。函数调用时，把值传递给参数。这个值被称之为实际参数或直参数。参数列表指参数的类型，顺序与数量。参数是可选的，一个函数可以没有参数。
函数体：函数体是代码语句集合，定义了函数的功能。
返回：在 Lua 中，可以使用 return 关键字同时返回多返回值，每个返回值之间使用逗号分隔。


函数参数
如果函数需要用到参数，则它必须声明接受参数值的变量。这些被声明的变量被称为函数的形式参数或简称形参。

函数的形参与函数中其它局部变量一样，在函数的入口处被创建，函数结束时被销毁。

--]]

function max(num1, num2)

   if (num1 > num2) then
      result = num1;
   else
      result = num2;
   end

   return result; 
end



-- 调用函数
print("The maximum of the two numbers is ",max(10,4))
print("The maximum of the two numbers is ",max(5,6))


--[[
赋值与传递函数
在 Lua 语言中，我们可以将函数赋值给一个变量，也可以将函数作为参数传递给另外一个函数。下面是赋值传递函数的一个例子：
--]]
myprint = function(param)
   print("This is my print function -   ##",param,"##")
end

function add(num1,num2,functionPrint)
   result = num1 + num2
   functionPrint(result)
end
myprint(10)
add(2,5,myprint)


--[[变参函数
在 Lua 语言中，使用 ... 作为参数可以创建参数个数可变的函数，即变参函数。我们可以使用下面的这个例子来理解变参函数的概念。下面的这个例子中函数返回输入参数的平均值：
--]]
function average(...)
   result = 0
   local arg={...}
   for i,v in ipairs(arg) do
      result = result + v
   end
   return result/#arg
end

print("The average is",average(10,5,3,4,5,6))





