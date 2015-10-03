package ca.etsmtl.log720.lab1;

public class MenuItem {
    private final Menu menu;
    private final String description;
    private final MenuItemListener listener;

    public MenuItem(Menu menu, String description, MenuItemListener listener) {
        this.menu = menu;
        this.description = description;
        this.listener = listener;
    }

    public String getDescription() {
        return description;
    }

    public void select() {
        listener.select(this);
    }

    public Menu getMenu() {
        return menu;
    }
}
