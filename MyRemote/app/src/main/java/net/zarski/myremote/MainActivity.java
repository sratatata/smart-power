package net.zarski.myremote;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import net.zarski.myremote.core.ButtonId;
import net.zarski.myremote.core.ButtonRow;
import net.zarski.myremote.core.Family;
import net.zarski.myremote.core.RcState;
import net.zarski.myremote.core.Remote;
import net.zarski.myremote.core.SwitchButton;
import net.zarski.myremote.storage.FileStorage;
import net.zarski.myremote.storage.RemoteStore;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listview = (ListView) findViewById(R.id.listview);
        listview.setItemsCanFocus(true);

       final RemoteStore rs = new RemoteStore(new FileStorage(this, "remotes.json"));
        Remote remote = null;
        try {
            remote = rs.load();
        } catch (IOException e) {
            Log.w("My", "Loads defaults");
            remote = loadDefaults();
        }

        final RemoteAdapter adapter = new RemoteAdapter(getApplicationContext(), remote);
        listview.setAdapter(adapter);
//        adapter.no

        AndroidRemote androidRemote = new AndroidRemote(remote, rs, adapter);
        androidRemote.setRowAddedListener(new Remote.RowAddedListener(){
            public void onRowAdded(Remote remote, ButtonRow row){
                try {
                    rs.save(remote);
//                    adapter.add(row);
                    adapter.notifyDataSetChanged();
                } catch (IOException e) {
                }

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(androidRemote);


    }

    private Remote loadDefaults(){
        Remote remote = new Remote();
        Family f1 = new Family("11010");
        remote.addRow("Drukarka", new SwitchButton(new ButtonId("10000"),f1 , RcState.ON), new SwitchButton(new ButtonId("10000"), f1, RcState.OFF));
        remote.addRow("B", new SwitchButton(new ButtonId("01000"),f1 , RcState.ON), new SwitchButton(new ButtonId("01000"), f1, RcState.OFF));
        remote.addRow("C", new SwitchButton(new ButtonId("00100"),f1, RcState.ON ), new SwitchButton(new ButtonId("00100"), f1, RcState.OFF));
        remote.addRow("Farelka", new SwitchButton(new ButtonId("00010"),f1, RcState.ON ), new SwitchButton(new ButtonId("00010"), f1, RcState.OFF));
        Family f2 = new Family("01111");
        remote.addRow("Zasilacz", new SwitchButton(new ButtonId("10000"), f2, RcState.ON), new SwitchButton(new ButtonId("10000"), f2, RcState.OFF));
        remote.addRow("Choinka", new SwitchButton(new ButtonId("00010"), f2, RcState.ON), new SwitchButton(new ButtonId("00010"), f2, RcState.OFF));
        return remote;
    }

}
