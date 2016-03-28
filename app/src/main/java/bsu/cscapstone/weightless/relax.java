package bsu.cscapstone.weightless;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

public class relax extends AppCompatActivity implements View.OnClickListener{

    Spinner time, order;
    CheckBox sleep, cb;
    Button start;
    double timeDouble;
    String timeString;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relax);
        time = (Spinner)findViewById(R.id.spinTime);
        order = (Spinner)findViewById(R.id.spinOrder);
        sleep = (CheckBox)findViewById(R.id.cbSleep);
        start = (Button)findViewById(R.id.bStart);
        cb = (CheckBox)findViewById(R.id.cbPrompts);
        start.setOnClickListener(this);

        ArrayAdapter<CharSequence> times = ArrayAdapter.createFromResource(this, R.array.times, android.R.layout.simple_spinner_item);
        times.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        time.setAdapter(times);

        ArrayAdapter<CharSequence> orders = ArrayAdapter.createFromResource(this, R.array.order, android.R.layout.simple_spinner_item);
        orders.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        order.setAdapter(orders);



    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bStart:
                Intent audioPlayer = new Intent(this, audioplayer.class);
                timeString = time.getSelectedItem().toString();
                try {
                    timeDouble = Double.parseDouble(timeString);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                String orderString = order.getSelectedItem().toString();
                boolean sleepBool = sleep.isChecked();
                Bundle bundle = new Bundle();
                bundle.putDouble("time", timeDouble);
                bundle.putString("order", orderString);
                bundle.putBoolean("sleep", sleepBool);
                bundle.putBoolean("prompts", cb.isChecked()); //get prompt for minimal or not
                audioPlayer.putExtras(bundle);
                if (audioPlayer.resolveActivity(getPackageManager()) != null) {
                    startActivity(audioPlayer);
                }
                break;
        }
    }
}
