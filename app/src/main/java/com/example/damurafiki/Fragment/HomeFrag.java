package com.example.damurafiki.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.damurafiki.Ask_for_blood;
import com.example.damurafiki.R;


public class HomeFrag extends Fragment {

    CardView find_donor_card,blood_health_card,new_request_card,other_services_card;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_home, container, false);

       find_donor_card = view.findViewById(R.id.find_donor_card);
       blood_health_card = view.findViewById(R.id.blood_health_card);
       new_request_card = view.findViewById(R.id.new_request_card);
       other_services_card = view.findViewById(R.id.other_services_card);


       find_donor_card.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(getContext(), Ask_for_blood.class));
           }
       });




        return view;
    }
}