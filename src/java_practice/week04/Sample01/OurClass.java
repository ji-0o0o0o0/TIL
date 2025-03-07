package java_practice.week04.Sample01;

public class OurClass {
    private  final  boolean just = true;

    //throws
    public void thisMethodDangerous() throws OurBadException{
        //custom logic
        if (just){
            throw new OurBadException();
        }
    }
}
