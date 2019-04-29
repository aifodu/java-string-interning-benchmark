public class StringCompare {
    String str1 = "Compare";
    String str2 = "Compare";
    String str3 = new String("Compare");
    final String str4 = str3.intern();

    public void compare() {
        // using == is faster than .equals because it doesn't do a byte-by-byte
        // comparison of string and with interning, it becomes even better
        System.out.println(str1 == str2);
        System.out.println(str2 == str3);
        System.out.println(str3 == str4);
        System.out.println(str1 == str3);
        System.out.println(str1 == str4);

        System.out.println(str1.equals(str2));
        System.out.println(str2.equals(str3));
        System.out.println(str3.equals(str4));
        System.out.println(str1.equals(str4));
        System.out.println(str1.equals(str3));
    }

}