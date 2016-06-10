package com.fazlamesai.bilgiandroiddemoone;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static android.view.View.OnClickListener;


public class MainActivity extends Activity {

    private double polyCoefficients;
    static double guess;
    static double accuracy;
    static int iterationLimit;
    private List<Double> root;

    EditText polyCoefficientsET;
    EditText guessET;
    EditText accuracyET;
    EditText iterationLimitET;

    TextView rootsText;

    //Declare buttons
    Button rootsCalcButton;         //Calculates roots when the button hit
    Button addToArray;              //Adds a coefficient to coefficients list

    //Declare a array list which holds polynomial coefficients
    ArrayList<Double> coefficientList = new ArrayList<Double>();


    //Declare another array list which holds roots
    ArrayList<Double> rootsList = new ArrayList<Double>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize the EditTexts
        polyCoefficientsET = (EditText) findViewById(R.id.polyEditText);            //coefficients for polynomial
        guessET = (EditText) findViewById(R.id.guessEditText);                      //a guess for getting a root
        accuracyET = (EditText) findViewById(R.id.accEditText);                     //an accuracy limit to get a better approximation
        iterationLimitET = (EditText) findViewById(R.id.iterationLimitEditText);    //an iteration limit to determine how calculation will be made


        rootsText = (TextView) findViewById(R.id.rootsTextView);


        rootsCalcButton = (Button) findViewById(R.id.rootsButton);
        rootsCalcButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                guess = Double.parseDouble(guessET.getText().toString());
                accuracy = Double.parseDouble(accuracyET.getText().toString());
                iterationLimit = Integer.parseInt(iterationLimitET.getText().toString());

                findAllRoots(coefficientList);

                for(int i=0;i<root.size();i++){
                    rootsText.setText("" + root.get(i));
                }
            }
        });





        addToArray = (Button)

                findViewById(R.id.addToArrayButton);

        addToArray.setOnClickListener(new OnClickListener() {
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
 }



        //gives roots of a polynomial

    public static double findRoot(List<Double> poly, double guess, double accuracy, int iterationLimit) {



        List<Double> deriv = findDerivative(poly);

        int iterationCount = 0;

        while (Math.abs(evaluatePolynomial(poly, guess)) > accuracy) {

            if (iterationCount > iterationLimit || guess == 0.0) {

                return 0.0;

            } else {

                guess = guess - evaluatePolynomial(poly, guess) / evaluatePolynomial(deriv, guess);
                iterationCount = iterationCount + 1;

            }

        }

        return guess;

    }





    //takes coefficients and produces a polynomial
    public static double evaluatePolynomial(List<Double> poly, double x) {

        double result = 0.0;

        for (int i = 0; i < poly.size(); i++) {

            result = result+poly.get(i)* Math.pow(x,i);

        }
        return result;
    }




    //takes the differentiate of a polynomial
    public static List<Double> findDerivative(List<Double> poly) {

        List<Double> result = new ArrayList<>();

        for (int i = 0; i < poly.size()-1; i++) {
            result.add(poly.get(i + 1) * (i + 1)) ;
        }

        return result;
    }


    public static List<Double> findAllRoots (List<Double> poly) {

        List<Double> roots = new ArrayList<>();

        int i = 0;

        while (roots.size() < poly.size() - 1 && i < 100) {

            roots = addUniqueRoot(roots, findRoot(poly, guess, accuracy, iterationLimit));
        }

        return roots;
    }





    public static List<Double> addUniqueRoot(List<Double> roots, double root) {

        if (isRootInRoots(roots, root)) {

            return roots;
        }

        else

        {

            return addRootToRoots(roots, root);
        }
    }





    public static boolean isRootInRoots(List<Double> roots, double root){

        double accuracyLimit = 0.00001;

        boolean result = false;

        for (int i=0; i<roots.size(); i++){

            result = result || Math.abs(root - roots.get(i)) < accuracyLimit;
        }

        return result;
    }







    public static List<Double> addRootToRoots (List<Double> roots, double root){

        List<Double> result = new ArrayList<>();

        for (int i=0; i<roots.size(); i++){

            result.add(roots.get(i));
        }


        result.add(root);
        return result;

    }
}
