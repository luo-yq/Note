def totalClinks = 0
def partyPeople = 100
1.upto(partyPeople) { guestNumber ->
    clinksWithGuest = guestNumber-1
    totalClinks += clinksWithGuest
}
assert totalClinks == (partyPeople*(partyPeople-1))/2

//闭包
def adder = { x, y=5 -> return x+y }
assert adder(4, 3) == 7
assert adder.call(7) == 12

//闭包作为参数
def caller (Closure closure){
closure.getParameterTypes().size()
}
assert caller { one -> } == 1 
assert caller { one, two -> } == 2

//grep和switch
assert [1,2,3].grep{ it<3 } == [1,2]
switch(10){
case {it%2 == 1} : assert false
}


list2=[1, 2, 3].collect{ return it * 2 }
println list2




