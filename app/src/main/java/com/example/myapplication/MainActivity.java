package com.example.myapplication;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultView,solutionView;
    Button buttonC,buttonOpenBracket,getButtonCloseBracket;
    Button buttonDivide,buttonMultiply,buttonPlus,buttonMinus,buttonEquals;
    Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;
    Button buttonAC,buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultView = findViewById(R.id.result_view);
        solutionView = findViewById(R.id.solution_view);

        assignID(buttonC,R.id.button_clear);
        assignID(buttonOpenBracket,R.id.button_open_bracket);
        assignID(getButtonCloseBracket,R.id.button_close_bracket);

        assignID(buttonDivide,R.id.button_division);
        assignID(buttonMultiply,R.id.button_multiplication);
        assignID(buttonPlus,R.id.button_plus);
        assignID(buttonMinus,R.id.button_minus);
        assignID(buttonEquals,R.id.button_equal);

        assignID(btn0,R.id.button_0);
        assignID(btn1,R.id.button_1);
        assignID(btn2,R.id.button_2);
        assignID(btn3,R.id.button_3);
        assignID(btn4,R.id.button_4);
        assignID(btn5,R.id.button_5);
        assignID(btn6,R.id.button_6);
        assignID(btn7,R.id.button_7);
        assignID(btn8,R.id.button_8);
        assignID(btn9,R.id.button_9);

        assignID(buttonAC,R.id.button_all_clear);
        assignID(buttonDot,R.id.button_dot);

    }

    void assignID(Button btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View view) {
        solutionView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,50);
        resultView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,35);
        solutionView.setTextColor(Color.parseColor("#ffffff"));
        resultView.setTextColor(Color.parseColor("#9A9A9A"));

        Button button = (Button) view;
        String buttonText = button.getText().toString();

        if(solutionView.getText().toString().equals("0") && !buttonText.equals("=") ){
            solutionView.setText("");
        }

        String dataToCalculate = solutionView.getText().toString();

        if(buttonText.equals("AC")){
            solutionView.setText("0");
            resultView.setText("");
            resultView.setVisibility(view.GONE);
            return;
        }

        if(buttonText.equals("=")){
           if(!dataToCalculate.equals("0")){
               solutionView.setText(resultView.getText());
               solutionView.setTextColor(Color.parseColor("#9A9A9A"));
               resultView.setTextColor(Color.parseColor("#ffffff"));
               solutionView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,35);
               resultView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,50);
           }
            return;

        }

        if(buttonText.equals("C")){
           if(dataToCalculate.length() != 0){
               dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length() - 1);
           }
            if(dataToCalculate.length() == 0){
                solutionView.setText("0");
                resultView.setText("");
                resultView.setVisibility(View.GONE);
                return;
            }
        }else {
            dataToCalculate = dataToCalculate + buttonText;
            resultView.setVisibility(View.VISIBLE);
        }



        solutionView.setText(dataToCalculate);
        String finalResult = getResult(dataToCalculate);
        if(!finalResult.equals("Err") && dataToCalculate.length() != 0){
            resultView.setText(finalResult);
        }
    }

    String getResult(String data) {
        String result = MathExpressionParser.evaluateExpression(data);
        if (result.endsWith(".0")) {
            result = result.replace(".0", "");
        }
        return result;
    }
}