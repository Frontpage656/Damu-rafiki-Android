package com.example.damurafiki;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Ask_for_blood extends AppCompatActivity {

    EditText id_number;
    ImageView scan_qr;
    ProgressDialog dialog;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_for_blood);

        id_number = findViewById(R.id.id_number);
        scan_qr = findViewById(R.id.scan_qr);

        dialog = new ProgressDialog(Ask_for_blood.this);


        scan_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(Ask_for_blood.this);
                intentIntegrator.setBeepEnabled(true)
                        .setPrompt("kuongeza mwanga tumia +v")
                        .setOrientationLocked(true)
                        .setCaptureActivity(Capture.class);
                intentIntegrator.initiateScan();
            }
        });

        id_number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() == 0) {
                    scan_qr.setVisibility(View.VISIBLE);
                } else {
                    scan_qr.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (charSequence.toString().trim().isEmpty()) {
                    scan_qr.setVisibility(View.VISIBLE);
                } else {
                    scan_qr.setVisibility(View.INVISIBLE);

                    int count = id_number.length();
                    if (count == 9) {
                        dialog.setMessage("Subiri kidogo...");
                        dialog.show();
                        id_number.setEnabled(false);

                        db.collection("find_donor").document(id_number.getText().toString())
                                .get()
                                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                        if (task.getResult().exists()) {
                                            dialog.dismiss();

                                            Intent intent = new Intent(getApplicationContext(),Post_it.class);
                                            intent.putExtra("id",id_number.getText().toString());
                                            startActivity(intent);
                                            finish();


                                        } else {
                                            dialog.dismiss();
                                            Toast.makeText(Ask_for_blood.this, "Nothing like this", Toast.LENGTH_SHORT).show();
                                            id_number.setText("");
                                            id_number.setEnabled(true);
                                        }
                                    }
                                });

                    } else {
                        id_number.setEnabled(true);
                    }


                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (editable.toString().length() == 0) {
                    scan_qr.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result.getContents() != null) {
            if (result.getContents().trim().length() == 9) {

                id_number.setText(result.getContents());

            } else {

                Toast.makeText(this, "Namba sio sahihi", Toast.LENGTH_SHORT).show();
                id_number.setText("");
                id_number.setEnabled(true);
            }
        } else {
            Toast.makeText(this, "Rudia kuscann qr code", Toast.LENGTH_SHORT).show();
        }
    }
}