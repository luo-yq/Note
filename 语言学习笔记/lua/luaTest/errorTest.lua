
--[[
pcall 与 xpcall　　
在 Lua 中，为了避免使用抛出错误和处理错误，我们需要用到 pcall 和 xpcall 函数来处理异常。
--]]
--[[
使用 pcall(f,arg1,...) 函数可以使用保护模式调用一个函数。如果函数 f 中发生了错误， 它并不会抛出一个错误，而是返回错误的状态。使用的 pcall 函数的方法如下所示：
--]]
function myfunction ()
   n = n/nil
end

print(pcall(myfunction))

if pcall(myfunction) then
   print("Success")
else
    print("Failure")
end


--[[
抛出异常
error(message [,level]) 函数会结束调用自己的函数，并将 message 作为错误信息返回调用者
级别(level) 参数指定错误发生的位置。
若为 1(默认值)，返回的错误的位置是 error 函数被调用的位置。
若为 2, 返回的错误位置为调用 error 函数的函数被调用的位置，依次类推。
若为 0 就不再需要在消息前增加额外的位置信息了。
--]]
function throwError0 ()
	error("my error",0) 
end
function throwError1 ()
	error("my error") 
end
function throwError2 ()
	error("my error",2) 
end
function callThrowError2()
	throwError2()
end
print(pcall(throwError0))
print(pcall(throwError1))
print(pcall(callThrowError2))


--[[
xpcall(f,err) 函数调用函数 f 同时为其设置了错误处理方法 err，并返回调用函数的状态。任何发生在函数 f 中的错误都不会传播，而是由 xpcall 函数捕获错误并调用错误处理函数 err，传入的参数即是错误对象本身。xpcall 的使用示例如下：
--]]

function myerrorhandler( err )
   print( "ERROR:", err )
end

status = xpcall( myfunction, myerrorhandler )
print( status)


--[[
assert抛出异常
--]]
local function add(a,b)
   assert(type(a) == "number", "a is not a number")
   assert(type(b) == "number", "b is not a number")
   return a+b
end
add(10)

