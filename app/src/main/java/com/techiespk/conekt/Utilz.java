package com.techiespk.conekt;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

/**
 * Created by samar_000 on 7/27/2016.
 */

public class Utilz {

    public static void tmsg(Activity a, String m) {
        Toast.makeText(a, m, Toast.LENGTH_SHORT).show();
    }


    public static void AlertBox(Context context, String title, String message) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create()
                .show();
    }
}
