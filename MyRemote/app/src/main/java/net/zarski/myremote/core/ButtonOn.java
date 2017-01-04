package net.zarski.myremote.core;

/**
 * Created by lb_lb on 29.12.16.
 */
public class ButtonOn extends SwitchButton {
    public ButtonOn(ButtonId id, Family family) {
        super(id, family);
    }

    @Override
    public RcState getRcState() {
        return RcState.ON;
    }
}
