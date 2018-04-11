--文件夹不存在则创建文件夹
if(os.execute("[ ! -d ../testdir ]")) then
	os.execute("mkdir ../testdir")
end
--文件不存在则创建文件
file = io.open("../testdir/testfile.txt", "w")
io.output(file)
io.write("-- End of the ../testfile.txt file")
io.close(file)


--[[
打开文件操作
file = io.open (filename [, mode])
可选的打开文件的模式如下表所示
"r"	只读模式，这也是对已存在的文件的默认打开模式。
"w"	可写模式，允许修改已经存在的文件和创建新文件。
"a"	追加模式，对于已存的文件允许追加新内容，但不允许修改原有内容，同时也可以创建新文件。
"r+"	读写模式打开已存的在文件。
"w+"	如果文件已存在则删除文件中数据；若文件不存在则新建文件。读写模式打开。
"a+"	以可读的追加模式打开已存在文件，若文件不存在则新建文件。
--]]

--隐式文件描述符
--隐式文件描述符使用标准输入输出模式或者使用单个输入文件和输出文件。使用隐匿文件描述符的示例代码如下：

-- 只读模式打开文件
file = io.open("../testfile.txt", "w")
-- 将 ../testfile.txt 设置为默认输入文件
io.input(file)
--打印输出文件的第一行
print(io.read())
-- 关闭打开的文件
io.close(file)
-- 以追加模式打开文件
file = io.open("../testfile.txt", "a")
-- 将 ../testfile.txt 设置为默认的输出文件
io.output(file)
-- 将内容追加到文件最后一行
io.write("-- End of the ../testfile.txt file")
-- 关闭打开的文件
io.close(file)
--[[
从上面的例子中，你可以看到隐式的描述述如何使用 io."x" 方法与文件系统交互。上面的例子使用 io.read() 函数时没有使用可选参数。此函数的可选参数包括：

模式	描述
"*n"	从文件当前位置读入一个数字，如果该位置不为数字则返回 nil。
"*a"	读入从当前文件指针位置开始的整个文件内容。
"*i"	读入当前行。
number	读入指定字节数的内容。
另外一些常用的方法：

io.tmpfile():返回一个可读写的临时文件，程序结束时该文件被自动删除。
io.type(file):检测输入参数是否为可用的文件句柄。返回 "file" 表示一个打开的句柄；“closed file” 表示已关闭的句柄；nil 表示不是文件句柄。
io.flush():清空输出缓冲区。
io.lines(optional file name): 返回一个通用循环迭代器以遍历文件，每次调用将获得文件中的一行内容,当到文件尾时，将返回nil。若显示提供了文件句柄，则结束时自动关闭文件；使用默认文件时，结束时不会自动关闭文件。
显示文件描述符
我们也会经常用到显示文件描述符，因为它允许我们同时操作多个文件。这些函数与隐式文件描述符非常相似，只不过我们在这儿使用 file:function_name 而不是使用 io.function_name 而已。下面的例子使用显示文件描述符实现了与前面例子中完全相同的功能。
--]]

-- 只读模式打开文件
file = io.open("../testfile.txt", "r")
-- 输出文件的第一行
print(file:read())
-- 关闭打开的文件
file:close()
-- 以追加模式打开文件 
file = io.open("../testfile.txt", "a")
-- 添加内容到文件的尾行
file:write("--test")
-- 关闭打开的文件
file:close()

--[[
在显式文件描述符中，打开文件的描述与读文件时的参数与隐式文件描述中的完全相同。另外的常用方法包括：

file:seek(option whence,option offset)：此函数用于移动文件指针至新的位置。参数 whence 可以设置为 “set”，"cur","end"，offset 为一个偏移量值，描述相对位置。如果第一个参数为 "set"，则相对位置从文件开始处开始计算；如果第一个参数为 "cur"，则相对位置从文件当前位置处开始计算； 如果第一个参数为 "end"，则相对位置从文件末尾处开始计算。函数的参数默认值分别为 "cur" 和 ０，因此不传递参数调用此函数可以获得文件的当前位置。
file:flush()：清空输出缓冲区。
io.lines(optional file name)：提供一个循环迭代器以遍历文件，如果指定了文件名则当遍历结束后将自动关闭该文件；若使用默认文件，则遍历结束后不会自动关闭文件。
下面的例子演示 seek 函数的使用方法。它将文件指针从文件末尾向前移动 25。并使用 read 函数从该位置出输出剩余的文件内容。
--]]
-- Opens a file in read
file = io.open("../testfile.txt", "r")
file:seek("end",-25)
print(file:read("*a"))
-- closes the opened file
file:close()