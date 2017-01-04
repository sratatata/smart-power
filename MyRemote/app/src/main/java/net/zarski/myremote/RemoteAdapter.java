package net.zarski.myremote;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import net.zarski.myremote.core.ButtonRow;
import net.zarski.myremote.core.Remote;

import java.io.IOException;

public class RemoteAdapter extends ArrayAdapter<ButtonRow> {
    private final Context context;
    private final Remote remote;

    public RemoteAdapter(Context context, Remote remote) {
        super(context, -1, remote.getButtons());
        this.context = context;
        this.remote = remote;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_layout, parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.label);

        final Button buttonOn = (Button) rowView.findViewById(R.id.onButton);
        buttonOn.setText(remote.getButtonRow(position).b1.getName());
        buttonOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    remote.getButtonRow(position).b1.press(new HttpExecutor());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Button buttonOff = (Button) rowView.findViewById(R.id.offButton);
        buttonOff.setText(remote.getButtonRow(position).b2.getName());
        buttonOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    remote.getButtonRow(position).b2.press(new HttpExecutor());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Log.d("ROW", "Label is: "+ remote.getButtonRow(position).label);
        textView.setText(remote.getButtonRow(position).label);

        return rowView;
    }
}
