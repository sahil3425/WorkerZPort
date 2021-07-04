package com.example.workerzport.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workerzport.Features.SendMailActivity;
import com.example.workerzport.Models.InternshipModel;
import com.example.workerzport.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdapterIntern extends RecyclerView.Adapter<AdapterIntern.Viewholder> {
    @NonNull
    Context context;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    List<InternshipModel> helperList;

    public AdapterIntern(@NonNull Context context, List<InternshipModel> helperList) {
        this.context = context;
        this.helperList = helperList;
    }
    @Override
    public Viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.intership, parent, false);
        return new AdapterIntern.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Viewholder holder, int position) {

        CollectionReference collectionReference = db.collection("Internships");
        InternshipModel helper = helperList.get(position);
        holder.companyName.setText(helper.getCompanyName());
        holder.jobtype.setText(helper.getJobtype());
        holder.Experience.setText(helper.getExperience());
        holder.skills.setText(helper.getSkills());
        holder.paid.setText(helper.getPaid());
        holder.Mail.setText(helper.getMail());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for (QueryDocumentSnapshot queryDocumentSnapshot1 : queryDocumentSnapshots) {
                            Intent intent = new Intent(v.getContext(), SendMailActivity.class);
                            intent.putExtra("MailAddress",helper.getMail());
                            intent.putExtra("CompanyName",helper.getCompanyName());
                            v.getContext().startActivity(intent);
                        }

                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return helperList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView companyName;
        private TextView jobtype;
        private TextView Experience;
        private TextView skills;
        private TextView paid;
        private LinearLayout layout;
        private TextView Mail;
        public Viewholder(@NonNull @NotNull View itemView) {
            super(itemView);
            companyName=itemView.findViewById(R.id.companyName);
            jobtype=itemView.findViewById(R.id.jobtype);
            Experience=itemView.findViewById(R.id.Experience);
            skills=itemView.findViewById(R.id.skills);
            paid=itemView.findViewById(R.id.paid);
            layout=itemView.findViewById(R.id.linear);
            Mail=itemView.findViewById(R.id.mail);
        }
    }
}
