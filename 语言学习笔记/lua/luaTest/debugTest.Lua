
--[[
Lua 调试库包括的函数如下表所示:
1	debug():进入交互式调试模式，在此模式下用户可以用其它函数查看变量的值。
2	getfenv(object):返回对象的环境。
3	gethook(optional thread)：返回线程当前的钩子设置，总共三个值：当前钩子函数、当前的钩子掩码与当前的钩子计数。
4	getinfo(optional thread,function or stack leve,optional flag)：
返回保存函数信息的一个表。你可以直接指定函数，或者你也可以通过一个值指定函数，该值为函数在当前线程的函数调用栈的层次。其中，0 表示当前函数（getinfo 本身）；层次 1 表示调用 getinfo 的函数，依次类推。如果数值大于活跃函数的总量，getinfo 则返回 nil。
5	getlocal(optional thread,stack level,local index)：
此函数返回在 level 层次的函数中指定索引位置处的局部变量和对应的值。如果指定的索引处不存在局部变量，则返回 nil。当 level 超出范围时，则抛出错误。
6	getmetatable(value)：返回指定对象的元表，如果不存在则返回 nil。
7	getregistry()：返回寄存器表。寄存器表是一个预定义的用于 C 代码存储 Lua 值的表。
8	getupvalue(func function,upvalue index)：
根据指定索引返回函数 func 的 upvalue 值（译注：upvalue 值与函数局部变量的区别在于，即使函数并非活跃状态也可能有 upvalue 值，而非活跃函数则不存在局部变量，所以其第一个参数不是栈的层次而是函数）。如果不存在，则返回 nil。
9	setfenv(function or thread or userdata,environment table)：将指定的对象的环境设置为 table,即改变对象的作用域。
10	sethook(optional thread,hook function,hook mask string with "c" and/or "r" and/or "l",optional instruction count)：
把指定函数设置为钩子。字符串掩码和计数值表示钩子被调用的时机。这里，c 表示每次调用函数时都会执行钩子；r 表示每次从函数中返回时都调用钩子；l 表示每进入新的一行调用钩子。
11	setlocal(optional thread,stack level,local index,value):
在指定的栈深度的函数中，为 index 指定的局部变量赋予值。如果局部变量不存在，则返回 nil。若 level 超出范围则抛出错误；否则返回局部变量的名称。
12	setmetatable(value,metatable):为指定的对象设置元表，元表可以为 nil。
13	setupvalue(function,upvalue index,value):为指定函数中索引指定的 upvalue 变量赋值。如果 upvalue 不存在，则返回 nil。否则返回此 upvalue 的名称。
14	traceback(optional thread,optional meesage string,opitona level argument)：用 traceback 构建扩展错误消息。
--]]

function myfunction ()
	print(debug.traceback("Stack trace"))
	print(debug.getinfo(1))
	print("Stack trace end")
    return 10
end
myfunction ()
print(debug.getinfo(1))
 
function newCounter ()
  local n = 0
  local k = 0
  return function ()
    k = n
    n = n + 1
    return n
    end
end

counter = newCounter ()
print(counter())
print(counter())

local i = 1

repeat
  name, val = debug.getupvalue(counter, i)
  if name then
    print ("index", i, name, "=", val)
    if(name == "n") then
        debug.setupvalue (counter,2,10)
    end
    i = i + 1
  end -- if
until not name

print(counter())