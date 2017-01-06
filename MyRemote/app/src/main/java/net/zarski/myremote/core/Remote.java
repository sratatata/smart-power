package net.zarski.myremote.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lb_lb on 29.12.16.
 */
public class Remote {
    private List<ButtonRow> buttons;

    public Remote() {
        buttons = new ArrayList<>();
    }

    public void addRow(String label, ButtonOn b1, ButtonOff b2){
        b1.setName("On");
        b2.setName("Off");
        buttons.add(new ButtonRow(label, b1,b2));
    }

    public ButtonRow[] getButtons() {
        return buttons.toArray(new ButtonRow[buttons.size()]);
    }
}
