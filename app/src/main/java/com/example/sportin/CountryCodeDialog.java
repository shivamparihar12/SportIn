package com.example.sportin;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class CountryCodeDialog extends DialogFragment {
    TextView countryCodeTextView;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select your Country")
                .setItems(R.array.DialogCountryCode, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String[] stringArray=getActivity().getResources().getStringArray(R.array.DialogCountryCode);
                        String[] split=stringArray[i].split("\\+");
                        countryCodeTextView=getActivity().findViewById(R.id.country_code);
                        countryCodeTextView.setText("+"+split[1]);
                    }
                });
        return builder.create();

    }
}
