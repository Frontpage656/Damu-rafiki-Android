package com.example.damurafiki.Fragment;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baoyachi.stepview.VerticalStepView;
import com.example.damurafiki.R;

import java.util.ArrayList;
import java.util.List;

public class AccountFrag extends Fragment {

    VerticalStepView verticalStepView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_account, container, false);

        verticalStepView = view.findViewById(R.id.step_view);

        List<String> list = new ArrayList<>();
        list.add("Jumamosi June, 01, 2021");
        list.add("Ijumaa June, 01, 2020");
        list.add("Jumamosi June, 01, 2021");
        list.add("Jumatatu June, 01, 2021");
        list.add("Jumanne May, 12, 2021");
        list.add("Jumamosi June, 01, 2021");
        list.add("Jumamosi June, 01, 2021");
        list.add("Jumamosi June, 01, 2021");
        list.add("Jumamosi June, 01, 2021");
        list.add("leo");

        verticalStepView.setStepsViewIndicatorComplectingPosition(list.size() - 2)//设置完成的步数
                .reverseDraw(true)//default is true
                .setStepViewTexts(list)
                .setLinePaddingProportion(0.85f)
                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(getActivity(), R.color.text_shadow))
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(getActivity(), in.aabhasjindal.otptextview.R.color.red))
                .setStepViewComplectedTextColor(ContextCompat.getColor(getActivity(), android.R.color.black))
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(getActivity(), R.drawable.donations))
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(getActivity(), R.color.black));

        return view;
    }
}