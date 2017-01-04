package net.zarski.myremote.core;

/**
 * Created by lb_lb on 29.12.16.
 */
public enum RcState {
    ON("on"), OFF("off");
    private String representation;

    RcState(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }
}
