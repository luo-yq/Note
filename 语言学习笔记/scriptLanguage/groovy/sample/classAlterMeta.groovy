class A{
       int w;
       A(){
           w=1;
       }
       A(int value){
           w=value;
       }

}

A.metaClass."v" = {
             ->
               println 'far'
      }
 
println new A().v()

println A.class.methods.name.grep(~/get.*/).sort()
println A.class.methods.name.sort()