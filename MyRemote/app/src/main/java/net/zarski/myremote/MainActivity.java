package net.zarski.myremote;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import net.zarski.myremote.core.ButtonId;
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

        RemoteStore rs = new RemoteStore(new FileStorage(this, "remotes.json"));
        Remote remote = null;
        try {
            remote = rs.load();
        } catch (IOException e) {
            Log.w("My", "Loads defaults");
            remote = loadDefaults();
        }

        RemoteAdapter adapter = new RemoteAdapter(getApplicationContext(), remote);
        listview.setAdapter(adapter);

        try {
            rs.save(remote);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }

}
