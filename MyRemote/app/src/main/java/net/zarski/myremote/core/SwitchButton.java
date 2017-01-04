package net.zarski.myremote.core;

import android.os.AsyncTask;

import java.io.IOException;

/**
 * Created by lb_lb on 29.12.16.
 */
public abstract class SwitchButton {
    private ButtonId id;
    private String name;
    private Family family;

    public SwitchButton(ButtonId id, Family family) {
        this.id = id;
        this.family = family;
        this.name = family.toString() + " | " + id.toString();
    }

    public void setName(String name) {
        this.name = name;
    }

    public ButtonId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Family getFamily() {
        return family;
    }

    public void press(AsyncTask task) throws IOException{
        SwitchButton[] a = new SwitchButton[]{this};
        task.execute(a);
    }

    public abstract RcState getRcState();
}
