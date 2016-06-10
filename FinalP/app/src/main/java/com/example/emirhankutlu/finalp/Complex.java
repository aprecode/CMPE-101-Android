package com.example.emirhankutlu.finalp;

/**
 * Created by emirhankutlu on 17/05/15.
 */
public class Complex {
    double re;
    double im;



    public Complex(double real, double imag) {
        re = real;
        im = imag;
    }




    public String toString () {
        if (im == 0) return re + "";
        if (re == 0) return im + "i";
        if (im < 0) return re + "-" + -im + "i";
        return re + "+" + im + "i";
    }

    //Data definition: Complex
    //Contract: Complex Complex -> Complex
    //Purpose: To add two complex numbers
    //Example:
    // 3+4i + 4+i -> 7+5i
    // 2 + 5+2i -> 7+2i
    public Complex add (Complex b){
        Complex a = this;
        double real = a.re + b.re;
        double imag = a.im + b.im;
        return new Complex(real, imag);
    }

    //Data definition: Complex
    //Contract: Complex Complex -> Complex
    //Purpose: To substract complex numbers
    //Example:
    // 3+4i - 4+i -> -1+3i
    // 2 - 1+2i -> 1-2i
    public Complex substract (Complex b){
        Complex a = this;
        double real = a.re - b.re;
        double imag = a.im - b.im;
        return new Complex(real,imag);
    }

    //Data definition: Complex
    //Contract: Complex Complex -> Complex
    //Purpose: To multiply complex numbers
    //Example:
    // 3+4i * 4+i -> 8+19i
    // 2 * 1+2i -> 2+4i
    public Complex multiply (Complex b){
        Complex a = this;
        double real = a.re * b.re - a.im * b.im;
        double imag = a.re * b.im + a.im * b.re;
        return new Complex(real, imag);
    }

    //Data definiton
    //Contract: Complex Complex -> Complex
    //Purpose: To divide complex numbers
    public Complex divide (Complex b){
        Complex a = this;
        double real = a.re * b.re + a.im * b.im /
                Math.pow(b.re, 2) + Math.pow(b.im, 2);
        double imag = a.im * b.re - a.im * b.im /
                Math.pow(b.re, 2) + Math.pow(b.im, 2);
        return new Complex(real, imag);
    }

    Complex c1 = new Complex(2.0,3.0);
    Complex c2 = new Complex(3.0,5.0);
}
