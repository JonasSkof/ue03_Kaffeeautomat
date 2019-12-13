package at.kaindorf.ahme15.skojom15.data;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

import at.kaindorf.ahme15.skojom15.R;
import at.kaindorf.ahme15.skojom15.gui.CoffeeMachineActivity;

public class RestgeldRechner extends AppCompatActivity {

    Bundle bundle = getIntent().getExtras();
    double amount;
    double[] amountArray;

    public void onRechnen() {
        try {

            if (bundle != null)
                amount =  bundle.getDouble("amount");

             for(int i = (int)amount; i > 2.00; i--) {
                 bundle.putDouble("amount2euro", amountArray[i]);
                 System.out.println("***"+ amountArray[i]);
             }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
