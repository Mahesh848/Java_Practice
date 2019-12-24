import java.util.*;
class ForamtDates {
    public static void main(String[] args) {
        Date today = new Date();
        String ct = String.format("%tc",today);
        System.out.println(ct);

        String day = String.format("%tA, %tB, %td", today, today, today); // day month date
        System.out.println(day);

        day = String.format("%tA, %<tB, %<td", today);
        System.out.println(day);

        Calendar cal = Calendar.getInstance();
        cal.set(1999,10,3,12,45);
        cal.setTimeInMillis(cal.getTimeInMillis());
        System.out.println(cal.getTime());
    }
}