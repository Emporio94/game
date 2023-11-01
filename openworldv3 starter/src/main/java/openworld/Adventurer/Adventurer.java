package openworld.Adventurer;

import java.util.ArrayList;
import java.util.Scanner;

import openworld.Coordinates;
import openworld.Damage;
import openworld.World;
import openworld.entityTypes.TravellingWorldEntity;
import openworld.entityTypes.WorldEntity;

public class Adventurer extends TravellingWorldEntity {

    private Damage[] attacks = new Damage[3];
    private int totalAttacks = 1;

    public Adventurer(String name, Coordinates location, int maxHealth, World world, Damage attack) {
        super(name, location, maxHealth, world, attack);
        attacks[0] = attack;
    }

    public void addAttack(Damage attack) {
        if (totalAttacks < attacks.length) {
            attacks[totalAttacks] = attack;
            totalAttacks++;
        }
    }

    public void attack(WorldEntity target) {
        for (int i = 0; i < totalAttacks; i++) {
            target.takeDamage(attacks[i]);
        }
    }

    public Damage[] getAttacks() {
        return attacks;
    }

    public void setLocation(Coordinates newLocation) {
        this.location = newLocation;
    }
    

    public String printOptions() {
        ArrayList<String> options = new ArrayList<>();

        if (this.getLocation().getY() < world.getyDimension() - 1) {
            options.add("Move North: Click 1");
        }
        if (this.getLocation().getX() < world.getxDimension() - 1) {
            options.add("Move East: Click 2");
        }
        if (this.getLocation().getY() > 0) {
            options.add("Move South: Click 3");
        }
        if (this.getLocation().getX() > 0) {
            options.add("Move West: Click 4");
        }

        // TODO: Add options for interacting with NPCs, items, etc. here

        String allOptions = String.join("\n", options);
        return allOptions;
    }

    public void takeTurn() {
        System.out.println(printOptions());
        Scanner userInput = new Scanner(System.in);
        while (!userInput.hasNext());
        int selection = Integer.parseInt(userInput.nextLine());
        resolveTurn(selection);
    }

    public void resolveTurn(int selection) {
        switch (selection) {
            case 1:
                this.setLocation(new Coordinates(this.getLocation().getX(), this.getLocation().getY() + 1));
                break;
            case 2:
                this.setLocation(new Coordinates(this.getLocation().getX() + 1, this.getLocation().getY()));
                break;
            case 3:
                this.setLocation(new Coordinates(this.getLocation().getX(), this.getLocation().getY() - 1));
                break;
            case 4:
                this.setLocation(new Coordinates(this.getLocation().getX() - 1, this.getLocation().getY()));
                break;
            // TODO: Add cases for other actions (e.g., interacting with NPCs, using items, etc.)
            default:
                System.out.println("Invalid selection. Please try again.");
                break;
        }
    }

    // TODO: Add any other methods or functionalities you need for the Adventurer class

}
