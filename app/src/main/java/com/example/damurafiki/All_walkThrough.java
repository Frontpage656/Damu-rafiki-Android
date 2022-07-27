package com.example.damurafiki;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class All_walkThrough extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout dotts_layout;
    SliderAdapter sliderAdapter;

    TextView[] text_dotts;
    TextView button_text,button_finish;

    int currentPage ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_walkthrough);

        button_text = findViewById(R.id.button_next);
        button_finish = findViewById(R.id.button_finish);

        viewPager = findViewById(R.id.view_pager);
        dotts_layout = findViewById(R.id.dotts_layout);

        sliderAdapter = new SliderAdapter(getApplicationContext());
        viewPager.setAdapter(sliderAdapter);
        add_indicator(0);
        viewPager.addOnPageChangeListener(viewpagerLis);


        button_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewPager.setCurrentItem(currentPage+1);

            }
        });

        button_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainPage.class));
                finish();
            }
        });

    }

    public void add_indicator(int position) {

        text_dotts = new TextView[3];
        dotts_layout.removeAllViews();

        for (int i = 0; i < text_dotts.length; i++) {

            text_dotts[i] = new TextView(this);
            text_dotts[i].setText(Html.fromHtml("&#8226;"));
            text_dotts[i].setTextSize(40);
            text_dotts[i].setTextColor(getResources().getColor(R.color.dotts));

            dotts_layout.addView(text_dotts[i]);

        }

        if (text_dotts.length >0){
            text_dotts[position].setTextColor(getResources().getColor(R.color.my_primary_color));
        }
    }


    ViewPager.OnPageChangeListener viewpagerLis = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            add_indicator(position);

            currentPage = position;

            if (position == text_dotts.length-1){
                button_text.setText("FINISH");
                button_text.setVisibility(View.GONE);
                button_finish.setVisibility(View.VISIBLE);
            }else{
                button_text.setText("NEXT");
                button_text.setVisibility(View.VISIBLE);
                button_finish.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}