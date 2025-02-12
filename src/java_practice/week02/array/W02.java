package java_practice.week02.array;

public class W02 {
    public static void main(String[] args) {
        //문자(char / 1byte), 문자열(String)
        //String = char[]

        //기본형 변수 vs 참조형 변수
        //1.기본형 변수는 소문자로 시작, 참조형변수는 대문자로 시작
        //  - wrapper class에서 기본형 변수 감싸줄때(boxing), int ->Integer
        //2.기본형 변수는 값 자체를 저장하고, 참조형 변수는 별도 공간에 저장 후 그 주소를 저장(=주소형 변수)

        //String은 wrapper class와 비슷

        //String 기능 활용 예시
        //1. length
        String str = "ABCD";
        System.out.println(str.length());

        //2. charAt(int indx)
        System.out.println(str.charAt(2));//3번째 자리의 C 출력

        //3. substring(int fromidx, in toidx)
        System.out.println(str.substring(0,3));//0에서 2번째 자리인 ABC 출력

        //4. equals
        String string = "ABCD";
        System.out.println(str.equals(string));//안에 내용물이 같은지 확인

        //5. toCharArray() : String -> char[]
        char[] strCharArray= str.toCharArray();

        //6. char[] -> String
        char[] test = {'a','b','c'};
        String values= new String(test);
        System.out.println(values);

    }
}
