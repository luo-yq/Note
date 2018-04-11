Class1  gina = new Class1('Groovy in Action')
assert gina.getTitle() == 'Groovy in Action'
assert getTitleBackwards(gina) == 'noitcA ni yvoorG'
String getTitleBackwards(book) {
	title = book.getTitle()
	return title.reverse()
}


def groovyBook = new Class2()
//通过显示的方法调用来使用属性
groovyBook.setTitle('Groovy conquers the world')
assert groovyBook.getTitle() == 'Groovy conquers the world'
//通过groovy的快捷方式来使用属性
groovyBook.title = 'Groovy in Action'
assert groovyBook.title == 'Groovy in Action'
println groovyBook.title

//只有显示的声明了变量类型后才能调用专有方法
ClassWithAlterMeta mp=new ClassWithAlterMeta(1)+new ClassWithAlterMeta(2)
println mp.mvalue 

