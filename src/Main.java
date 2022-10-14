public class Main {
    public static void main(String[] args) {
        StringList stringList = new StringListImpl(3);
        stringList.add("da0");
        stringList.add("da");
        stringList.add("da2");
        System.out.println(stringList);

        stringList.add("da");
        stringList.add("da2");
        System.out.println(stringList);

        System.out.println("stringList.add(2, \"net\") = " + stringList.add(2, "net"));
        System.out.println(stringList);

        stringList.remove("da");
        System.out.println(stringList);

        stringList.add("end");
        System.out.println(stringList);
    }
}