class FormatNumbers {
    public static void main(String[] args) {
        String num = String.format("%, d", 1000000);
        System.out.println(num);

        String num1 = String.format("%.2f", 12344.24545);
        System.out.println(num1);

        String num2 = String.format("%, .3f", 13423525.325346);
        System.out.println(num2);
    }
}