package com.example.quizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView totalQuestions;
    TextView Question;
    Button answerA, answerB, answerC, answerD;
    Button submitButton;

    int score;
    int totalQuestion=QuestionAnswer.question.length;
    int currentQuestionIndex=0;
    String selectedAnswer="";

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestions = findViewById(R.id.totalQ);
        Question=findViewById(R.id.question);
        answerA = findViewById(R.id.answerA);
        answerB = findViewById(R.id.answerB);
        answerC = findViewById(R.id.answerC);
        answerD = findViewById(R.id.answerD);
        submitButton= findViewById(R.id.button);


        answerA.setOnClickListener(this);
        answerB.setOnClickListener(this);
        answerC.setOnClickListener(this);
        answerD.setOnClickListener(this);
        submitButton.setOnClickListener(this);

        totalQuestions.setText("Total Questions: "+totalQuestion);

        loadQuestions();



    }



    @Override
    public void onClick(View view) {

        answerA.setBackgroundColor(Color.WHITE);
        answerB.setBackgroundColor(Color.WHITE);
        answerC.setBackgroundColor(Color.WHITE);
        answerD.setBackgroundColor(Color.WHITE);


        Button clickedButton=(Button) view;
        if(clickedButton.getId()==R.id.button){
            if (selectedAnswer.equals(QuestionAnswer.CorrectAnswer[currentQuestionIndex]));
        }
        else {
            selectedAnswer=clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.RED);
        }
            currentQuestionIndex++;
            loadQuestions();



    }

    private void loadQuestions() {
        if (currentQuestionIndex==totalQuestion){
            finihQuiz();
            return;
        }
        Question.setText(QuestionAnswer.question[currentQuestionIndex]);
        answerA.setText(QuestionAnswer.choice[currentQuestionIndex][0]);
        answerB.setText(QuestionAnswer.choice[currentQuestionIndex][1]);
        answerC.setText(QuestionAnswer.choice[currentQuestionIndex][2]);
        answerD.setText(QuestionAnswer.choice[currentQuestionIndex][3]);
    }

     void finihQuiz() {
        String passStatus="";
        if(score>totalQuestion+0.50){
            passStatus="Passed";
        }
        else {
            passStatus="Fail";
        }

         new AlertDialog.Builder(this)
                .setTitle(passStatus).setMessage("Score is:"+score+"out of"+totalQuestion)
         .setPositiveButton("Restart",(dialogInterface,i )->restartQuiz())
                 .setCancelable(false)
                 .show();



    }

    private void restartQuiz() {
        score=0;
        currentQuestionIndex=0;
        loadQuestions();
    }
}