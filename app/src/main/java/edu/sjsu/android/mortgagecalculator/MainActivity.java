package edu.sjsu.android.mortgagecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Float.parseFloat;

public class MainActivity extends AppCompatActivity {
    private EditText borrowed;
    private SeekBar seekBar;
    private TextView interest;
    private TextView percentage;
    private TextView term;
    private Button calculate;
    private RadioGroup radioGrp;
    private RadioButton radiobtn;
    private int progress_value;
    private float radio, taxes;
    private CheckBox chkBox;
    private float monthly;
    private TextView payment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekBarID);
        percentage = findViewById(R.id.percentID);
        interest = findViewById(R.id.interestID);
        term = findViewById(R.id.termID);
        borrowed = findViewById(R.id.editTextID);
        payment = findViewById(R.id.paymentID);
        radioGrp = findViewById(R.id.radioGroupID);
        chkBox = findViewById(R.id.checkBox);

        percentage.setText("10%");

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progress_value = i;

                seekBar.setProgress(i);
                percentage.setText(seekBar.getProgress() + "%"); //Initial getProgress is zero.

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        calculate = findViewById(R.id.buttonID);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               float borrowedAmount = parseFloat(borrowed.getText().toString()); //
               //payment.setText(borrowedAmount); //borrowed

                int radioID =  radioGrp.getCheckedRadioButtonId();
                radiobtn = findViewById(radioID);
                radio = parseFloat(radiobtn.getText().toString() ); //radio button data

               // payment.setText(radio+ " ");
                if(payment.getText().length() == 0){ //if no input
                    Toast.makeText(MainActivity.this, "Entered number was not valid", Toast.LENGTH_LONG).show();
                    return;
                }
                if(chkBox.isChecked()){
                    taxes = (borrowedAmount / 1000); //0.1 percent of borrowed

                }

                monthly = (borrowedAmount / radio) + taxes;
                payment.setText("Monthly payment is: " + monthly);


            }


        });

    }
}