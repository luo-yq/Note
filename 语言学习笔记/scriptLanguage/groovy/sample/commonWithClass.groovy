class ClassWithAlterMeta{
    int mvalue
    ClassWithAlterMeta(int value){
        mvalue=value
    }
    ClassWithAlterMeta plus(ClassWithAlterMeta mp){
        int mpvalue=mvalue+mp.mvalue
        def nmp=new ClassWithAlterMeta(mpvalue)
        return nmp
    }
}


class ClassWithAlterMeta2{
    int mvalue
    ClassWithAlterMeta2(int value){
        mvalue=value
    }
    ClassWithAlterMeta2 plus(ClassWithAlterMeta2 mp){
        int mpvalue=mvalue+mp.mvalue
        def nmp=new ClassWithAlterMeta2(mpvalue)
        return nmp
    }
}
ClassWithAlterMeta2 cwam2=new ClassWithAlterMeta2(1)
println new ClassWithAlterMeta2(1).mvalue