package ca.etsmtl.log720.lab1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {

    private final HashMap<String, MenuItem> items;
    private Scanner scanner = new Scanner(System.in);

    public Menu(){
        this.items = new HashMap<>();
    }

    public void put(String key, String description, Runnable callback) {
        this.items.put(key, new MenuItem(description, callback));
    }

    public void put(String description, Runnable callback) {
        this.items.put(String.valueOf(items.size()), new MenuItem(description, callback));
    }

    public MenuItem get(String key) {
        return items.get(key);
    }

    public void prompt(String title) {
        System.out.println(title);
        System.out.println(this.toString());
        System.out.print("=> ");

        String choix = scanner.next();

        MenuItem action = this.get(choix);
        if(action != null){
            action.run();
        }
    }

    @Override
    public String toString() {
        String value = "";
        for (Map.Entry<String, MenuItem> i:items.entrySet()){
            value += String.format("[%s] %s\n", i.getKey(), i.getValue());
        }
        return value;
    }
}
