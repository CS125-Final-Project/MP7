import java.util.Scanner;

/**
 * It's a class. Yeah!
 * 
 * @author dvg
 */
public class TestClass {
    /**
     * Does stuff.
     * 
     * @param args unused
     */
    public static void main(final String[] args) {
        System.out.println("Do a thing.");
        Scanner input = new Scanner(System.in);
        if (input.nextInt() == 0) {
            System.out.println("That's 0.");
        } else {
            System.out.println("That's not 0.");
        }
        input.close();
    }
}