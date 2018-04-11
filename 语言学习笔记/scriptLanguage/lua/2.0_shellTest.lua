#!/usr/local/bin/lua

--[[
Lua 解释器
Lua 解释器是一个能让您输入 Lua 命令并立即执行的小程序。它在执行一个 Lua 文件过程中，一旦遇到错误就立即停止执行，而不像编译器会执行完整个文件。

Lua 编译器
如果将 Lua 扩展到其它语言或者应用中时，我们需要一个软件开发工具箱以及 Lua 应用程序接口兼容的编译器。


你可以在 shell 中使用 lua -i 或者 lua 命令启动。输入命令后，按下回车键，就启动了交互模式，显示如下：
$ lua -i 
$> print("test")

执行lua文件
方案1：lua test.lua
方案2：
#!/usr/local/bin/lua
$ chmod a+rx test.lua
./test.lua
--]]
print("hello")

