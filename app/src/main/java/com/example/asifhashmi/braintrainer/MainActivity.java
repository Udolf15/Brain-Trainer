package com.example.asifhashmi.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Varibles for global
    String name;
    TextView timerText,scoreText,quesText,resultText;
    Button choice1,choice2,choice3,choice4,playB,playAgain;
    int correctCount=0,totalCount=0;
    EditText nameEntry;
    boolean isActive=false;
    int locationCorrectAnswer;
    ArrayList<Integer> answers=new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quesText=(TextView)findViewById(R.id.quesText);
        choice1=(Button)findViewById(R.id.choice1);
        choice1.setTag(0);
        choice2=(Button)findViewById(R.id.choice2);
        choice2.setTag(1);
        choice3=(Button)findViewById(R.id.choice3);
        choice3.setTag(2);
        choice4=(Button)findViewById(R.id.choice4);
        choice4.setTag(3);
        timerText=(TextView)findViewById(R.id.timerText);
        scoreText=(TextView)findViewById(R.id.scoreText);
        playB=(Button)findViewById(R.id.playB);
        resultText=(TextView)findViewById(R.id.resultText);
        scoreText.setText(correctCount+"/"+totalCount);
        timerText.setText("50S");
        question();
        playAgain=(Button)findViewById(R.id.playAgain);
        nameEntry=(EditText)findViewById(R.id.nameEntry);

    }

    public void playAgain(View view){

        isActive=true;
        answers.clear();
        correctCount=0;
        totalCount=0;
        scoreText.setText("0/0");
        timer(view);
        playAgain.setVisibility(View.INVISIBLE);
        question();
        answers.clear();


    }

    public void timer(final View view){
        new CountDownTimer(60000+100,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timerText.setText(Long.toString(millisUntilFinished/1000)+"S");
            }

            @Override
            public void onFinish() {

                scoreText.setText(correctCount+"/"+totalCount);
                resultText.setText("Done");
                timerText.setText("0S");
                playAgain.setVisibility(View.VISIBLE);
                playAgain.setText(name+" scored : "+correctCount+"/"+totalCount+"\n\nPlay Again ?");
                isActive=false;
            }
        }.start();
    }

    public void question(){

        Random rand=new Random();

        int num1=rand.nextInt(21);
        int num2=rand.nextInt(21);
        int mul=num1*num2;
        quesText.setText(num1+" * "+num2 +" ?");

        locationCorrectAnswer=rand.nextInt(4);
            Log.i("out",Integer.toString(num1*num2));
        for(int i=0;i<4;i++){
            if(i==locationCorrectAnswer){
                answers.add(mul);
            }else{
                int incorrectAnswer=rand.nextInt(401);
                while(incorrectAnswer==num1*num2){

                }
                answers.add(incorrectAnswer);

            }
        }

        choice1.setText(answers.get(0).toString());
        choice2.setText(answers.get(1).toString());
        choice3.setText(answers.get(2).toString());
        choice4.setText(answers.get(3).toString());

    }



    public void play(View view){
        playB.setVisibility(view.INVISIBLE);
        timer(view);
        isActive=true;

    }

    public void chooseAnswer(View view) {
        if (isActive) {
            Log.i("Sfd", view.getTag().toString());

            if ((int) view.getTag() == locationCorrectAnswer) {
                Log.i("afsf", "Correct");
                resultText.setText("Correct");
                correctCount += 1;

            } else {
                resultText.setText("Incorrect");
                Log.i("afdsf", "incorrect");
            }
            totalCount += 1;
            scoreText.setText(correctCount + "/" + totalCount);
            answers.clear();
            question();

        }


    }

    public void enterTheName(View view){

      if( nameEntry.getText().toString().matches("")){
            Toast.makeText(MainActivity.this,"Enter name first",Toast.LENGTH_SHORT).show();
        }else{
          Log.i("out",nameEntry.getText().toString());
          FrameLayout frameLayout=(FrameLayout)findViewById(R.id.frameLayout);
          frameLayout.setVisibility(View.INVISIBLE);
          TextView title=(TextView)findViewById(R.id.title);
          title.setVisibility(View.INVISIBLE);
          Button enterButton=(Button)findViewById(R.id.enter);
          enterButton.setVisibility(View.INVISIBLE);
          nameEntry.setVisibility(View.INVISIBLE);
          playB.setVisibility(View.VISIBLE);
          name=nameEntry.getText().toString();

          Log.i("NAMe",name);
      }

    }


}
