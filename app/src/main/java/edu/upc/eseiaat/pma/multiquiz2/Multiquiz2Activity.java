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
    private int current_question;
    private String[] all_questions;
    private boolean[] answer_is_correct;
    private TextView TextQuestion;
    private RadioGroup group;
    private Button btn_next;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiquiz2);



        TextQuestion = (TextView) findViewById(R.id.TextQuestion);
        group=(RadioGroup) findViewById(R.id.answer_group) ;
        btn_next = (Button) findViewById(R.id.btn_check);



        all_questions = getResources().getStringArray(R.array.all_questions);
        answer_is_correct=new boolean[all_questions.length];
        current_question=0;
        showquestion();

        //Hemos creado un metodo llamado showquestion,
        // que sera el que lea las preguntas
        // y nos ayude a poder pasar de pregunta en cada pantalla



        //TODO: cuando clickan el boton deberia pasar a la siguiente pregunta

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = group.getCheckedRadioButtonId();
                int answer = -1;
                for (int i = 0; i < ids_answers.length; i++) {
                    if (ids_answers[i] == id) {
                        answer = i;
                    }
                }
                /*
                if (answer == correct_answer) {
                    Toast.makeText(Multiquiz2Activity.this, R.string.correct, Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Multiquiz2Activity.this, R.string.incorrect, Toast.LENGTH_SHORT).show();
                }
                */
                answer_is_correct[current_question]=(answer==correct_answer);

                if (current_question<all_questions.length-1){

                    current_question++;
                    showquestion();
                } else {
                    int correctas=0, incorrectas=0;
                    for (boolean b:answer_is_correct){
                        if (b)correctas++;
                        else incorrectas++;

                    }
                    String resultado=
                            String.format("Correctas: %d  -- Incorrectas: %d", correctas,incorrectas);
                    Toast.makeText(Multiquiz2Activity.this, resultado, Toast.LENGTH_LONG).show();
                    finish();

                }



            }
        });

        //TODO estaria bien tener un boton que pasa a la anterior

    }

    private void showquestion() {
        String q= all_questions[current_question];
        String[] parts=q.split(";");

        group.clearCheck();
        TextQuestion.setText(parts[0]);

        //String[] answers=getResources().getStringArray(R.array.answers);

        for (int i=0; i<ids_answers.length; i++){
            RadioButton rb=(RadioButton) findViewById(ids_answers[i]);
            String answer = parts[i+1];
            if (answer.charAt(0)=='*'){
                correct_answer = i;
                answer=answer.substring(1);
            }
            rb.setText(answer);
        }
        if (/*ultima prgunta*/ current_question==all_questions.length-1){
            btn_next.setText(R.string.finish);
        }
    }
}

