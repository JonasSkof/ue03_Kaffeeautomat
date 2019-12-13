package at.kaindorf.ahme15.skojom15.gui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import at.kaindorf.ahme15.skojom15.R;
import at.kaindorf.ahme15.skojom15.data.Coffee;

public class CoffeeMachineActivity extends AppCompatActivity implements View.OnClickListener {

    private double amount = 0.00;


    private void addCoffee () {

        final RadioGroup radioGroup = findViewById(R.id.radioGroup);
        radioGroup.removeAllViews();
        radioGroup.setOrientation(RadioGroup.VERTICAL);
        Coffee[] coffees = Coffee.values();



        for (Coffee coffee : coffees) {
            RadioButton radioButton = new RadioButton(this);
            String s = coffee.getName() + "(" + String.format("%.0f",100* coffee.getPrice()) + "¢)";
            int textColor = Color.parseColor("white");
            radioButton.setButtonTintList(ColorStateList.valueOf(textColor));
            radioButton.setTextColor(getResources().getColor(R.color.white, null));
            radioButton.setText(s);
            radioGroup.addView(radioButton);
        }
    }


    private void buttonHandler () {

        Button bt1 = findViewById(R.id.bt1); //euro2
        bt1.setOnClickListener(this);
        Button bt2 = findViewById(R.id.bt2); //euro1
        bt2.setOnClickListener(this);
        Button bt3 = findViewById(R.id.bt3); //cent50
        bt3.setOnClickListener(this);
        Button bt4 = findViewById(R.id.bt4); //cent20
        bt4.setOnClickListener(this);
        Button bt5 = findViewById(R.id.bt5); //10
        bt5.setOnClickListener(this);
        Button bt6 = findViewById(R.id.bt6); //cent5
        bt6.setOnClickListener(this);
        Button btLeave = findViewById(R.id.btAbbruch);  // Leave Button
        btLeave.setOnClickListener(this);
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bt1: { amount += 2.00; break;}
            case R.id.bt2: { amount += 1.00; break;}
            case R.id.bt3: { amount += 0.50; break;}
            case R.id.bt4: { amount += 0.20; break;}
            case R.id.bt5: { amount += 0.10; break;}
            case R.id.bt6: { amount += 0.05; break;}
            case R.id.btAbbruch: {  amount = 0; }
        }
        TextView tvAmount = findViewById(R.id.tvAmount);
        tvAmount.setText(String.format("%.2f",amount)+ getString(R.string.currency));
    }

    public void setSugarText () {

        SeekBar seekBar = findViewById(R.id.seekBar);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                TextView textView = findViewById(R.id.tvSugar);

                switch(progress) {
                    case 0: { textView.setText(getText(R.string.sugar0))  ;}
                    case 1: { textView.setText(R.string.sugar1); break;}
                    case 2: { textView.setText(R.string.sugar2); break;}
                    case 3: { textView.setText(R.string.sugar3); break; }
                    case 4: { textView.setText(R.string.sugar4); break; }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void onCoffeeToGo () {

        Button btnext = findViewById(R.id.btNext);

        btnext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                try{
                        RadioGroup radioGroup = findViewById(R.id.radioGroup);
                        Intent intent = new Intent(CoffeeMachineActivity.this, PayBackActivity.class);
                        Coffee[] coffees = Coffee.values();
                        if(radioGroup.getCheckedRadioButtonId() == -1) {
                            printToast(getString(R.string.error1));
                        }
                        double coffeePrice = coffees[(radioGroup.getCheckedRadioButtonId())-1].getPrice();

                        if (amount >= coffeePrice) {
                            amount -= coffeePrice;
                            intent.putExtra("amount", amount);
                            TextView tvAmount = findViewById(R.id.tvAmount);
                            tvAmount.setText(String.format("%.2f", amount) + getString(R.string.currency));
                            if(amount != 0.00)
                             startActivity(intent);
                        } else {
                            printToast(getString(R.string.error2));
                        }


                } catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void printToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addCoffee();
        onCoffeeToGo();
        buttonHandler();
        setSugarText();

        if(savedInstanceState!= null) {
            amount = savedInstanceState.getDouble("amount");
            TextView tvAmount = findViewById(R.id.tvAmount);
            tvAmount.setText(String.format("%.2f", amount) + getString(R.string.currency));
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble("amount", amount);

    }

}
