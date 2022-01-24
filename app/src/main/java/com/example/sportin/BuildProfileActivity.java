package com.example.sportin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sportin.databinding.ActivityBuildProfileBinding;
import com.example.sportin.model.UserDetail;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class BuildProfileActivity extends AppCompatActivity {
    private ActivityBuildProfileBinding binding;
    private ArrayList<String> sportList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBuildProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSpinnerValues();

        binding.saveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.userName.getText().toString().isEmpty())
                    Toast.makeText(BuildProfileActivity.this, "Please Enter Your Name", Toast.LENGTH_SHORT).show();
                else if (binding.selectSportSpinner.getSelectedItem() == null)
                    Toast.makeText(BuildProfileActivity.this, "Please Select a Sport", Toast.LENGTH_SHORT).show();
                else if (binding.userName.getText().toString().isEmpty() && binding.selectSportSpinner.getSelectedItem() == null)
                    Toast.makeText(BuildProfileActivity.this, "Please Enter your name and select a sport", Toast.LENGTH_SHORT).show();
                else {
                    UserDetail userDetail = new UserDetail(binding.userName.getText().toString(), binding.selectSportSpinner.getSelectedItem().toString());
                    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
                    DocumentReference documentReference = firebaseFirestore.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    documentReference.set(userDetail).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(BuildProfileActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(BuildProfileActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }
                    })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(BuildProfileActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }

            }
        });

    }

    public void setSpinnerValues() {
        sportList = new ArrayList<String>();
        sportList.add(0, "Cricket");
        sportList.add(1, "Football");

        ArrayAdapter<String> sportListArrayAdapter = new ArrayAdapter<String>(BuildProfileActivity.this, android.R.layout.simple_spinner_item, sportList);
        sportListArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        binding.selectSportSpinner.setAdapter(sportListArrayAdapter);

    }
}