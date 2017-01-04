package net.zarski.myremote.core;

/**
 * Created by lb_lb on 29.12.16.
 */
public class ButtonOff extends SwitchButton {

    public ButtonOff(ButtonId id, Family family) {
        super(id, family);
    }

    @Override
    public RcState getRcState() {
        return RcState.OFF;
    }
}
