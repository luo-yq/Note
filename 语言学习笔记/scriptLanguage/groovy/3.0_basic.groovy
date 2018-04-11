

char a = 'x'
Character b = 'x'
'x' as char
'x'.toCharacter()
'8'.toInteger()
'8'.toLong()
'8'.toFloat()
'8'.toDouble()



// 字符串中使用el表达式置换变量，“包含的变量被原样输出，‘包含的变量被置换输出，’‘’，”“” 可自由换行
def param="param"
println 'firstline\nsecondline\n$param'
println "firstline\nsecondline\n$param"
println '''
firstline\nsecondline\n$param
forthline
'''
println """
firstline\nsecondline\n$param
forthline
"""


// 字符串默认已el表达式分割成一个strings数组和一个values数组
me = 'Tarzan'
you = 'Jane'
line = "me $me - you $you"
assert line == 'me Tarzan - you Jane'
assert line instanceof GString
assert line.strings[0] == 'me '
assert line.strings[1] == ' - you '
assert line.values[0] == 'Tarzan'
assert line.values[1] == 'Jane'



//序列，列表，片段
println 1..10
println (1..10) 
println (1..<10)
def list=[0,1,2,3,4,0,4,3,2,1]
println list[0..9]
println list[0..<9]
println list[0..-2]
println list[-5..8]


//集合
def x = [1,1,1]
assert [1] == new HashSet(x).toList()
assert [1] == x.unique()

def x2 = [1,null,1]
assert [1,1] == x2.findAll{it != null}
assert [1,1] == x2.grep{it}




//控制语句  if,while,for,switch

//在一行的if语句
if (false) assert false
//null表示false
if (null){
	assert false
}
else{
	assert true
}
//典型的while
def i = 0
while (i < 10) {
	i++
}
assert i == 10
//迭代一个range
def clinks = 0
for (remainingGuests in 0..9) {
	clinks += remainingGuests
}
assert clinks == (10*9)/2
//迭代一个列表
for (j in list) {
	assert j == list[j]
}
//以闭包为参数的each方法
list.each() { item ->
	assert item == list[item]
}
//典型的switch
switch(3) {
	case 1 : assert false; break
	case 3 : assert true; break
	default: assert false
}




