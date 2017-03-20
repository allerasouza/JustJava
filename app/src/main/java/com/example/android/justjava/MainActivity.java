package com.example.android.justjava;
/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
 * package com.example.android.justjava;
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
//import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity;
    //int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quantity = Integer.valueOf((((TextView) findViewById(R.id.quantity_text_view)).getText().toString()));
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();
        String priceMessage = createOrderSummary(price);
        //displayMessage(priceMessage);

        /*Intent intent = new Intent(Intent.ACTION_SET_ALARM);
        intent.setData(Uri.parse("geo:47.6, -122.3"));
        if(intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);*/

        /*Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
        intent.putExtra(AlarmClock.EXTRA_MESSAGE, "AAS");
        intent.putExtra(AlarmClock.EXTRA_HOUR, 20);
        intent.putExtra(AlarmClock.EXTRA_MINUTES, 10);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }//<uses-permission android:name="com.android.alarm.permission.SET_ALARM" /> //AndroidManifest.xml
        */
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        //intent.putExtra(Intent.EXTRA_SUBJECT, "JustJava order for " + ((EditText) findViewById(R.id.name_field)).getText().toString());
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.order_summary_name, ((EditText) findViewById(R.id.name_field)).getText().toString()));
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     *
     * @param totalPrice Total price
     * @return Returns Order Summary
     */
    private String createOrderSummary(int totalPrice) {
        String priceMessage = getString(R.string.order_summary_name, (((EditText) findViewById(R.id.name_field)).getText().toString())) + "\n";
        priceMessage += getString(R.string.order_summary_whipped_cream, ((CheckBox) findViewById(R.id.whipped_cream_checkbox)).isChecked()) + "\n";
        priceMessage += getString(R.string.order_summary_chocolate, ((CheckBox) findViewById(R.id.chocolate_checkbox)).isChecked()) + "\n";
        priceMessage += getString(R.string.order_summary_quantity, quantity) + "\n";
        priceMessage += getString(R.string.order_summary_price, NumberFormat.getCurrencyInstance().format(totalPrice)) + "\n";
        priceMessage += getString(R.string.thank_you);
        return priceMessage;
    }

    /**
     * Calculates the price of the order.
     *
     * @return total price
     */
    private int calculatePrice() {
        int basePrice = 5;
        if(((CheckBox) findViewById(R.id.chocolate_checkbox)).isChecked())
            basePrice += 2;
        if(((CheckBox) findViewById(R.id.whipped_cream_checkbox)).isChecked())
            basePrice += 1;
        return quantity * basePrice;
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        quantity = quantity + 1;
        if (quantity > 100) {
            quantity = 100;
            Toast.makeText(this, "You cannot have more than " + quantity + " coffees", Toast.LENGTH_SHORT).show();
        }
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {

        quantity = quantity - 1;
        if (quantity < 1) {
            quantity = 1;
            Toast.makeText(this, "You cannot have less than " + quantity + " coffee", Toast.LENGTH_SHORT).show();
        }
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen
     */

    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }


}

