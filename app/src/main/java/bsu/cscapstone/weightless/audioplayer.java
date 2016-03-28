package bsu.cscapstone.weightless;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.lang.reflect.Array;

public class audioplayer extends AppCompatActivity implements View.OnClickListener {



    String topToBottom = "topToBottom";
    String bottomToTop = "bottomToTop";
    String orderString, sessionLengthSeconds;
    MediaPlayer mediaPlayer;
    long time, millisTime;
    Boolean sleep, min;
    TextView remaining, progressed;
    Button pause, seekLeft, seekRight;
    int [] promptHeadtoToeMin = {R.raw.nofalling2,R.raw.eyes2,R.raw.face2,R.raw.mouth2,R.raw.neck2,R.raw.shoulders2,R.raw.upperarm2,R.raw.forearm2,R.raw.hands2,R.raw.chest2,R.raw.legs2,R.raw.hipsthighs2,R.raw.lowerleg2,R.raw.feet2};
    int [] promptToetoHeadMin = {R.raw.nofalling2,R.raw.feet2,R.raw.lowerleg2,R.raw.hipsthighs2,R.raw.legs2,R.raw.chest2,R.raw.hands2,R.raw.forearm2,R.raw.upperarm2,R.raw.shoulders2,R.raw.neck2,R.raw.mouth2,R.raw.face2,R.raw.eyes2};
    int [] promptHeadtoToe = {R.raw.nofalling1,R.raw.nofalling2,R.raw.eyes1,R.raw.eyes2,R.raw.face1,R.raw.face2,R.raw.mouth1,R.raw.mouth2,R.raw.neck1,R.raw.neck2,R.raw.shoulders1,R.raw.shoulders2,R.raw.upperarm1,R.raw.upperarm2,R.raw.forearm1,R.raw.forearm2,R.raw.hands1,R.raw.hands2,R.raw.chest1,R.raw.chest2,R.raw.legs1,R.raw.legs2,R.raw.hipsthighs1,R.raw.hipsthighs2,R.raw.lowerleg1,R.raw.lowerleg2,R.raw.feet1,R.raw.feet2};
    int [] promptToetoHead = {R.raw.nofalling1,R.raw.nofalling2,R.raw.feet1,R.raw.feet2,R.raw.lowerleg1,R.raw.lowerleg2,R.raw.hipsthighs1,R.raw.hipsthighs2,R.raw.legs1,R.raw.legs2,R.raw.chest1,R.raw.chest2,R.raw.hands1,R.raw.hands2,R.raw.forearm1,R.raw.forearm2,R.raw.upperarm1,R.raw.upperarm2,R.raw.shoulders1,R.raw.shoulders2,R.raw.neck1,R.raw.neck2,R.raw.mouth1,R.raw.mouth2,R.raw.face1,R.raw.face2,R.raw.eyes1,R.raw.eyes2};
    int [] beginning = {R.raw.liedown,R.raw.breathe};  //will have silence between liedown and breathe

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audioplayer);




        pause = (Button)findViewById(R.id.bPause);
        seekLeft = (Button)findViewById(R.id.bSeekLeft);
        seekRight = (Button)findViewById(R.id.bSeekRight);
        TextView order = (TextView)findViewById(R.id.tvOrder);
        remaining = (TextView)findViewById(R.id.tvRemaining);
        progressed = (TextView)findViewById(R.id.tvProgressed);
        ImageView background = (ImageView)findViewById(R.id.iVBackground);
        sessionLengthSeconds = remaining.getText().toString();
        pause.setOnClickListener(this);
        seekLeft.setOnClickListener(this);
        seekRight.setOnClickListener(this);
                                                        //Set media player object.track info to the textview


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(!bundle.isEmpty()) {
            boolean hasOrder = bundle.containsKey("order");
            boolean hasTime = bundle.containsKey("time");
            boolean hasSleep = bundle.containsKey("sleep");
            //Get Bundles from the intents
            orderString = bundle.getString("order");
            time = bundle.getLong("time");
            millisTime = time * 60000;
            sleep = bundle.getBoolean("sleep");
            min = bundle.getBoolean("prompts");
           // remaining.setText((Double)time.toString());

            if(orderString == topToBottom ){
                playTopToBottom();
            }

            if(orderString == bottomToTop ){
                playBottomToTop();

            }


        }





    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.bPause:                       //Pause media player
                pause();
                break;
            case R.id.bSeekLeft:                    //Will go back to the last prompt

                break;
            case R.id.bSeekRight:                   //Will go to the next prompt

                break;
        }
    }


    public void pause(){
                      //Pause put into method so that we can edit lengths of pauses and silences
    }

    public void playTopToBottom() {
        long waitLength = millisTime/promptHeadtoToeMin.length;
        for(int i = 0; i<3; i++){
            mediaPlayer = MediaPlayer.create(this, beginning[i]);
            mediaPlayer.start();
            mediaPlayer.release();
        }

        if(min == true){

            for(int i = 0; i < promptHeadtoToeMin.length; i++){
                mediaPlayer = MediaPlayer.create(this, promptHeadtoToeMin[i]);
                mediaPlayer.start();
                try {
                    wait(waitLength);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mediaPlayer.release();

            }
        } else{
            for(int i = 0; i < promptHeadtoToe.length; i=i+2){
                mediaPlayer = MediaPlayer.create(this, promptHeadtoToe[i]);     //idle state
                mediaPlayer.start();                                            //start state
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        try {
                            wait(3000);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                });
                mediaPlayer.release();

                mediaPlayer = MediaPlayer.create(this, promptHeadtoToe[i++]);
                mediaPlayer.start();
                try {
                    wait(waitLength);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                                                    //stopped state
                mediaPlayer.release();              //released state


            }
        }


    }

    public void playBottomToTop(){

    }




}
