-module(hello).
-export([start/0,add/3,add/1,test/0,getListByWhere/1]).

start()->
	io:format("hello world-n").

add(int , One,Two)->
	Ret=(One+Two),
	Ret=Ret;
add(double , One,Two)->
	(One+Two).
add({int , One,Two})->
	Ret=(One+Two),
	Ret=Ret;
add({double , One,Two})->
	Double=fun(X)->2*X end,
	Double(One+Two).
getListByWhere(N)->
	[{A,B,C}||
		A<-lists:seq(1,N),
		B<-lists:seq(1,N),
		C<-lists:seq(1,N),
		A=<B,
		A*A+B*B=:=C*C
	].
test()->
	2=add(int,1,1),
	2=add(double,1,1),
	2=add({int,1,1}),
	4=add({double,1,1}),
	[{3,4,5},{6,8,10}]=getListByWhere(10),
	test_worked.