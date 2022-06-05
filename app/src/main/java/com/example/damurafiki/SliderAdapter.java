package com.example.damurafiki;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    public int slide_images[] = {
            R.drawable.first_img,
            R.drawable.secong_img,
            R.drawable.third_one
    };

    public String headings[]={
            "Unahitaji damu",
            "Saidia damu",
            "Afya ya damu"
    };

    public String explanation[] = {
            "Tafuta wachangia damu karibu yako",
            "Kila sekunde kunauhitaji wa damu",
            "Pata elimu sahihi ya damu, kutoka kwa wataalamu"
    };

    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout,container,false);

        ImageView imageView = view.findViewById(R.id.imageView);
        TextView the_heading = view.findViewById(R.id.the_heading);
        TextView explanation_text = view.findViewById(R.id.explanation);

        imageView.setImageResource(slide_images[position]);
        the_heading.setText(headings[position]);
        explanation_text.setText(explanation[position]);


        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
