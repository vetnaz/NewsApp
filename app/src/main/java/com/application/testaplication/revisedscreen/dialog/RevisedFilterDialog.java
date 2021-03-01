package com.application.testaplication.revisedscreen.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.application.testaplication.R;
import com.application.testaplication.revisedscreen.view.RevisedActivity;

public class RevisedFilterDialog extends DialogFragment {

    private final String[] filter = {"New","Old"};
    private int number;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Filter")
                .setSingleChoiceItems(filter, -1,
                        (dialog, item) -> {
                            Toast.makeText(
                                    getActivity(),
                                    "Filter: "
                                            + filter[item],
                                    Toast.LENGTH_SHORT).show();
                            number = item;
                        })
                .setIcon(R.drawable.ic_search_black_24dp)
                .setPositiveButton("Save", (dialog, id) -> {
                    RevisedActivity.changeOrder(filter[number]);
                    dialog.cancel();
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        return builder.create();
    }
}
