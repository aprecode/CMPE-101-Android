package com.fazlamesai.bilgiandroiddemoone;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static android.view.View.OnClickListener;


public class MainActivity extends Activity {
    ArrayList<Integer> bandlist = new ArrayList<Integer>();
    ArrayList<Integer> band2list = new ArrayList<Integer>();
    ArrayList<String> band3list = new ArrayList<String>();

    ArrayList<Integer> taxlist = new ArrayList<Integer>();
    ArrayList<Integer> tax2list = new ArrayList<Integer>();
    ArrayList<Integer> tax3list = new ArrayList<Integer>();

    ArrayList<Double> band_array = new ArrayList<Double>();
    ArrayList<Double> tax_array = new ArrayList<Double>();

    double band1;
    double band2;
    double tax1;
    double tax2;
    double tax3;
    


    Button btc;
    Button calculate;


    EditText salary;
    TextView nett_salary_tw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        btc = (Button) findViewById(R.id.band_tax_choice);
        btc.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                populatebtSelection();


            }
        });

        calculate = (Button) findViewById(R.id.calculate_salary);
        calculate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                salary = (EditText) findViewById(R.id.salaryet);
                if(salary.getText().toString().compareTo("")!=0) {
                    if (band_array.isEmpty() || tax_array.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Please choose band and text values first", Toast.LENGTH_LONG).show();
                    } else {


                        double taken_salary = Double.parseDouble(salary.getText().toString());
                        double nett_salary = calculate_nett_salary(band_array.get(0), band_array.get(1), tax_array.get(0),
                                tax_array.get(1), tax_array.get(2), taken_salary);
                        nett_salary_tw = (TextView) findViewById(R.id.nett_salary);
                        nett_salary_tw.setText("" + nett_salary);

                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Please enter salary", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void populatebtSelection() {

        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.btclayout);
        dialog.setTitle("Band and Tax Selection");
        dialog.show();

        band_array.clear();
        tax_array.clear();


        bandlist = populatebandList();
        final Spinner bandspinner = (Spinner) dialog.findViewById(R.id.band);
        ArrayAdapter<Integer> bandsadap = new ArrayAdapter<Integer>(MainActivity.this, android.R.layout.simple_list_item_1,bandlist);
        bandspinner.setAdapter(bandsadap);

        band2list = populatebandList();
        final Spinner band2spinner = (Spinner) dialog.findViewById(R.id.band2);
        ArrayAdapter<Integer> bands2adap = new ArrayAdapter<Integer>(MainActivity.this, android.R.layout.simple_list_item_1,band2list);
        band2spinner.setAdapter(bands2adap);

        band3list.add("Rest");
        final Spinner band3spinner = (Spinner) dialog.findViewById(R.id.band3);
        ArrayAdapter<String> bands3adap = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,band3list);
        band3spinner.setAdapter(bands3adap);



        taxlist = populatetaxlist();
        final Spinner taxspinner = (Spinner) dialog.findViewById(R.id.tax);
        ArrayAdapter<Integer> taxadap = new ArrayAdapter<Integer>(MainActivity.this, android.R.layout.simple_list_item_1, taxlist);
        taxspinner.setAdapter(taxadap);

        tax2list = populatetaxlist();
        final Spinner tax2spinner = (Spinner) dialog.findViewById(R.id.tax2);
        ArrayAdapter<Integer> tax2adap = new ArrayAdapter<Integer>(MainActivity.this, android.R.layout.simple_list_item_1, tax2list);
        tax2spinner.setAdapter(tax2adap);

        tax3list = populatetaxlist();
        final Spinner tax3spinner = (Spinner) dialog.findViewById(R.id.tax3);
        ArrayAdapter<Integer> tax3adap = new ArrayAdapter<Integer>(MainActivity.this, android.R.layout.simple_list_item_1, tax3list);
        tax3spinner.setAdapter(tax3adap);


        Button btb = (Button) dialog.findViewById(R.id.btb);
        btb.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                band1 = Double.parseDouble(bandspinner.getSelectedItem().toString());
                band2 = Double.parseDouble(band2spinner.getSelectedItem().toString());

                tax1 = Double.parseDouble(taxspinner.getSelectedItem().toString());
                tax2 = Double.parseDouble(tax2spinner.getSelectedItem().toString());
                tax3 = Double.parseDouble(tax3spinner.getSelectedItem().toString());


                if (band2 <= band1){
                    Toast.makeText(getApplicationContext(), "Second band can't be lower than previous one", Toast.LENGTH_LONG).show();
                }
                else if (tax2<=tax1||tax3<=tax2)
                         {
                    Toast.makeText(getApplicationContext(), "Second and Third band can't be lower than previous ones", Toast.LENGTH_LONG).show();

                }
                else{
                    band_array.add(band1);
                    band_array.add(band2);
                    tax_array.add(tax1);
                    tax_array.add(tax2);
                    tax_array.add(tax3);
                    Toast.makeText(getApplicationContext(),band_array.get(0)+" "+band_array.get(1)+" "+tax_array.get(0)+" "+tax_array.get(1)+" "+tax_array.get(2),Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
            }
        });
    }

    private ArrayList<Integer> populatetaxlist() {
        ArrayList<Integer> taxes = new ArrayList<Integer>();

        for(int i = 0 ; i <= 100 ; i+=10){
            taxes.add(i);

        }
        return taxes;
    }

    private ArrayList<Integer> populatebandList() {

        ArrayList<Integer> bands = new ArrayList<Integer>();

        for(int i = 500; i<=5000; i+=500){
            bands.add(i);
        }
        return bands;
    }


    private static double calculate_nett_salary(double TopOfTaxFree, double TopOfLowRate, double LowRate, double MiddleRate, double HighRate, double Salary) {
        if (Salary <= TopOfTaxFree) {

            return Salary - Salary * LowRate/100;

        } else if (Salary > TopOfTaxFree && Salary <= TopOfLowRate) {

            return Salary - TopOfTaxFree* LowRate / 100 - (Salary - TopOfTaxFree) * MiddleRate/100 ;

        } else if (Salary > TopOfLowRate) {

            return Salary - TopOfTaxFree * LowRate/100 - (TopOfLowRate - TopOfTaxFree) * MiddleRate / 100 - (Salary - TopOfLowRate) * HighRate / 100;

        } else {
            return 0;
        }
    }

}

