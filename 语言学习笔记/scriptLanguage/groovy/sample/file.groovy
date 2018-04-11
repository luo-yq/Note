def number=0


new File ('../../tmp/tmp.txt').eachLine { 
	line ->
		number++
		println "$number: $line"
}
input = new File('../../tmp/tmp.txt')
assert input.exists()
assert input.canRead()
println input.text

def classes = [String, List, File]
for (clazz in classes)
{
	println clazz.'package'.name
}
println( [String, List, File].'package'.name )


def code = '"1" + "'
code += System.getProperty('os.version')+'"'

println code

println evaluate(code)