import java.util.*;
class Wrappers {
    public static void main(String[] args) {
        Integer num1 = Integer.valueOf(3);
        int num2 = num1.intValue();
        System.out.println(num2);

        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        System.out.println(list.get(0));

        int str_to_num = Integer.parseInt("2");
        System.out.println(str_to_num);

        String num_to_str = Integer.toString(233);
        System.out.println(num_to_str);
    }
}