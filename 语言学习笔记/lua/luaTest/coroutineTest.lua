--协程有着与线程类似的特性，但是协程不能并发，任意时刻只会有一个协程执行，而线程允许并发的存在。
--[[
1	coroutine.create(f):用函数 f 创建一个协程，返回 thread 类型对象。
2	coroutine.resume(co[,val1,...]): 传入参数（可选），重新执行协程 co。此函数返回执行状态，也可以返回其它值。
3	coroutine.running():返回正在运行的协程，如果在主线程中调用此函数则返回 nil。
4	coroutine.status(co):返回指定协程的状态，状态值允许为：正在运行(running)，正常(normal)，挂起(suspended)，结束(dead)。
5	coroutine.wrap(f):与前面 coroutine.create 一样，coroutine.wrap 函数也创建一个协程，与前者返回协程本身不同，后者返回一个函数。当调用该函数时，重新执行协程。
6	coroutine.yield(...):挂起正在执行的协程。为此函数传入的参数值作为执行协程函数 resume 的额外返回值（默认会返回协程执行状态）。
--]]


co = coroutine.create(function (value1,value2)
   local tempvar3 =10
   print("coroutine section 1", value1, value2, tempvar3)
   local tempvar1 = coroutine.yield(value1+1,value2+1)
   tempvar3 = tempvar3 + value1
   print("coroutine section 2",tempvar1 ,tempvar2, tempvar3)
   local tempvar1, tempvar2= coroutine.yield(value1+value2, value1-value2)
   tempvar3 = tempvar3 + value1
   print("coroutine section 3",tempvar1,tempvar2, tempvar3)
   return value2, "end"
end)

print("main", coroutine.resume(co, 3, 2))
print("main", coroutine.resume(co, 12,14))
print("main", coroutine.resume(co, 5, 6))
print("main", coroutine.resume(co, 10, 20))

--[[
首先，我们创建了一个协程并将其赋给变量 co。此协程允许传入两个参数。
第一次调用函数 resume 时，协程内局部变量 value1 和 value2 的值分别为 3 和 2。
为了便于理解，我们使用了局部变量 tempvar3 该变量被初始化为 10。由于变量 value1 的值为3，所以 tempvar3 在随后的协程调用过程中被先后更新为 13 和 16。
第一次调用 coroutine.yield 时，为 resume 函数返回了值 4 和 3，这两个值是由传入的参数 3，2 分别加 1 后的结果，这一点可以从 yield 语句中得到证实。除了显示指定的返回值外，resume 还收到隐式的返回值 true，该值表示协程执行的状态，有 true 和 false 两个可能取值。
上面的例子中，我们还应该关注在下一次调用 resume 时如何为协程传入参数。从例子中可以看到，coroutine.yield 函数返回后为两个变量赋值，该值即是第二次调用 resume 时传入的参数。这种参数传递的机制让可以结合前面传入的参数完成很多新的操作。
最后，协程中所有语句执行完后，后面的调用就会返回 false 状态，同时返回 "cannot resume dead coroutine"消息。
另一个协程的示例
下面这例子中的协程使用 yield 函数和 resume 函数依次返回数字 1 到 5。示例中，如果没有协程对象或对象已结束（dead），则重新创建一个新的协程对象；若协程已经存在，则执行已经存在的协程。
--]]
function getNumber()
   local function getNumberHelper()
      co = coroutine.create(function ()
      coroutine.yield(1)
      coroutine.yield(2)
      coroutine.yield(3)
      coroutine.yield(4)
      coroutine.yield(5)
      end)
      return co
   end
   if(numberHelper) then
      status, number = coroutine.resume(numberHelper);
      if coroutine.status(numberHelper) == "dead" then
         numberHelper = getNumberHelper()
         status, number = coroutine.resume(numberHelper);
      end
      return number
   else
      numberHelper = getNumberHelper()
      status, number = coroutine.resume(numberHelper);
      return number
   end
end
for index = 1, 10 do
   print(index, getNumber())
end



