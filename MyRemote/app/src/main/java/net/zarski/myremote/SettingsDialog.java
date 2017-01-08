package net.zarski.myremote;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import net.zarski.myremote.core.ButtonId;
import net.zarski.myremote.core.Family;
import net.zarski.myremote.core.RcState;
import net.zarski.myremote.core.Remote;
import net.zarski.myremote.core.SwitchButton;

/**
 * Created by lb_lb on 07.01.17.
 */
public class SettingsDialog extends DialogFragment {
    public static final String PREFS_NAME = "MyRemoteSettings";

    public static DialogFragment newInstance(){
        SettingsDialog d = new SettingsDialog();
        return d;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_ip, null);

        final EditText ip_text = (EditText) view.findViewById(R.id.ip);

        // Restore preferences
        final SharedPreferences settings = view.getContext().getSharedPreferences(PREFS_NAME, 0);
        String ip = settings.getString("ip_setting", "");

        if(settings.contains("ip_setting")){
            ip_text.setText(ip);
        }

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("ip_setting", ip_text.getText().toString());
                        editor.commit();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
