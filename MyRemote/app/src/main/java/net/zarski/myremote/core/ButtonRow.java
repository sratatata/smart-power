package net.zarski.myremote.core;

public class ButtonRow {
        public String label;
        public SwitchButton b1;
        public SwitchButton b2;

    public ButtonRow(String label, SwitchButton b1, SwitchButton b2) {
            this.label = label;
            this.b1 = b1;
            this.b2 = b2;
        }
    }