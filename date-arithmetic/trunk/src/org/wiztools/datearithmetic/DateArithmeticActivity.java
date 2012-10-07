package org.wiztools.datearithmetic;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateArithmeticActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    final private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.date_unit_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = (Spinner) findViewById(R.id.unit);
        spinner.setAdapter(adapter);
    }

    private int getCalendarUnit(String unit) {
        if("Day".equals(unit)) {
            return Calendar.DATE;
        }
        else if("Month".equals(unit)) {
            return Calendar.MONTH;
        }
        else {
            return Calendar.YEAR;
        }
    }

    public void compute(View view) {

        EditText txtAmt = (EditText) findViewById(R.id.amount);
        final String amtStr = txtAmt.getText().toString();
        if(amtStr.trim().equals("")) {
            Toast.makeText(this, "Value empty!", Toast.LENGTH_SHORT).show();
            txtAmt.requestFocus();
            return;
        }
        final int amt = Integer.parseInt(amtStr);

        DatePicker dt = (DatePicker) findViewById(R.id.dt);

        // Set Calendar Date:
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, dt.getYear());
        cal.set(Calendar.MONTH, dt.getMonth());
        cal.set(Calendar.DAY_OF_MONTH, dt.getDayOfMonth());

        // Find unit of operation:
        Spinner spUnit = (Spinner) findViewById(R.id.unit);
        final String unitStr = (String) spUnit.getSelectedItem();
        final int unit = getCalendarUnit(unitStr);

        // Find if plus or minus operation:
        RadioButton rbPlus = (RadioButton) findViewById(R.id.plus);
        if(rbPlus.isChecked()) {
            cal.add(unit, amt);
        }
        else {
            cal.add(unit, ((-1)*amt));
        }

        // Set the result:
        TextView txtOut = (TextView) findViewById(R.id.out);
        txtOut.setText(DATE_FORMAT.format(cal.getTime()));
    }
}
