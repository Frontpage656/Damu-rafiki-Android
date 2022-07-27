package com.example.damurafiki;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.example.damurafiki.Adapters.CardListAdapter;
import com.example.damurafiki.Model_class.CardListModelClass;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class NewRequest extends AppCompatActivity {

    RecyclerView card_list;
    RecyclerView.LayoutManager linerLayout;
    CardListAdapter cardListAdapter;

    List<CardListModelClass> list = new ArrayList<>();
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_request);

        card_list = findViewById(R.id.card_list);

        linerLayout = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        card_list.setLayoutManager(linerLayout);

        cardListAdapter = new CardListAdapter(getApplicationContext(),list);

        card_list.setAdapter(cardListAdapter);
        addAll();

    }

    private void addAll() {

        firestore.collection("new_requests").orderBy("distance", Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            Toast.makeText(getApplicationContext(), "Error" + error.toString(), Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            for (DocumentChange documentChange : value.getDocumentChanges()) {
                                if (documentChange.getType() == DocumentChange.Type.ADDED) {

                                    list.add(documentChange.getDocument().toObject(CardListModelClass.class));

                                }

                                cardListAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });

    }
}