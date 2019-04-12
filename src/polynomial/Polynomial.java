package polynomial;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Iterator;
import java.util.Collections;
import java.util.ListIterator;

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
        Polynomial copy = new Polynomial (b); 
        Iterator <Term> thisIter = this.terms.iterator();
        while(thisIter.hasNext()){
            Term cur = thisIter.next();
            Iterator <Term> copyIter = copy.terms.iterator();
            while(copyIter.hasNext()){
                Term cur2 = copyIter.next();
                if (cur.getExp() == cur2.getExp()){
                     cur.plus(cur2);
                     copyIter.remove();
                }
            }
            if (cur.getCoef() == 0)
                thisIter.remove();
        }
        this.terms.addAll(copy.terms);
        Collections.sort(terms);
    }

    public void minus(Polynomial b) {
        Polynomial copy = new Polynomial(b);
        Polynomial minus = new Polynomial("-1 0");
        copy.times(minus);
        this.plus(copy);
    }

    public void times(Polynomial b) {
        Polynomial res = new Polynomial (); 
        for(Term t : this.terms){
            Polynomial temp = termTimes(t,b);
            res.plus(temp);
        }
        this.terms = res.terms;
    } 
    
    public Polynomial termTimes (Term a ,Polynomial b){
        Polynomial copy = new Polynomial (b);
        ListIterator<Term> i = copy.terms.listIterator();
        while(i.hasNext()){      
            Term next = i.next();
            next.times(a);
            i.set(next); 
        }
        return copy;
    }
    
    public void divide(Polynomial b) {
    }
    
    public void evaluate (int x){
        double res = 0;
        Iterator<Term> i = terms.iterator();
        while (i.hasNext()){
            //Term temp = i.next();
            res += i.next().evaluate(x);
        }
        System.out.println (res);
    }
    
    @Override
    public boolean equals(Object other_poly) {
        
        if(other_poly != null && other_poly.getClass() == this.getClass()){     
            Polynomial p = (Polynomial)other_poly;
            Iterator<Term> i = p.terms.listIterator();
            Iterator<Term> i2 = this.terms.listIterator();
            while(i.hasNext()){
                if(!i.next().equals(i2.next())){
                    return false;
                }
            }
            return true;
        }else 
            return false;    
    }
}
