package com.techiespk.conekt.ui.fragmentDialogs;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.techiespk.conekt.R;
import com.techiespk.conekt.Utilz;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by samar_000 on 11/8/2016.
 */

public class ChangeEmailDialog extends BaseFragmentDialog {

    private static final String TAG = ChangeEmailDialog.class.getName().toUpperCase();

    @BindView(R.id.dialog_change_email_newEMail)
    EditText etEmail;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View dialogView = getActivity().getLayoutInflater().inflate(R.layout.dialog_change_email, null, false);

        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setView(dialogView)
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (etEmail.getText().toString().trim().isEmpty()) {
                            etEmail.setError("Required");
                            return;
                        }

                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                        final ProgressDialog dialog1 = ProgressDialog.show(getActivity(), "Please Wait", "Updating Email");

                        user.updateEmail(etEmail.getText().toString().trim())
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Log.d(TAG, "User email address updated.");
                                            Utilz.tmsg(getActivity(), "Email Address Updated");
                                        } else {
                                            Utilz.tmsg(getActivity(), "Error : Please Try Again");
                                        }

                                        dialog1.dismiss();
                                    }
                                });


                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setTitle("Change Password")
                .show();

        ButterKnife.bind(this, dialogView);


        return dialog;
    }

}
