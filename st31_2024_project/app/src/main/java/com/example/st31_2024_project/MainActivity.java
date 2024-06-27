package com.example.st31_2024_project;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class MainActivity extends AppCompatActivity
//        implements View.OnClickListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView strView = findViewById(R.id.textV);
        strView.setText("はろー");

        //    匿名クラスを使った方法
        Button button;
        button = findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edt = findViewById(R.id.edText);
                Editable ed = edt.getText();
                String strName = ed.toString();

                TextView strView = findViewById(R.id.textV);
                strView.setText(strName);

//                課題No.2

                EditText editText = findViewById(R.id.edText2);
                String strText2 = editText.getText().toString();
                Integer intText = 0;
                Integer intText2 = 0;

                boolean flg = true;
                try {
                    intText2 = Integer.valueOf(strText2);
                    intText = Integer.valueOf(strName);

                } catch (NumberFormatException e){
                    Log.w("st31_2024_r01", "数値以外の入力");
                    flg = !flg;
                } catch (Exception e){
                    Log.e("st31_2024_r01", Objects.requireNonNull(e.getMessage()));
                    flg = !flg;
                }
//                TextView strView = findViewById(R.id.textV);
                String strMessage = "入力エリアに文字あり";
                if(flg){
                    Integer intTotal = 0;
                    intTotal = intText + intText2;
                    strMessage = intTotal.toString();
//                    strView.setText(intTotal);
                }else {
                    strMessage = "入力エリアに文字あり";
                }
                strView.setText(strMessage);

            }

        });
        //    BMI計算ボタンを押したとき
        Button buttonBmi = findViewById(R.id.btnBmi);
        buttonBmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                BMI計算
                EditText editText2 = findViewById(R.id.edText);
                String strHeight = editText2.getText().toString();
                EditText editText = findViewById(R.id.edText2);
                String strWeight = editText.getText().toString();

//                数値変換
                Double dbHeight = 0.0;
                Double dbWeight = 0.0;
                String strMessage = "入力エリアに文字あり";
                boolean flg = true;
                String judge = "";
                try {
                    Double dblHeight = Double.valueOf(strHeight);
                    Double dblWeight = Double.valueOf(strWeight);
                    Double dblBmi = dblWeight / (dblHeight * dblHeight / 10000);
//                    str使った方法
//                    String strBmi = String.format("%.2f", dblBmi);
//                    小数点丸め
                    dblBmi = Math.round(dblBmi * 100.0) / 100.0;

                    if(dblBmi < 18.5){
                        judge = "低体重(痩せ型)";
                    }else if(dblBmi < 25){
                        judge = "標準";
                    }else if(dblBmi < 30){
                        judge = "肥満(1度)";
                    }else if(dblBmi < 35){
                        judge = "肥満(2度)";
                    }else if(dblBmi < 40){
                        judge = "肥満(3度)";
                    }else {
                        judge = "肥満(4度)";
                    }
                    strMessage = dblBmi.toString();
                } catch (NumberFormatException e){
                    Log.w("st31_2024_r01", "数値以外の入力");
                    flg = !flg;
                } catch (Exception e){
                    Log.e("st31_2024_r01", Objects.requireNonNull(e.getMessage()));
                    flg = !flg;
                }

                if(!flg){
                    strMessage = "入力エリアに文字あり";
                }
                strView.setText("BMI結果:"+strMessage+"\n判定:"+judge);


            }
        });
    }




//    方法2
//




    
//    public void btnClick(View v) {
//        EditText edt = findViewById(R.id.edText);
//        Editable ed = edt.getText();
//        String strName = ed.toString();
//
//        TextView strView = findViewById(R.id.textV);
//        strView.setText(strName);
//    }

}