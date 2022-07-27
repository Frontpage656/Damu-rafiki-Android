package com.example.damurafiki.Adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.damurafiki.Model_class.CardListModelClass;
import com.example.damurafiki.R;
import com.example.damurafiki.Singleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.ViewHolderClass> {
    Context context;
    List<CardListModelClass> list;

    TextView name, chupa_count, distance;

    ProgressDialog progressDialog;


    public CardListAdapter(Context context, List<CardListModelClass> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_it, parent, false);

        ViewHolderClass viewHolderClass = new ViewHolderClass(view);

        return viewHolderClass;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderClass holder, @SuppressLint("RecyclerView") int position) {

        holder.person_name.setText(list.get(position).getPerson_name());
        holder.distance.setText(list.get(position).getDistance());
        holder.uhitaji.setText(list.get(position).getUhitaji());
        holder.zilizopatikana.setText(list.get(position).getZilizopatikana());
        holder.waliotayari.setText(list.get(position).getWaliotayari());
        holder.phone_number.setText(list.get(position).getPhone_number());
        holder.share_count.setText(list.get(position).getShare_count());

        if (list.get(position).getBlood_group().equals("A+")) {

            holder.blood_group.setBackgroundResource(R.drawable.a_plus);

        } else if (list.get(position).getBlood_group().equals("A-")) {

            holder.blood_group.setBackgroundResource(R.drawable.a_neg);

        } else if (list.get(position).getBlood_group().equals("AB+")) {

            holder.blood_group.setBackgroundResource(R.drawable.ab_plus);

        } else if (list.get(position).getBlood_group().equals("AB-")) {

            holder.blood_group.setBackgroundResource(R.drawable.ab_neg);

        } else if (list.get(position).getBlood_group().equals("O+")) {

            holder.blood_group.setBackgroundResource(R.drawable.o_plus);

        } else if (list.get(position).getBlood_group().equals("O-")) {

            holder.blood_group.setBackgroundResource(R.drawable.o_neg);

        } else if (list.get(position).getBlood_group().equals("B+")) {

            holder.blood_group.setBackgroundResource(R.drawable.b_plus);

        } else if (list.get(position).getBlood_group().equals("B-")) {

            holder.blood_group.setBackgroundResource(R.drawable.b_neg);
        }


        holder.blood_group.setText(list.get(position).getBlood_group());


        holder.saidia_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                View dialog_view = LayoutInflater.from(view.getRootView().getContext()).inflate(R.layout.custom_layout, null);


                name = dialog_view.findViewById(R.id.name);
                chupa_count = dialog_view.findViewById(R.id.chupa_count);
                distance = dialog_view.findViewById(R.id.distance);

                name.setText(holder.person_name.getText());
                chupa_count.setText(holder.uhitaji.getText());
                distance.setText(holder.distance.getText());

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
                builder.setView(dialog_view)
                        .setCancelable(false)
                        .setPositiveButton("THIBITISHA", new DialogInterface.OnClickListener() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                progressDialog = new ProgressDialog(view.getRootView().getContext());
                                progressDialog.setMessage("Subiri kidogo...");
                                progressDialog.setCancelable(false);
                                progressDialog.show();

                                sendText("Anna", "+225745051250", holder.phone_number.getText().toString());

                            }
                        }).setNegativeButton("SITISHA", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                builder.setCancelable(true);
                            }
                        }).show();
            }
            private void sendText(String anayechangia_name, String anayechangia_no, String anayeomba_no) {

                try {

                    JSONObject object = new JSONObject();

                    JSONArray array = new JSONArray();
                    String numbers[] = {anayeomba_no};
                    for (int i = 0; i < numbers.length; i++) {

                        array.put(numbers[i]);

                    }

                    object.put("message", anayechangia_name + " mwenye no " + anayechangia_no + " ameonesha nia ya kukuchangia damu.");
                    object.put("receivers", array);


                    Log.e("out", object.toString());


                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, "http://139.177.182.132:3000/api/v1/send-sms", object, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            if (response != null) {
                                progressDialog.dismiss();
                                list.remove(position);
                                notifyDataSetChanged();
                                Toast.makeText(context.getApplicationContext(), "Ujumbe mfupi umetumwa!!", Toast.LENGTH_SHORT).show();
                                Log.e("Ans", response.toString());

                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            Toast.makeText(context, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
                            Log.e("error", error.toString());
                        }

                    }) {

                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {

                            HashMap<String, String> headers = new HashMap<>();
                            headers.put("Content-Type", "application/json; charset=utf-8");

                            return headers;
                        }
                    };


                    Singleton.getInstance(context).addToRequestQueue(request);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolderClass extends RecyclerView.ViewHolder {
        TextView blood_group, person_name, distance, uhitaji, zilizopatikana, waliotayari, share_count, saidia_button, phone_number;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);

            blood_group = itemView.findViewById(R.id.blood_group);
            phone_number = itemView.findViewById(R.id.phone_number);
            person_name = itemView.findViewById(R.id.person_name);
            distance = itemView.findViewById(R.id.distance);
            uhitaji = itemView.findViewById(R.id.uhitaji);
            zilizopatikana = itemView.findViewById(R.id.zilizopatikana);
            waliotayari = itemView.findViewById(R.id.waliotayari);
            share_count = itemView.findViewById(R.id.share_count);
            saidia_button = itemView.findViewById(R.id.saidia_button);


        }
    }
}
