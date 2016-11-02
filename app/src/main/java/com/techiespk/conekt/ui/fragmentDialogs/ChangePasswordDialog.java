package com.techiespk.conekt.ui.fragmentDialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.techiespk.conekt.R;

/**
 * Created by samar_000 on 8/1/2016.
 */

public class ChangePasswordDialog extends BaseFragmentDialog {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View dialogView = getActivity().getLayoutInflater().inflate(R.layout.dialog_change_password, null, false);

        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setView(dialogView)
                .setPositiveButton("Update", null)
                .setNegativeButton("Cancel", null)
                .setTitle("Change Password")
                .show();


        return dialog;
    }

}
