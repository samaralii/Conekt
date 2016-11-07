package com.techiespk.conekt.ui.fragmentDialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.techiespk.conekt.R;
import com.techiespk.conekt.Utilz;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by samar_000 on 8/1/2016.
 */

public class ChangePasswordDialog extends BaseFragmentDialog {

    private static final String TAG = ChangePasswordDialog.class.getName().toUpperCase();


    @BindView(R.id.dialog_change_password_newPassword)
    EditText newPassword;


    @BindView(R.id.dialog_change_password_rePassword)
    EditText rePassword;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View dialogView = getActivity().getLayoutInflater().inflate(R.layout.dialog_change_password, null, false);

        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setView(dialogView)
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (newPassword.getText().toString().trim().isEmpty()) {
                            newPassword.setError("Required");
                            return;
                        }

                        if (rePassword.getText().toString().trim().isEmpty()) {
                            rePassword.setError("Required");
                            return;
                        }

                        if (!newPassword.getText().toString().trim().equalsIgnoreCase(rePassword.getText().toString().trim())) {
                            Utilz.tmsg(getActivity(), "Password are not same");
                            return;
                        }


                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                        user.updatePassword(newPassword.getText().toString().trim())
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
//                                            Utilz.tmsg(getActivity(), "Password Successfully Updated");
                                            Log.d(TAG, "User password updated.");
                                        } else {
//                                            Utilz.tmsg(getActivity(), "Failed To Update Password");
                                        }
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
