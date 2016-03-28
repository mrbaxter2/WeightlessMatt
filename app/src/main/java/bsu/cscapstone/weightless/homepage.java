package bsu.cscapstone.weightless;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class homepage extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        Button relax = (Button)findViewById(R.id.bRelax);
        Button fitbit = (Button)findViewById(R.id.bFitBit);
        Button about = (Button)findViewById(R.id.bAbout);
        Button tutorial = (Button)findViewById(R.id.bTutorial);

        //March 17th
        relax.setOnClickListener(this);
        fitbit.setOnClickListener(this);
        about.setOnClickListener(this);
        tutorial.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //March 18th
        switch(v.getId()){
            case R.id.bRelax:
                Intent relax = new Intent(this, relax.class);
                startActivity(relax);
                break;
            case R.id.bFitBit:
                Intent fitbit = new Intent(this, fitbit.class);
                startActivity(fitbit);
                break;
            case R.id.bAbout:
                Intent about = new Intent(this, about.class);
                startActivity(about);
                break;
            case R.id.bTutorial:
                Intent tutorial = new Intent(this, tutorial.class);
                startActivity(tutorial);
                break;

        }
    }
}
