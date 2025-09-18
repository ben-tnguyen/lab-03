package com.example.listycitylab3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.io.Serializable;

public class EditCityFragment extends DialogFragment {

    public City editCity;
    public String editName;
    public String editProvince;

    public EditCityFragment(City inputCity){
        editCity = inputCity;

        editName = editCity.getName();
        editProvince = editCity.getProvince();
    }

    interface EditCityDialogListener {
        void editCity(City city, String name, String province);
    }
    private EditCityDialogListener listener;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof EditCityDialogListener) {
            listener = (EditCityDialogListener) context;
        } else {
            throw new RuntimeException(context + " must implement EditCityDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_edit_city, null);

        EditText editCityNameBox = view.findViewById(R.id.edit_city);
        EditText editProvinceNameBox = view.findViewById(R.id.edit_province);

        editCityNameBox.setText(editName);
        editProvinceNameBox.setText(editProvince);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Edit a city")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Edit", (dialog, which) -> {
                    String cityName = editCityNameBox.getText().toString();
                    String provinceName = editProvinceNameBox.getText().toString();
                    listener.editCity(editCity, cityName, provinceName);
                })
                .create();
    }
}