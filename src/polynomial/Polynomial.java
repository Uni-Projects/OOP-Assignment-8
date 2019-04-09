package polynomial;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Iterator;
import java.util.Collections;

/**
 * A skeleton class for representing Polynomials
 *
 * @author Sjaak Smetsers
 * @date 19-04-2016
 */
public class Polynomial {

    /**
     * A polynomial is a sequence of terms here kept in an List
     */
    List<Term> terms;
   
    /**
     * A constructor for creating the zero Polynomial zero is presented as an
     * empty list of terms and not as a single term with 0 as a coefficient
     */
    public Polynomial() {
        terms = new LinkedList<>();
    }

    /**
     * A Constructor creating a polynomial from the argument string.
     *
     * @param s a String representing a list of terms which is converted to a
     * scanner and passed to scanTerm for reading each individual term
     */
    public Polynomial( String s ) {
        terms = new LinkedList<>();
        Scanner scan = new Scanner( s );

        for (Term t = Term.scanTerm(scan); t != null; t = Term.scanTerm(scan)) {
            terms.add(t);
        }
    }

    /**
     * The copy constructor for making a deep copy
     *
     * @param p the copied polynomial
     *
     */
    public Polynomial( Polynomial p ) {
        terms = new LinkedList<>();
        for (Term t : p.terms) {
            terms.add(new Term(t));
        }
    }
    
    /**
     * A straightforward conversion of a Polynomial into a string based on the
     * toString for terms
     *
     * @return a readable string representation of this
     */
    @Override
    public String toString() {
       Iterator <Term> t = terms.iterator();
       StringBuilder s = new StringBuilder();
       while (t.hasNext()) {
           s.append(t.next().toString());
           if(t.hasNext())
             s.append(" + ");
       }     
       return s.toString() ;
    }

    public void plus(Polynomial b) {
        Polynomial c = new Polynomial (b); 
        Iterator <Term> i = this.terms.iterator();
        while(i.hasNext()){
            Term c1 = i.next();
            Iterator <Term> i2 = c.terms.iterator();
            while(i2.hasNext()){
                Term c2 = i2.next();
                if (c1.getExp() == c2.getExp()){
                     c1.plus(c2);
                     i2.remove();
                }
            }
        }
        this.terms.addAll(c.terms);
        Collections.sort(terms);
    }


    public void minus(Polynomial b) {
        Polynomial b2 = new Polynomial(b);
        Polynomial c = new Polynomial("-1 0");
        b2.times(c);
        this.plus(b2);
    }

    public void times(Polynomial b) {
        Polynomial c = new Polynomial (); 
        Iterator <Term> i = this.terms.iterator();
        while(i.hasNext()){
            Term c1 = i.next();
            Iterator <Term> i2 = b.terms.iterator();
            while(i2.hasNext()){
                Term c2 = i2.next();
                Term temp = new Term(c1);
                temp.times(c2);
                c.terms.add(temp); 
            }
        }
        this.terms = c.terms;
    }
    public void divide(Polynomial b) {
    }

    @Override
    public boolean equals(Object other_poly) {
        return false;
    }

}
