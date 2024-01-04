package com.example.mindsharpener;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RadioGroup levelRadioGroup;
    private RadioButton radioButtonOption1;
    private RadioButton radioButtonOption2;
    private RadioButton radioButtonOption3;
    private TextView questionTextView;
    private EditText answerEditText;
    private Button submitButton;
    private TextView pointTextView;

    private int currentLevel = 0;
    private int firstNumber = 0;
    private int secondNumber = 0;
    private int currentOperator = 0;
    private int currentPoints = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView textViewQuestion = findViewById(R.id.textViewQuestion);


        levelRadioGroup = findViewById(R.id.radioGroup);
        radioButtonOption1 = findViewById(R.id.radioButtonOption1);
        radioButtonOption2 = findViewById(R.id.radioButtonOption2);
        radioButtonOption3 = findViewById(R.id.radioButtonOption3);
        questionTextView = findViewById(R.id.textViewQuestion);
        answerEditText = findViewById(R.id.editText);
        submitButton = findViewById(R.id.submitButton);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });

        // Change this part of the code
        levelRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButtonOption1) {
                    currentLevel = 1;
                } else if (checkedId == R.id.radioButtonOption2) {
                    currentLevel = 2;
                } else if (checkedId == R.id.radioButtonOption3) {
                    currentLevel = 3;
                }
                generateQuestion();
            }
        });

    }


    private void generateQuestion() {
        Random random = new Random();
        int maxNumber = (int) Math.pow(10, currentLevel);
        firstNumber = random.nextInt(maxNumber);
        secondNumber = random.nextInt(maxNumber);
        currentOperator = random.nextInt(4);

        String operatorSymbol = getOperatorSymbol(currentOperator);

        questionTextView.setText(firstNumber + " " + operatorSymbol + " " + secondNumber);
    }

    private void checkAnswer() {
        String userAnswerStr = answerEditText.getText().toString();
        if (!userAnswerStr.isEmpty()) {
            double userAnswer = Double.parseDouble(userAnswerStr);

            double correctAnswer = calculateAnswer(firstNumber, secondNumber, currentOperator);

            if (userAnswer == correctAnswer) {
                currentPoints++;
            } else {
                currentPoints--;
            }

            pointTextView.setText("Points: " + currentPoints);
            generateQuestion();
        }
    }

    private String getOperatorSymbol(int operator) {
        switch (operator) {
            case 0:
                return "+";
            case 1:
                return "-";
            case 2:
                return "*";
            case 3:
                return "/";
            default:
                return "+";
        }
    }

    private double calculateAnswer(int firstNumber, int secondNumber, int operator) {
        switch (operator) {
            case 0:
                return firstNumber + secondNumber;
            case 1:
                return firstNumber - secondNumber;
            case 2:
                return firstNumber * secondNumber;
            case 3:
                return firstNumber / secondNumber;
            default:
                return firstNumber + secondNumber;
        }
    }
}
