package com.example.st31_2024_r04_intent;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {

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

        Button btn = findViewById(R.id.btnMain);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                EditText edMain = findViewById(R.id.edTextMain);
                EditText edMain2 = findViewById(R.id.edTextMain2);
                String strHeight = edMain.getText().toString();
                String strWeight = edMain2.getText().toString();
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
                intent.putExtra("data1", "BMI結果:"+strMessage+"\n判定:"+judge);

                startActivity(intent);
            }
        });
    }

}