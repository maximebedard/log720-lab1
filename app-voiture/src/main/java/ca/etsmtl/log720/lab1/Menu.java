package ca.etsmtl.log720.lab1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private HashMap<String, MenuItem> items;
    private HashMap<String, MenuItem> additionalItems;
    private Menu parent;
    private boolean running = true;
    private String header;

    public Menu() {
        this.items = new HashMap<>();
        this.additionalItems = new HashMap<>();
        addDefaultItems();
    }


    public Menu(Menu parent) {
        this.items = new HashMap<>();
        this.additionalItems = new HashMap<>();
        this.parent = parent;
        addDefaultItems();
    }

    public void add(String key, String description, MenuItemListener listener) {
        this.items.put(key, new MenuItem(this, description, listener));
    }

    public void add(String description, MenuItemListener listener) {
        this.items.put(String.valueOf(items.size()), new MenuItem(this, description, listener));
    }

    public void addOption(String key, String description, MenuItemListener listener) {
        this.additionalItems.put(key, new MenuItem(this, description, listener));
    }

    private void addDefaultItems() {
        if(parent != null) {
            addOption("retour", "Retour au menu précédent", (item) -> {
                setRunning(false);
            });
        }
        addOption("quitter", "Quitter l'application", (item) -> {
            System.exit(0);
        });
    }

    public void display() {
        Scanner scanner = new Scanner(System.in);
        while (running) {
            printItems();
            System.out.print(" -> ");
            String option = scanner.next();

            MenuItem defaultItem = additionalItems.get(option);
            if (defaultItem != null) {
                defaultItem.select();
                continue;
            }

            MenuItem item = items.get(option);
            if (item != null) {
                item.select();
                continue;
            }
        }
    }

    private void printItems() {
        System.out.print(header);
        for (Map.Entry<String, MenuItem> entry : items.entrySet()) {
            System.out.println(String.format("[%s] %s",
                    entry.getKey(),
                    entry.getValue().getDescription()));
        }
        if(additionalItems.size() > 0) {
            System.out.print("\n");
        }
        for (Map.Entry<String, MenuItem> entry : additionalItems.entrySet()) {
            System.out.println(String.format(" -> [%s] %s",
                    entry.getKey(),
                    entry.getValue().getDescription()));
        }
    }

    public Menu getParent() {
        return parent;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
