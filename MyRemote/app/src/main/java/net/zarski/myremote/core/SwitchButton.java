package net.zarski.myremote.core;

import android.os.AsyncTask;

import java.io.IOException;

/**
 * Created by lb_lb on 29.12.16.
 */
public class SwitchButton {
    private ButtonId id;
    private String name;
    private Family family;
    private RcState function;

    public void setId(ButtonId id) {
        this.id = id;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public SwitchButton(ButtonId id, Family family, RcState function) {
        this.id = id;
        this.family = family;
        this.function = function;
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

    public RcState getFunction(){
        return function;
    }
}
