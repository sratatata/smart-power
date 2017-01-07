package net.zarski.myremote;

import android.app.Dialog;
import android.content.DialogInterface;
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
public class MyDialog extends DialogFragment {
    private Remote remote;

    public static DialogFragment newInstance(Remote remote){
        MyDialog d = new MyDialog();
        d.remote = remote;
        return d;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_button, null);

        final EditText label = (EditText) view.findViewById(R.id.label);
        final EditText family = (EditText) view.findViewById(R.id.family);
        final EditText device = (EditText) view.findViewById(R.id.device);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Family f = new Family(family.getText().toString());
                        ButtonId b = new ButtonId(device.getText().toString());
                        remote.addRow(label.getText().toString(),
                                new SwitchButton(b,f , RcState.ON),
                                new SwitchButton(b, f, RcState.OFF));

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
