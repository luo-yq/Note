//断言
assert(true)
assert 1 == 1




def current = 1
def next = 1
10.times { //循环10次
	print current + ' '
	def newCurrent = next
	next = next + current
	current = newCurrent
}
println ''
