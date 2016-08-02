package com.techiespk.conekt;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by samar_000 on 7/27/2016.
 */

public class Utilz {


    public static void tmsg(Activity a, String m) {
        Toast.makeText(a, m, Toast.LENGTH_SHORT).show();
    }
}
