package net.zarski.myremote;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listview = (ListView) findViewById(R.id.listview);
        listview.setItemsCanFocus(true);

        Remote remote = new Remote();
        Family f1 = new Family("11010");
        remote.addRow("A", new ButtonOn(new ButtonId("10000"),f1 ), new ButtonOff(new ButtonId("10000"), f1));
        remote.addRow("B", new ButtonOn(new ButtonId("01000"),f1 ), new ButtonOff(new ButtonId("01000"), f1));
        remote.addRow("C", new ButtonOn(new ButtonId("00100"),f1 ), new ButtonOff(new ButtonId("00100"), f1));
        remote.addRow("D", new ButtonOn(new ButtonId("00010"),f1 ), new ButtonOff(new ButtonId("00010"), f1));
        Family f2 = new Family("01111");
        remote.addRow("Test A", new ButtonOn(new ButtonId("10000"), f2), new ButtonOff(new ButtonId("10000"), f2));
        remote.addRow("Test D", new ButtonOn(new ButtonId("00010"), f2), new ButtonOff(new ButtonId("00010"), f2));
        RemoteAdapter adapter = new RemoteAdapter(getApplicationContext(), remote);
        listview.setAdapter(adapter);

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
