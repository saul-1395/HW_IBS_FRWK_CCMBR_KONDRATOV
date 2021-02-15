public class main {
    public static void main(String[] args) {
        System.out.println(converter("8,3%"));
    }

    protected static float converter(String value) {

        float f;
        value = value.replaceAll("[^{\\d,}]", "");
        value = value.replace(",", ".");


        f = Float.parseFloat(value);
        System.out.println("ковертер " + f);
        return f;
    }
}
