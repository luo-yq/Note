
--多行文本[[...]]
multiLine=[[
firstLine
secondLine
]]

print(multiLine)

--[[
单引号字符串
双引号字符串
\[\[和\]\]之间的字符串
--]]

string1 = "Lua"
print("\"String 1 is\"",string1)
string2 = 'Tutorial'
print("String 2 is",string2)

string3 = [["Lua Tutorial"]]
print("String 3 is",string3)
--[[
字符串中转义字符用于改变字符的一般正常的解释。在上面的例子中，输出双引号（""）的时候，我们使用的是 \"。下表列出了转义序列及相应的使用方法：

转义序列	用法
\a响铃    \b退格    \f换页    \n换行    \r回车     \t制表符    \v垂直制表符    \\反斜线    \"双引号    \'单引号    \[左方括号    \]右方括号
--]]
--[[
	string.upper(argument):将输入参数全部字符转换为大写并返回。
	string.lower(argument):将输入参数全部字符转换为小写并返回。
	string.gsub(maingString,findString,replaceString):将 mainString 中的所有 findString 用 replaceString 替换并返回结果。
	string.strfind(mainString,findString,optionalStartIndex,optionalEndIndex):在主字符串中查找 findString 并返回 findString 在主字符串中的开始和结束位置，若查找失败则返回 nil。
	string.reverse(arg):将输入字符串颠倒并返回。
	string.format(...):返回格式化后的字符串。
	string.char(arg) 和 string.byte(arg):前者返回输出参数的所代表的字符，后者返回输入参数（字符）的数值。
	string.len(arg):返回输入字符串的长度。
	string.rep(string,n): 将输入字符串 string 重复 n　次后的新字符串返回。
	..:连接两个字符串。
--]]
string1 = "Lua";
print(string.upper(string1))
print(string.lower(string1))
string = "Lua Tutorial"
-- 替换字符串
newstring = string.gsub(string,"Tutorial","Language")
print("The new string is",newstring)
string = "Lua Tutorial"
-- 查找字符串
print(string.find(string,"Tutorial"))
reversedString = string.reverse(string)
print("The new string is",reversedString)
string1 = "Lua"
string2 = "Tutorial"
number1 = 10
number2 = 20
-- 基本字符串格式
print(string.format("Basic formatting %s %s",string1,string2))
-- 日期格式化
date = 2; month = 1; year = 2014
print(string.format("Date formatting %02d/%02d/%02d", date, month, year))
-- 符点数格式化
print(string.format("%.4f",1/3))
-- 字节转换
-- 第一个字符
print(string.byte("Lua"))
-- 第三个字符
print(string.byte("Lua",3))
-- 倒数第一个字符
print(string.byte("Lua",-1))
-- 第二个字符
print(string.byte("Lua",2))
-- 倒数第二个字符
print(string.byte("Lua",-2))

-- 内部 ASCII 字值转换为字符
print(string.char(97)) 
string1 = "Lua"
string2 = "Tutorial"
-- 用 .. 连接两个字符串
print("Concatenated string",string1..string2)

-- 字符串的长度
print("Length of string1 is ",string.len(string1))

-- 重复字符串
repeatedString = string.rep(string1,3)
print(repeatedString) 
