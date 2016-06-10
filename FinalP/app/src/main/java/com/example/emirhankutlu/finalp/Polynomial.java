package com.example.emirhankutlu.finalp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emirhankutlu on 17/05/15.
 */
public class Polynomial {
    List<Double> polyCoefficients;
    static double accuracy;
    static double guess;
    List<Double> poly = new ArrayList<Double>();
    static int iterationLimit;

    Complex[] complexCoefficients;



    String temp;


    Polynomial(List<Double> x) {

        polyCoefficients = x;



    }


    public String toString(){

        poly = findAllRoots(poly);
        for(int i=0; i<poly.size();i++){
            temp += "" + poly.get(i);
        }
        return temp;
    }




    //gives roots of a polynomial
    //Data definition: a double list of polynomial, a double guess, a double accuracy limit, and an integer iteration limit

    //Contract: poly guess acclimit iterlimit —> root

    //Purpose: Find a root of a polynomial

    //Example:
    //[x+1] —> -1.0
    //[x^2-3x+2] —> 2.0



    public static double findRoot(List<Double> poly, double guess, double accuracy, int iterationLimit) {


        Polynomial deriv;

        deriv = MainActivity.polynomial.findDerivative();

        int iterationCount = 0;

        while (Math.abs(evaluatePolynomial(poly, guess)) > /*MainActivity.*/accuracy) {

            if (iterationCount > iterationLimit || guess == 0.0) {

                return 0.0;

            } else {

                guess = guess - evaluatePolynomial(poly, guess) / evaluatePolynomial((List<Double>) deriv, guess);
                iterationCount = iterationCount + 1;

            }

        }

        return guess;

    }






    //takes coefficients and produces a polynomial
    //Data definition: a double list of polynomial and a double value

    //Contract: poly x —> root

    //Purpose: Method gives a value of a polynomial for a value x

    //Example:
    //[1 1] 1 —> 2.0
    //[1 2 3] 2 —> 17.0
    public static double evaluatePolynomial(List<Double> poly, double x) {

        double result = 0.0;

        for (int i = 0; i < poly.size(); i++) {

            result = result+poly.get(i)* Math.pow(x,i);

        }
        return result;
    }








    //takes the differentiate of a polynomial
    //Data definition: a double list of polynomial

    //Contract: poly —> derivative of poly

    //Purpose: Gives the first derivative of a polynomial

    //Example:
    //[3x^2+2x+1] —> 6x+2
    //[4x+5] —> 4.0


    public  Polynomial findDerivative() {

        List<Double> result = new ArrayList<>();

        for (int i = 0; i < polyCoefficients.size()-1; i++) {
            result.add(polyCoefficients.get(i + 1) * (i + 1)) ;
        }

        return new Polynomial(result);
    }




    // takes a polynomial and gives all roots
    //Data definition: Double list of Polynomial

    //Contract: Poly —> Roots

    //Purpose: Gives all roots of a polynomial

    //Example:
    //[2 3 1] —> [1.0 2.0]
    //[-1 0 1] —> [1.0 -1.0]

    public static List<Double> findAllRoots (List<Double> poly) {

        List<Double> roots = new ArrayList<>();

        int i = 0;

        while (roots.size() < poly.size() - 1 && i < MainActivity.iterationLimit) {

            roots = addUniqueRoot(roots, findRoot(poly, guess, accuracy, iterationLimit));
            i++;
        }

        return roots;
    }






    // takes a list of root and adds newt root to roots if it's not in the roots
    //Data definition: Unordered collection of unique roots stored in an array of doubles

    //Contract: Roots Root —> Roots

    //Purpose: Add valid root to roots

    //Example:
    //[] 1.0 —> [1]
    //[1.0] 1.0 —> [1]
    //[1.0] 0.99999 —> [1.0]

    public static List<Double> addUniqueRoot(List<Double> roots, double root) {

        if (isRootInRoots(roots, root)) {

            return roots;
        }

        else

        {

            return addRootToRoots(roots, root);
        }
    }





    // checks is the root in roots or not
    //Data definition: roots which is unordered value of unique roots and root is a double value

    //Contract: roots root —> boolean

    //Purpose: Is there a value in roots which is with in the accuracy limit from root?

    //Example:
    //[] 1.0 —> false
    //[1.0] 1.0 —> true
    //[1.0] 0.99999 —> true

    public static boolean isRootInRoots(List<Double> roots, double root){

        double accuracyLimit = 0.00001;

        boolean result = false;

        for (int i=0; i<roots.size(); i++){

            result = result || Math.abs(root - roots.get(i)) < accuracyLimit;
        }

        return result;
    }







    // adds root to roots
    //Data definition: root which is not in roots and roots which is unordered array of unique doubles

    //Contract: Roots Root —> Roots

    //Purpose: add value root to roots

    //Example
    //[] 3 —> [3.0]
    //[1.0 2.0 3.0] 4.0 —> [1.0 2.0 3.0 4.0]
    public static List<Double> addRootToRoots (List<Double> roots, double root){

        List<Double> result = new ArrayList<>();

        for (int i=0; i<roots.size(); i++){

            result.add(roots.get(i));
        }


        result.add(root);
        return result;

    }


    // \\\\\\\\\\\\ NEW STUFF /////////////



    // Adds a polynomial with a complex polynomial
    //Data definition polynomial and a complex
    //contract polynomial complex -> complex
    Complex[] addPoly(Complex complex){
        Complex[] result = new Complex[complexCoefficients.length + 1 ];
        for(int i=0; i < complexCoefficients.length; i++){
            result[i] = complexCoefficients[i] ;
        }
        result[result.length - 1] = complex;
        return result;
    }



    //Multiplies a polynomial with a complex polynomial
    //Data definition: polynomial complex -> complex
    //Contract polynomial complex -> complex
    Complex[] multiplyPoly(Complex complex){
        for(int i = 0; i < polyCoefficients.size() ; i++){
            complexCoefficients[i].re =  complexCoefficients[i].re * complex.re - complexCoefficients[i].im * complex.im;
            complexCoefficients[i].im = complexCoefficients[i].re * complex.im + complexCoefficients[i].im * complex.re;
        }
        return complexCoefficients;
    }
}
