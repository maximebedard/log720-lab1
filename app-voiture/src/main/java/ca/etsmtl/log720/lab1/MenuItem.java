package ca.etsmtl.log720.lab1;

public class MenuItem {
    private final String value;
    private Runnable callback;

    public MenuItem(String value, Runnable callback) {
        this.value = value;
        this.callback = callback;
    }

    public void run(){
        callback.run();
    }

    @Override
    public String toString() {
        return value;
    }
}