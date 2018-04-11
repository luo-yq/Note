/**
 * Created by lyt1987 on 15/4/16.
 */
import pack1.PackClass1 as oldClassTest

//在不修改类名的情况下继承修改一个类
class ClassExtend extends oldClassTest{
    def k;
    ClassExtend(int k){
      this.k=k
    }

    ClassExtend(){

    }
    public static void main(String[] args) {
        System.out.print(System.currentTimeMillis() );
        System.out.println(" ms");
    }
}
