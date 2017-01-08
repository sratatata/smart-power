package net.zarski.myremote;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

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

        ImageButton settingsButton = (ImageButton) findViewById(R.id.settings);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = SettingsDialog.newInstance();
                newFragment.show(getSupportFragmentManager(), String.valueOf(R.string.settings));
            }
        });

        final RemoteStore rs = new RemoteStore(new FileStorage(this, "remotes.json"));
        Remote remote = null;
        try {
            remote = rs.load();
        } catch (IOException e) {
            remote= new Remote();
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        final SwipeMenuListView listview = (SwipeMenuListView) findViewById(R.id.listview);
        listview.setItemsCanFocus(true);

        listview.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);

        final RemoteAdapter adapter = new RemoteAdapter(getApplicationContext(), remote);

        // step 1. create a MenuCreator
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(60));
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        // set creator
        listview.setMenuCreator(creator);
        listview.setAdapter(adapter);

        AndroidRemote androidRemote = new AndroidRemote(remote, rs, adapter, this);
        androidRemote.setRowAddedListener(new Remote.RowAddedListener(){
            public void onRowAdded(Remote remote, ButtonRow row){
                try {
                    rs.save(remote);
                    adapter.notifyDataSetChanged();
                } catch (IOException e) {
                }

            }
        });

        listview.setOnMenuItemClickListener(androidRemote);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(androidRemote);


    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

}
