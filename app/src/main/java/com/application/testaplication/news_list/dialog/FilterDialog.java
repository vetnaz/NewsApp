package com.application.testaplication.news_list.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.application.testaplication.R;
import com.application.testaplication.news_list.FirstActivity;

public class FilterDialog extends DialogFragment {

    final String[] countriesName = {"Ukraine", "USA", "Great Britain","Russia","Germany","France","Italy"};

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Filter")
                .setSingleChoiceItems(countriesName, -1,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int item) {
                                Toast.makeText(
                                        getActivity(),
                                        "Choosen country "
                                                + countriesName[item],
                                        Toast.LENGTH_SHORT).show();
                                FirstActivity.changeCountries(countriesName[item]);
                            }
                        })
                .setIcon(R.drawable.ic_search_black_24dp)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        return builder.create();
    }
}
