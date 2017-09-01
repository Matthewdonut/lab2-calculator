package com.example.a1536381.lab2_calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText etLoan, etTerm, etInterest;
    TextView tvMonthlyPayment, tvTotalPayment, tvTotalInterest;
    double loan, interest, monthlyPayment, totalPayment, totalInterest;
    int term;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etLoan = (EditText) findViewById(R.id.loanAmount);
        etTerm = (EditText) findViewById(R.id.term);
        etInterest = (EditText) findViewById(R.id.interestRate);
        tvMonthlyPayment = (TextView) findViewById(R.id.monthlyPayment);
        tvTotalPayment = (TextView) findViewById(R.id.totalPayment);
        tvTotalInterest = (TextView) findViewById(R.id.totalInterest);
    }

    public double getMonthlyPayment ()
    {
        double monthlyPayment;
        double monthlyInterestRate;
        int numberOfPayments;
        if (term != 0 && interest != 0)
        {
            //calculate the monthly payment
            monthlyInterestRate = interest / 1200;
            numberOfPayments = term * 12;

            monthlyPayment =
                    (loan * monthlyInterestRate) /
                            (1 - (1 / Math.pow ((1 + monthlyInterestRate), numberOfPayments)));

            monthlyPayment = Math.round(monthlyPayment * 100) / 100.0;
        }
        else
            monthlyPayment = 0;
        return monthlyPayment;
    }


    public double getTotalPayment()
    {
        return getMonthlyPayment () * term * 12;
    }


    public double getTotalInterest ()
    {
        return getTotalPayment() - loan;
    }

    public void calculate(View v) {
        loan = Double.parseDouble(etLoan.getText().toString());
        term = Integer.parseInt(etTerm.getText().toString());
        interest = Double.parseDouble(etInterest.getText().toString());

        tvMonthlyPayment.append("\t" + Double.toString(getMonthlyPayment()));
        tvTotalPayment.append("\t" + Double.toString(getTotalPayment()));
        tvTotalInterest.append("\t" + Double.toString(getTotalInterest()));
    }

    public void clear(View v) {
        etLoan.setText("");
        etTerm.setText("");
        etInterest.setText("");
        tvMonthlyPayment.setText("");
        tvTotalPayment.setText("");
        tvTotalInterest.setText("");
    }
}
