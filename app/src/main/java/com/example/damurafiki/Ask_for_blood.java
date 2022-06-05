package com.example.damurafiki;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Ask_for_blood extends AppCompatActivity {

    EditText id_number;
    ImageView scan_qr;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_for_blood);

        id_number = findViewById(R.id.id_number);
        scan_qr = findViewById(R.id.scan_qr);

        dialog = new ProgressDialog(Ask_for_blood.this);
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
                        id_number.setEnabled(false);
                        if (id_number.getText().toString().trim().equals("123456789")) {
                            dialog.show();
                            dialog.setMessage("Alomost there...");



                        } else {
                            Toast.makeText(Ask_for_blood.this, "Wrong id!!!!", Toast.LENGTH_SHORT).show();
                            id_number.setEnabled(true);
                            id_number.setText("");
                        }
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
}