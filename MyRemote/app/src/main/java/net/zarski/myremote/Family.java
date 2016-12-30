package net.zarski.myremote;

/**
 * Created by lb_lb on 29.12.16.
 */
public class Family {
    private String id;

    public Family(String id){
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }
}
