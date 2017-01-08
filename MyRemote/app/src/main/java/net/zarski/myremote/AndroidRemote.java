package net.zarski.myremote;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import net.zarski.myremote.core.ButtonRow;
import net.zarski.myremote.core.Remote;
import net.zarski.myremote.core.SwitchButton;
import net.zarski.myremote.storage.RemoteStore;

import java.io.IOException;
import java.util.List;

public class AndroidRemote extends Remote implements View.OnClickListener, SwipeMenuListView.OnMenuItemClickListener {
    private Remote remote;
    private RemoteStore remoteStorage;
    private RemoteAdapter adapter;
    private Context context;

    public AndroidRemote(Remote remote, RemoteStore rs, RemoteAdapter adapter, Context context) {
        this.remote = remote;
        remoteStorage = rs;
        this.adapter = adapter;
        this.context = context;
    }

    public void addRow(String label, SwitchButton b1, SwitchButton b2) {
        this.remote.addRow(label, b1, b2);
    }

    public List<ButtonRow> getButtons() {
        return remote.getButtons();
    }

    @Override
    public void setRowAddedListener(RowAddedListener listener) {
        this.remote.setRowAddedListener(listener);
    }

    @Override
    public void onClick(View view) {
        DialogFragment newFragment = AddButtonDialog.newInstance(remote);
        newFragment.show(((FragmentActivity) context).getSupportFragmentManager(), "missiles");
    }

    @Override
    public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
        ButtonRow row = adapter.getItem(position);

        switch (index) {
            case 0:
                // delete
                adapter.remove(row);
                adapter.notifyDataSetChanged();
                try {
                    remoteStorage.save(remote);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
        return false;
    }
}