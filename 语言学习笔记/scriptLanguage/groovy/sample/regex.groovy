matcher = 'a b c' =~ /\S/
assert matcher[0] == 'a' 
assert matcher[1..2] == ['b','c']
assert matcher.count == 3

matcher = 'a:1 b:2 c:3' =~ /(\S+):(\S+)/
assert matcher.hasGroup()
assert matcher[0] == ['a:1', 'a', '1']

('xy' =~ /(.)(.)/).each { all, x, y ->
assert all == 'xy'
assert x == 'x'
assert y == 'y'
}






