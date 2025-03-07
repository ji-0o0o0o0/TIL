package java_practice.week04.Sample01;

public class StudyException {
    public static void main(String[] args) {
        OurClass ourClass = new OurClass();

        try {
            //일단 실행
            ourClass.thisMethodDangerous();
        }catch (OurBadException e){ //thisMethodDangerous()에서 발생할 수 있는 ourbadException을 명시해줌
            System.out.println(e.getMessage());
        }finally {
            //무조건 거침
            System.out.println("우리는 방금 예외를 handling 함 -> 모든 순간 이곳을 지남");
        }
    }
}
