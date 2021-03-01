package com.application.testaplication.newsscreen.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.application.testaplication.R;
import com.application.testaplication.newsscreen.view.FirstActivity;

public class FilterDialog extends DialogFragment {

    private final String[] countriesName = {"Ukraine", "USA", "Great Britain","Russia","Germany","France","Italy"};

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Filter")
                .setSingleChoiceItems(countriesName, -1,
                        (dialog, item) -> {
                            Toast.makeText(
                                    getActivity(),
                                    "Choosen country "
                                            + countriesName[item],
                                    Toast.LENGTH_SHORT).show();
                            ((FirstActivity) requireActivity()).changeCountries(countriesName[item]);
                        })
                .setIcon(R.drawable.ic_search_black_24dp)
                .setPositiveButton("Save", (dialog, id) -> dialog.cancel())
                .setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        return builder.create();
    }
}
