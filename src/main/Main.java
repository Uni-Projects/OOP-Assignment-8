package main;

import polynomial.Polynomial;

/**
 *
 * @author Sjaak Smetsers
 */
public class Main {

    public static void main(String[] args) {
        Polynomial p1 = new Polynomial("3.0 1 2 3");
        Polynomial p2 = new Polynomial("4 0 5 3");
        Polynomial p3 = new Polynomial("3 1 -5 3");
        Polynomial p4 = new Polynomial("-3 1 4 4 4 0");

        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println(p4);
        p1.minus(p2);
        System.out.println(p1);
        p1.evaluate(0);

    }

}
