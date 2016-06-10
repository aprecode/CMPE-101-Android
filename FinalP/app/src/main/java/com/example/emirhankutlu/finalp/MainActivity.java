package com.example.emirhankutlu.finalp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private double polyCoefficients;
    static double guess;
    static double accuracy;
    static int iterationLimit;
    private static List<Double> root;

    static Complex[] compCoef = null;

    private static Complex[] add;
    private static Complex[] mult;

    static double real;
    static double imaginary;

    EditText polyCoefficientsET;
    EditText guessET;
    EditText accuracyET;
    EditText iterationLimitET;
    EditText realET;
    EditText imaginaryET;

    TextView rootsText;

    //Declare buttons
    Button rootsCalcButton;         //Calculates roots when the button hit
    Button addToArray;              //Adds a coefficient to coefficients list
    /**new*/Button createComplex;        //Creates a complex
    /*new*/Button calculate;            //Calculates the addition and multiplication

    //Declare an array list which holds polynomial coefficients
    ArrayList<Double> coefficientList = new ArrayList<Double>();

    //Declare an array list which holds complex numbers real and imaginary parts
    /*new*/ArrayList<Double> compx = new ArrayList<Double>();


    static Polynomial polynomial = new Polynomial(root); //polynomial object
    /*new*/ static Complex complex = new Complex(real, imaginary); //complex object






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize the EditTexts
        polyCoefficientsET = (EditText) findViewById(R.id.polyEditText);            //coefficients for polynomial
        guessET = (EditText) findViewById(R.id.guessEditText);                      //a guess for getting a root
        accuracyET = (EditText) findViewById(R.id.accEditText);                     //an accuracy limit to get a better approximation
        iterationLimitET = (EditText) findViewById(R.id.iterationLimitEditText);    //an iteration limit to determine how calculation will be made

        realET = (EditText) findViewById(R.id.realEditText);
        imaginaryET = (EditText) findViewById(R.id.imaginaryEditText);

        rootsText = (TextView) findViewById(R.id.rootsTextView);


        rootsCalcButton = (Button) findViewById(R.id.rootsButton);
        rootsCalcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                guess = Double.parseDouble(guessET.getText().toString());
                accuracy = Double.parseDouble(accuracyET.getText().toString());
                iterationLimit = Integer.parseInt(iterationLimitET.getText().toString());




                root = polynomial.findAllRoots(coefficientList);

                for(int i=0;i<root.size();i++){
                    rootsText.setText("" + root.get(i));
                }
            }
        });





        addToArray = (Button)

                findViewById(R.id.addToArrayButton);

        addToArray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                polyCoefficients = Double.parseDouble(polyCoefficientsET.getText().toString());

                if (polyCoefficientsET.getText().toString().compareTo("") != 0) {

                    coefficientList.add(polyCoefficients);
                    Toast.makeText(getApplicationContext(), polyCoefficients + " " + "is added to array", Toast.LENGTH_LONG).show();

                } else {


                    Toast.makeText(getApplicationContext(), "please enter a coefficient", Toast.LENGTH_LONG).show();

                }


            }
        });





        // \\\\\\\\\\\\\\\ NEW STUFF //////////////



        //Definiton: an array of Complex numbers Complex polynomial
        //Contract: array of Complex numbers -> Comlex Polynomial
        //Purpose: To get a complex polynomial from roots
      /*< static Polynomial makePolynomialFromRoots (Complex[] compCoef){

            Polynomial[] compPoly = new Polynomial[compCoef.length];

            for (int i=0;i<compCoef.length;i++){

         compPoly[i].complexCoefficients = new Complex[2];

         compPoly[i].complexCoefficients[0] = new Complex(-1*compCoef[i].re,-1*compCoef[i].im);

         compPoly[i].complexCoefficients[1] = new Complex(1,0);

            }
                    Polynomial result;

        result = compPoly[0];

        for (int j=1;j<compPoly.length;j++){
                     result = result.multiplyPoly(compPoly[j]);

        }
           return result;
    }*/



        createComplex = (Button)

                findViewById(R.id.createComplexButton);

        createComplex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                real = Double.parseDouble(realET.getText().toString());
                imaginary = Double.parseDouble(imaginaryET.getText().toString());

                if (realET.getText().toString().compareTo("") !=0){

                    compx.add(real);
                    compx.add(imaginary);

                    Toast.makeText(getApplicationContext(), "Complex Number is Created" + real + imaginary + "i", Toast.LENGTH_LONG).show();

                }else{

                    Toast.makeText(getApplicationContext(), "please enter real and imaginary input", Toast.LENGTH_LONG).show();
                }
            }
        });



        calculate = (Button)

                findViewById(R.id.calculateButton);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                add = polynomial.addPoly(complex);


                for(int i=0;i<add.length;i++){
                    rootsText.setText("" + add[i]);
                }

                mult = polynomial.multiplyPoly(complex);

                for(int i=0;i<mult.length;i++){
                    rootsText.setText("" + mult[i]);
                }

            }
        });

    }
}
