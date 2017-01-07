package net.zarski.myremote;

import android.view.View;

import net.zarski.myremote.core.ButtonId;
import net.zarski.myremote.core.ButtonRow;
import net.zarski.myremote.core.Family;
import net.zarski.myremote.core.RcState;
import net.zarski.myremote.core.Remote;
import net.zarski.myremote.core.SwitchButton;
import net.zarski.myremote.storage.RemoteStore;

import java.io.IOException;
import java.util.List;

public class AndroidRemote extends Remote implements View.OnClickListener{
    private Remote remote;
    private RemoteStore remoteStorage;
    private RemoteAdapter adapter;

    public AndroidRemote(Remote remote, RemoteStore rs, RemoteAdapter adapter) {
        this.remote = remote;
        remoteStorage = rs;
        this.adapter = adapter;
    }

    public void addRow(String label, SwitchButton b1, SwitchButton b2) {
        this.remote.addRow(label,b1,b2);
    }

    public List<ButtonRow> getButtons() {
        return remote.getButtons();
    }

    @Override
    public void setRowAddedListener(RowAddedListener listener){
        this.remote.setRowAddedListener(listener);
    }

    @Override
    public void onClick(View view) {
        Family f1 = new Family("11010");

        this.remote.addRow("Test", new SwitchButton(new ButtonId("10000"),f1 , RcState.ON), new SwitchButton(new ButtonId("10000"), f1, RcState.OFF));
    }

}