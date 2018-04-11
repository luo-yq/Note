“#!” 是一个约定的标记，它告诉系统这个脚本需要什么解释器来执行，即使用哪一种 Shell。
#!/bin/bash

设置可执行权限
chmod +x ./test.sh  #使脚本具有执行权限
./test.sh  #执行脚本


/bin/sh test.sh #以sh执行脚本
/bin/php test.php #执行php

#变量操作
variable=value   #定义变量
readonly variable   #设置为只读，不允许修改，也不允许删除
unset variable  #删除变量
echo ${variable}yey #无后继字符时{}可省略


$echo $0	#当前脚本的文件名
$echo $n	#传递给脚本或函数的参数。n 是一个数字，表示第几个参数。例如，第一个参数是$1，第二个参数是$2。
$echo $#	#传递给脚本或函数的参数个数。
$echo $*	#传递给脚本或函数的所有参数。 输出  "$1 $2 $3 ...$n"
$echo "$*"	#传递给脚本或函数的所有参数。 输出  "$1 $2 $3 ...$n"
$echo $@	#传递给脚本或函数的所有参数。 输出  "$1 $2 $3 ...$n"
$echo "$@"	#传递给脚本或函数的所有参数。 输出  "$1" "$2" "$3" ... "$n"
$echo $?	#上个命令的退出状态，或函数的返回值。大部分命令执行成功会返回 0，失败返回 1。不过，也有一些命令返回其他值，表示不同类型的错误。
$echo $$	#当前Shell进程ID，即pid。对于 Shell 脚本，就是这些脚本所在的进程ID。

$10 取$1 后补1  ${10} 取 第10个参数

#转义符和echo，sed连用时
echo -e '\n' #表示新行
echo -e '\r' #表示回车
echo -e '\t' #表示水平的制表符
echo -e '\v' #表示垂直的制表符
echo -e '\b' #表示后退符
echo -e '\a' #表示“警告”（蜂鸣或是闪动）
echo -e '\0xxx' #翻译成ASCII码为八进制0xx所表示的字符  如: \077 \0077  输出？  \0 本身输出空白



printf 命令的完整语法分为两部分: printf format-string [arguments....]
分析:printf 是命令，不解释。format-string 为格式控制字符串，arguments 为参数列表。
printf 命令不用加括号。 format-string 可以没有引号，但是最好加上，单双引号均可。参数多于格式控制符(%)时，format-string 可以重用，可以将所有参数都转换 arguments:使用空格分割，不用逗号。 printf “%d,%s\n” 1 abc 这里输出的是 1, abc。有没有引号都可以。

如果没有 arguments %s 用 NULL 表示，%d 用 0 表示
例如 :printf “%s , %d\n” 输出结果为 0

format-string 的可重用性:printf “%s” abc def==>abcdef

如果以 %d 来显示字符串，会有警告，提示无效的数字，此时的默认值为 0。 例如:printf "%d\n" abc==>bash: printf: abc: invalid number 0; 
liunx 的各种版本对 echo 的移植性不好，所以引入了 printf，printf 可以说是 echo 的加强版，是由 POSIX 标准定义。
printf的转义序列
\a	警告字符，通常为ASCII的BEL字符
\b	后退
\c	不显示输出结果中任何结尾的换行字符（只在%b格式指示符控制下的参数字符串中有效），而且任何留在参数里的字符、任何接下来的参数以及任何留在格式字符串中的字符，都被忽略
\f	换页（formfeed）
\n	换行
\r	回车（Carriage return）
\t	水平制表符
\v	垂直制表符
\\	一个字面上的反斜杠字符
\ddd	表示1到3位数八进制值的字符。仅在格式字符串中有效
\0ddd	表示1到3位的八进制值字符



IFS：有space或者tab或者Enter三者之一组成 (我们常用 space)
CR: 由Enter产生；
IFS是用来拆解command line中每一个词 (word) 用的， 因为shell command line是按词来处理的。 而CR则是用来结束command line用的，这也是为何我们敲Enter键， 命令就会跑的原因。

除了常用的IFS与CR, 常用的 meta 还有：

meta 字符	meta 字符作用
=	设定变量
$	作变量或运算替换 (请不要与shell prompt混淆)
>	输出重定向 (重定向 stdout)
<	输入重定向 (重定向 stdin)
|	命令管道
&	重定向 file descriptor 或将命令至于后台 (bg) 运行
()	将其内部的命令置于 nested subshell 执行，或用于运算或变量替换
{}	将期内的命令置于 non-named function 中执行，或用在变量替换的界定范围
;	在前一个命令执行结束时，而忽略其返回值，继续执行下一个命令
&&	在前一个命令执行结束时，若返回值为 true，继续执行下一个命令
||	在前一个命令执行结束时，若返回值为 false，继续执行下一个命令
!	执行 histroy 列表中的命令


防止读文件时被过滤掉空格
old_IFS=$IFS
IFS=; #将IFS设置为null
cat file | while read i
do
  echo "$i"
done
IFS=old_IFS #恢复IFS的原始值
