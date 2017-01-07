package net.zarski.myremote.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lb_lb on 29.12.16.
 */
public class Remote {
    private List<ButtonRow> buttons;
    private List<RowAddedListener> rowAddedListeners;

    public Remote() {
        buttons = new ArrayList<>();
        rowAddedListeners = new ArrayList<>();
    }

    public void addRow(String label, SwitchButton b1, SwitchButton b2){
        b1.setName("On");
        b2.setName("Off");
        ButtonRow row = new ButtonRow(label, b1,b2);
        buttons.add(row);
        fireRowAddedListener(row);
    }

    private void fireRowAddedListener(ButtonRow row) {
        for (RowAddedListener listener : rowAddedListeners ) {
            //todo workaround check null
            if(listener != null){
                listener.onRowAdded(this, row);
            }
        }
    }

    public List<ButtonRow> getButtons() {
        return buttons;
    }

    public void setRowAddedListener(RowAddedListener listener){
        if(rowAddedListeners == null){
            rowAddedListeners = new ArrayList<>();
        }
        rowAddedListeners.add(listener);
    }

    public interface RowAddedListener{
        void onRowAdded(Remote remote, ButtonRow row);
    }
}
