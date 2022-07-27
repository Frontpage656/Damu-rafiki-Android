package com.example.damurafiki;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Post_it extends AppCompatActivity {

    TextInputEditText explanation;
    TextView count, post, blood_group, person_name, chupa_count, time;

    String the_coming_id;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_it);

        Intent come_intent = getIntent();


        the_coming_id = come_intent.getStringExtra("id");

        explanation = findViewById(R.id.explanation);
        count = findViewById(R.id.count);
        post = findViewById(R.id.post);

        blood_group=findViewById(R.id.blood_group);
        person_name=findViewById(R.id.person_name);
        chupa_count=findViewById(R.id.chupa_count);
        time=findViewById(R.id.time);


        firestore.collection("find_donor").document(the_coming_id).get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                                    blood_group.setText(String.valueOf(task.getResult().get("blood_group")));
                                    chupa_count.setText(String.valueOf(task.getResult().get("chupa_count")));
                                    person_name.setText(String.valueOf(task.getResult().get("mgojwa_name")));
                                    time.setText(String.valueOf(task.getResult().get("time")));
                                    
                            }
                        });


                        explanation.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                            }

                            @SuppressLint("ResourceAsColor")
                            @Override
                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                int count_value = charSequence.length();
                                int remain_value = 100 - count_value;

                                if (remain_value > 1) {
                                    count.setText(String.valueOf(remain_value));
                                } else {
                                    count.setText(String.valueOf(remain_value));
                                    count.setTextColor(R.color.my_primary_color);
                                    Toast.makeText(Post_it.this, "Imezidi idadi", Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void afterTextChanged(Editable editable) {

                            }
                        });


                    }
                }





