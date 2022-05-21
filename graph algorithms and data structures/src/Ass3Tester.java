import java.io.FileNotFoundException;

public class Ass3Tester {
    
    public static void main(String[] args) throws FileNotFoundException {
        Vaccines vaccines = new Vaccines();
        vaccines.rafaleTesting(7);
        vaccines.rafaleCustomTesting(6);
        Honors honors = new Honors();
        honors.rafaleTesting(30);
    }
}
