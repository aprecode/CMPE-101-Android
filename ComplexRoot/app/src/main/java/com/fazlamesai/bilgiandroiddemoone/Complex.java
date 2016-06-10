package com.fazlamesai.bilgiandroiddemoone;

/**
 * Created by emirhankutlu on 06/05/15.
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

    public Complex add (Complex b){
        Complex a = this;
        double real = a.re + b.re;
        double imag = a.im + b.im;
        return new Complex(real, imag);
    }

    public Complex substract (Complex b){
        Complex a = this;
        double real = a.re - b.re;
        double imag = a.im - b.im;
        return new Complex(real,imag);
    }

    public Complex multiply (Complex b){
        Complex a = this;
        double real = a.re * b.re - a.im * b.im;
        double imag = a.re * b.im + a.im * b.re;
        return new Complex(real, imag);
    }

    public Complex divide (Complex b){
        Complex a = this;
        double real = a.re * b.re + a.im * b.im /
                Math.pow(b.re, 2) + Math.pow(b.im, 2);
        double imag = a.im * b.re - a.im * b.im /
                Math.pow(b.re, 2) + Math.pow(b.im, 2);
        return new Complex(real, imag);
    }

}
