package com.iamafridi.afridi.mathexpert;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton,button0,button1,button2,button3,PlayAgainbt;
    TextView tv1 ,tv2,resulttv,sumtv,markstv,timerTV;
    ArrayList<Integer> answers =new ArrayList<Integer>();
    int correctAnsLocation;
    int score=0;
    int numofQuestions=0;
    RelativeLayout gamemainlayout;


    public void playAgain(View view){
        score=0;

        numofQuestions=0;
        timerTV.setText("30s");
        markstv.setText("0/0");
        resulttv.setText("");

        PlayAgainbt.setVisibility(View.INVISIBLE);
        generateNewQs();

        button0.setClickable(true);
        button1.setClickable(true);
        button2.setClickable(true);
        button3.setClickable(true);

        new CountDownTimer(30000,1000){

            @Override
            public void onTick(long l) {

                timerTV.setText(String.valueOf(l/1000)+"s");

            }

            @Override
            public void onFinish() {
                PlayAgainbt.setVisibility(View.VISIBLE);
                timerTV.setText("0s");

                button0.setClickable(false);
                button1.setClickable(false);
                button2.setClickable(false);
                button3.setClickable(false);


                resulttv.setText("Your Score is " + Integer.toString(score)+"/"+Integer.toString(numofQuestions));


            }
        }.start();

    }
    public void generateNewQs(){
        Random rand =new Random();

        int n1 = rand.nextInt(21);
        int n2=  rand.nextInt(21);

        sumtv.setText(Integer.toString(n1)+ " + " +Integer.toString(n2));

        correctAnsLocation=rand.nextInt(4);
        answers.clear();
        int wrongAns;

        for (int i=0;i<4;i++){

            if (i==correctAnsLocation){

                answers.add(n1+n2);

            }else {

                wrongAns=rand.nextInt(41);

                while (wrongAns==n1+n2){

                    wrongAns=rand.nextInt(41);
                }
                answers.add(wrongAns);
            }
        }//end of for loop

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }
    public void chooseAnswer(View view){

        if(view.getTag().toString().equals(Integer.toString(correctAnsLocation))){

            score++;
            resulttv.setText("Correct!!!");


        }else{

            resulttv.setText("Wrong!!!");

        }

        numofQuestions++;
        markstv.setText(Integer.toString(score)+"/"+Integer.toString(numofQuestions));
        generateNewQs();


    }
    public void start(View view){

        startButton.setVisibility(View.INVISIBLE);
        tv1.setVisibility(View.INVISIBLE);
        tv2.setVisibility(View.INVISIBLE);

        gamemainlayout.setVisibility(View.VISIBLE);

        playAgain(findViewById(R.id.PlayAgainbt));


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton =(Button)findViewById(R.id.stbutton);
        tv1 =(TextView)findViewById(R.id.textView2);
        tv2 =(TextView)findViewById(R.id.textView3);
        sumtv=(TextView)findViewById(R.id.sumtv);
        resulttv=(TextView)findViewById(R.id.resulttv);
        markstv =(TextView)findViewById(R.id.marksTV);
        timerTV=(TextView)findViewById(R.id.timerTV);

         button0 =(Button)findViewById(R.id.button0);
         button1 =(Button)findViewById(R.id.button1);
         button2 =(Button)findViewById(R.id.button2);
         button3 =(Button)findViewById(R.id.button3);
        PlayAgainbt=(Button)findViewById(R.id.PlayAgainbt);
        gamemainlayout =(RelativeLayout)findViewById(R.id.gamemainlayout);
    }
}
