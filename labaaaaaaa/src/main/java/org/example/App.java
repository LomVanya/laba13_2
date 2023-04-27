package org.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Tester tester1 = new Tester("Petr", "Petrov", 5, "Advanced", 2500.0);
        Tester tester2 = new Tester("Vasiliy", "Popov", 3, "Advanced");

        tester1.printInfo();
        System.out.println();
        tester2.printInfo(true);
        System.out.println();
        tester2.printInfo(false, true);
        System.out.println();

        Tester.doSomethingStatic();
    }
}
