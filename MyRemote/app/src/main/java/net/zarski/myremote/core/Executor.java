package net.zarski.myremote.core;

import java.io.IOException;

/**
 * Created by lb_lb on 29.12.16.
 */
public interface Executor {
    void send(SwitchButton button, RcState state) throws IOException;
}

