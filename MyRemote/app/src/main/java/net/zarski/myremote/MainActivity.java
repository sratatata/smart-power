package net.zarski.myremote;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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

public class MainActivity extends FragmentActivity {

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
            remote= new Remote();
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        final RemoteAdapter adapter = new RemoteAdapter(getApplicationContext(), remote);
        listview.setAdapter(adapter);
//        adapter.no

        AndroidRemote androidRemote = new AndroidRemote(remote, rs, adapter, this);
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
}
