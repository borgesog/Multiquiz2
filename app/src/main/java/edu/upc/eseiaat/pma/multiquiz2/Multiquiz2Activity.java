package edu.upc.eseiaat.pma.multiquiz2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Multiquiz2Activity extends AppCompatActivity {
    private int ids_answers[]= {
            R.id.answer1, R.id.answer2, R.id.answer3, R.id.answer4
    };
    private int correct_answer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiquiz2);


        TextView TextQuestion = (TextView) findViewById(R.id.TextQuestion);

        String[] all_questions = getResources().getStringArray(R.array.all_questions);
        String q0=all_questions[0];
        String[] parts=q0.split(";");

        TextQuestion.setText(parts[0]);

        //String[] answers=getResources().getStringArray(R.array.answers);

        for (int i=0; i<ids_answers.length; i++){
            RadioButton rb=(RadioButton) findViewById(ids_answers[i]);
            String answer = parts[i+1];
            if (answer.charAt(0)=='*'){
                correct_answer = i;
            }
            rb.setText(parts[i+1]);
        }

        //final int correct_answer = getResources().getInteger(R.integer.correct_answer);

        final RadioGroup group=(RadioGroup) findViewById(R.id.answer_group) ;

        Button btn_check = (Button) findViewById(R.id.btn_check);

        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = group.getCheckedRadioButtonId();
                int answer = -1;
                for (int i = 0; i < ids_answers.length; i++) {
                    if (ids_answers[i] == id) {
                        answer = i;
                    }
                }
                if (answer == correct_answer) {
                    Toast.makeText(Multiquiz2Activity.this, R.string.correct, Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Multiquiz2Activity.this, R.string.incorrect, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

