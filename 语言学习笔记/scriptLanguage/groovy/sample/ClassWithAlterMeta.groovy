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
