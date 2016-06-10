package com.fazlamesai.bilgiandroiddemoone;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

  TextView textViewPrompt;
  TextView textViewPromptTwo;
  TextView textViewPromptThree;
  EditText editTextValueOne;
  EditText editTextValueTwo;
  EditText editTextValueThree;
  EditText editTextValueFour;
  Button buttonDoSomething;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    textViewPrompt = (TextView) findViewById(R.id.text_view_promptOne);
    textViewPromptTwo = (TextView) findViewById(R.id.text_view_promptTwo);
    textViewPromptThree = (TextView) findViewById(R.id.text_view_promptThree);
    editTextValueOne = (EditText) findViewById(R.id.edit_text_value_one);
    editTextValueTwo = (EditText) findViewById(R.id.edit_text_value_two);
    editTextValueThree = (EditText) findViewById(R.id.edit_text_value_three);
    editTextValueFour = (EditText) findViewById(R.id.edit_text_value_four);
    buttonDoSomething = (Button) findViewById(R.id.button_do_something);
    buttonDoSomething.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String valueOne = editTextValueOne.getText().toString().trim();
        String valueTwo = editTextValueTwo.getText().toString().trim();
        String valueThree = editTextValueThree.getText().toString().trim();
        String valueFour = editTextValueFour.getText().toString().trim();
        int intValueOne = Integer.parseInt(valueOne);
        int intValueTwo = Integer.parseInt(valueTwo);
        int intValueThree = Integer.parseInt(valueThree);
        int intValueFour = Integer.parseInt(valueFour);

        int result = doSomething(intValueOne, intValueTwo);
        textViewPrompt.setText(String.valueOf(result));
        int result2 = doSomething2(intValueThree, intValueFour);
        textViewPromptTwo.setText(String.valueOf(result2));
        int result3 = doSomething3(intValueOne, intValueTwo, intValueThree, intValueFour);
        textViewPromptThree.setText(String.valueOf(result3));


      }
    });
  }

  private static int doSomething(int valueOne, int valueTwo) {
    return (valueOne * valueOne * valueOne) + (valueTwo * valueTwo * valueTwo);}
  private static int doSomething2(int valueThree, int valueFour) {
    return (valueThree * valueThree * valueThree) + (valueFour * valueFour * valueFour);
    }
  private static int doSomething3(int valueOne, int valueTwo, int valueThree, int valueFour){
    return (((valueOne * valueOne * valueOne) + (valueTwo * valueTwo * valueTwo)) -
            ((valueThree * valueThree * valueThree) + ((valueFour * valueFour * valueFour))));}
  }
