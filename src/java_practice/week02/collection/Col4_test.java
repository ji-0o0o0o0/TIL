package java_practice.week02.collection;

import java.util.*;

public class Col4_test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String colType= sc.nextLine();
        String title= sc.nextLine();

        switch (colType){
            case "List":
                ArrayList<String> strList = new ArrayList<>();
                while (true) {
                    String text = sc.nextLine();
                    if (Objects.equals(text, "끝")) {
                        break;
                    }
                    strList.add(text);
                }
                System.out.println("["+colType+"으로 저장된 백종원 돼지고기 김치찌개 만들기]");
                for (int i = 0; i <strList.size() ; i++) {

                    System.out.println((i+1)+"."+strList.get(i));
                }
                break;
            case "Set":
                LinkedHashSet<String> stringSet = new LinkedHashSet<String>();
                while (true) {
                    String text = sc.nextLine();
                    if (Objects.equals(text, "끝")) {
                        break;
                    }
                    stringSet.add(text);
                }
                System.out.println("["+colType+"으로 저장된 백종원 돼지고기 김치찌개 만들기]");
                Iterator iterator = stringSet.iterator();
                for (int i = 0; i <stringSet.size() ; i++) {

                    System.out.println((i+1)+"."+iterator.next());
                }
                break;
            case "Map":
                Map<Integer,String> stringMap = new HashMap<Integer,String>();
                int idx =1;
                while (true) {
                    String text = sc.nextLine();
                    if (Objects.equals(text, "끝")) {
                        break;
                    }
                    stringMap.put(idx++,text);
                }
                System.out.println("["+colType+"으로 저장된 백종원 돼지고기 김치찌개 만들기]");
                for (int i = 0; i <stringMap.size() ; i++) {

                    System.out.println((i+1)+"."+stringMap.get(i+1));
                }
                break;
        }


    }
}
