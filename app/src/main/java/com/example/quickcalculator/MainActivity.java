package com.example.quickcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView rsltTv,solutionTv;
    MaterialButton buttonC,buttonBrackOpen,buttonBrackClose;
    MaterialButton buttonDivide,buttonMultiply,buttonPlus,buttonMinus,buttonEquals;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAC,buttonDot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rsltTv = findViewById(R.id.rslt_tv);
        solutionTv = findViewById(R.id.solution_tv);

        assignId(buttonC,R.id.button_c);
        assignId(buttonBrackOpen,R.id.button_open_bracket);
        assignId(buttonBrackClose,R.id.button_close_bracket);
        assignId(buttonDivide,R.id.button_divide);
        assignId(buttonMultiply,R.id.button_multiply);
        assignId(buttonPlus,R.id.button_plus);
        assignId(buttonMinus,R.id.button_minus);
        assignId(buttonEquals,R.id.button_equals);
        assignId(button0,R.id.b0);
        assignId(button1,R.id.b1);
        assignId(button2,R.id.b2);
        assignId(button3,R.id.b3);
        assignId(button4,R.id.b4);
        assignId(button5,R.id.b5);
        assignId(button6,R.id.b6);
        assignId(button7,R.id.b7);
        assignId(button8,R.id.b8);
        assignId(button9,R.id.b9);
        assignId(buttonAC,R.id.bac);
        assignId(buttonDot,R.id.bdot);





    }

    void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        MaterialButton button =(MaterialButton) view;
        String buttonText = button.getText().toString();
        String toCal = solutionTv.getText().toString();

        if(buttonText.equals("AC")){
            solutionTv.setText("");
            rsltTv.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solutionTv.setText(rsltTv.getText());
            return;
        }
        if(buttonText.equals("C")){
            toCal = toCal.substring(0,toCal.length()-1);
        }else{
            toCal = toCal+buttonText;
        }
        solutionTv.setText(toCal);

        String frslt = getrslt(toCal);

        if(!frslt.equals("Err")){
            rsltTv.setText(frslt);
        }

    }

    String getrslt(String data){
        try{
            Context context  = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String frslt =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(frslt.endsWith(".0")){
                frslt = frslt.replace(".0","");
            }
            return frslt;
        }catch (Exception e){
            return "Err";
        }
    }

}