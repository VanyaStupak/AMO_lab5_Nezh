package com.example.amo_lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    EditText x11, x12, x13, x14, x21, x22, x23, x24, x31, x32, x33, x34, val1, val2, val3, val4, x41, x42, x43,x44;
    TextView result;
    Button count;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#9C27B0")));

        x11 = findViewById(R.id.editTextX11);
        x12 = findViewById(R.id.editTextX12);
        x13 = findViewById(R.id.editTextX13);
        x14 = findViewById(R.id.editTexta);
        x21 = findViewById(R.id.editTextX21);
        x22 = findViewById(R.id.editTextX22);
        x23 = findViewById(R.id.editTextX23);
        x24 = findViewById(R.id.editTextb);
        x31 = findViewById(R.id.editTextX31);
        x32 = findViewById(R.id.editTextX32);
        x33 = findViewById(R.id.editTextX33);
        x34 = findViewById(R.id.editTextc);
        x41 = findViewById(R.id.editTextX41);
        x42 = findViewById(R.id.editTextX42);
        x43 = findViewById(R.id.editTextX43);
        x44 = findViewById(R.id.editTextd);
        val1 = findViewById(R.id.editTextFinal1);
        val2 = findViewById(R.id.editTextFinal2);
        val3 = findViewById(R.id.editTextFinal3);
        val4 = findViewById(R.id.editTextFinal4);
        result = findViewById(R.id.resultX);
        count = findViewById(R.id.button2);
        double[][] matrix = new double[4][4];
        double[] vector = new double[4];


        count.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"SetTextI18n", "DefaultLocale"})
            @Override
            public void onClick(View view) {
                try {
                    matrix[0][0] = Double.parseDouble(String.valueOf(x11.getText()));
                    matrix[0][1] = Double.parseDouble(String.valueOf(x12.getText()));
                    matrix[0][2] = Double.parseDouble(String.valueOf(x13.getText()));
                    matrix[0][3] = Double.parseDouble(String.valueOf(x14.getText()));
                    matrix[1][0] = Double.parseDouble(String.valueOf(x21.getText()));
                    matrix[1][1] = Double.parseDouble(String.valueOf(x22.getText()));
                    matrix[1][2] = Double.parseDouble(String.valueOf(x23.getText()));
                    matrix[1][3] = Double.parseDouble(String.valueOf(x24.getText()));
                    matrix[2][0] = Double.parseDouble(String.valueOf(x31.getText()));
                    matrix[2][1] = Double.parseDouble(String.valueOf(x32.getText()));
                    matrix[2][2] = Double.parseDouble(String.valueOf(x33.getText()));
                    matrix[2][3] = Double.parseDouble(String.valueOf(x34.getText()));
                    matrix[3][0] = Double.parseDouble(String.valueOf(x41.getText()));
                    matrix[3][1] = Double.parseDouble(String.valueOf(x42.getText()));
                    matrix[3][2] = Double.parseDouble(String.valueOf(x43.getText()));
                    matrix[3][3] = Double.parseDouble(String.valueOf(x44.getText()));
                    vector[0] = Double.parseDouble(String.valueOf(val1.getText()));
                    vector[1] = Double.parseDouble(String.valueOf(val2.getText()));
                    vector[2] = Double.parseDouble(String.valueOf(val3.getText()));
                    vector[3] = Double.parseDouble(String.valueOf(val4.getText()));

                    double[] res = jacobi(matrix, vector);
                    String[] stres = new String[res.length];
                    for (int i = 0; i < res.length; i++) {
                        stres[i] = String.format("%.2f", res[i]);
                        if(stres[i].equals("-0.00")){
                            stres[i] = "0.00";
                        }
                    }
                    result.setText(" X1 = " + stres[0] + "\n" + "X2 = " + stres[1] + "\n" + "X3 = " + stres[2] +"\n" + "X4 = " + stres[3]);
                } catch (NumberFormatException e) {
                    result.setText("Введіть коректні числа");
                }
            }

        });
    }


    public static double[] jacobi(double[][] A, double[] b) {
        int n = A.length;
        double[][] Ab = new double[n][n + 1]; // розширена матриця

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Ab[i][j] = A[i][j];
            }
            Ab[i][n] = b[i];
        }

        for (int k = 0; k < n - 1; k++) {
            for (int i = k + 1; i < n; i++) {
                double factor = Ab[i][k] / Ab[k][k];
                for (int j = k + 1; j < n + 1; j++) {
                    Ab[i][j] = Ab[i][j] - factor * Ab[k][j];
                }
            }
        }

        double[] x = new double[n];
        x[n - 1] = Ab[n - 1][n] / Ab[n - 1][n - 1];
        for (int i = n - 2; i >= 0; i--) {
            double sum = Ab[i][n];
            for (int j = i + 1; j < n; j++) {
                sum = sum - Ab[i][j] * x[j];
            }
            x[i] = sum / Ab[i][i];
        }

        return x;
    }

    public void variant(View v) {
        x11.setText(String.valueOf(2));
        x12.setText(String.valueOf(0));
        x13.setText(String.valueOf(-1));
        x14.setText(String.valueOf(-2));
        x21.setText(String.valueOf(0));
        x22.setText(String.valueOf(1));
        x23.setText(String.valueOf(2));
        x24.setText(String.valueOf(-1));
        x31.setText(String.valueOf(1));
        x32.setText(String.valueOf(-1));
        x33.setText(String.valueOf(0));
        x34.setText(String.valueOf(-1));
        x41.setText(String.valueOf(-1));
        x42.setText(String.valueOf(3));
        x43.setText(String.valueOf(2));
        x44.setText(String.valueOf(0));
        val1.setText(String.valueOf(-8));
        val2.setText(String.valueOf(-1));
        val3.setText(String.valueOf(-6));
        val4.setText(String.valueOf(7));
    }

}