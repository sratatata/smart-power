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
import net.zarski.myremote.core.SwitchButton;

import java.io.IOException;

public class RemoteAdapter extends ArrayAdapter<ButtonRow> {
    private final Context context;

    public RemoteAdapter(Context context, Remote remote) {
        super(context, -1, remote.getButtons());
        this.context = context;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_layout, parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.label);
        final SwitchButton b1 = getItem(position).b1;
        final SwitchButton b2 = getItem(position).b2;
        final Button buttonOn = (Button) rowView.findViewById(R.id.onButton);
        buttonOn.setText(b1.getName());
        buttonOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    b1.press(new HttpExecutor(context));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Button buttonOff = (Button) rowView.findViewById(R.id.offButton);
        buttonOff.setText(b2.getName());
        buttonOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    b2.press(new HttpExecutor(context));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        textView.setText(getItem(position).label);

        return rowView;
    }
}
