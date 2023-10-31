package openworld.Adventurer;

import java.util.ArrayList;
import java.util.Scanner;

import openworld.Coordinates;
import openworld.Damage;
import openworld.World;
import openworld.characters.Healer;
import openworld.characters.Wizard;
import openworld.entityTypes.TravellingWorldEntity;
import openworld.entityTypes.WorldEntity;
import openworld.terrain.Mountain;

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
    

    public void resolveTurn(int choice) {
        Coordinates currentLocation = this.getLocation();
        int x = currentLocation.getX();
        int y = currentLocation.getY();
    
        switch (choice) {
            case 1: // Move North
                if (y > 0) {
                    this.setLocation(new Coordinates(x, y - 1));
                } else {
                    System.out.println("Invalid move! You can't move North from here.");
                }
                break;
            case 2: // Move East
                if (x < world.getxDimension() - 1) {
                    this.setLocation(new Coordinates(x + 1, y));
                } else {
                    System.out.println("Invalid move! You can't move East from here.");
                }
                break;
            case 3: // Move South
                if (y < world.getyDimension() - 1) {
                    this.setLocation(new Coordinates(x, y + 1));
                } else {
                    System.out.println("Invalid move! You can't move South from here.");
                }
                break;
            case 4: // Move West
                if (x > 0) {
                    this.setLocation(new Coordinates(x - 1, y));
                } else {
                    System.out.println("Invalid move! You can't move West from here.");
                }
                break;
            default:
                System.out.println("Invalid choice! Please select a valid option.");
                break;
        }
    }
    

    public String printOptions() {
        ArrayList<String> options = new ArrayList<>();
    
        if (this.getLocation().getY() < world.getyDimension() - 1) {
            options.add("Move North: Click 2");
        }
        if (this.getLocation().getX() < world.getxDimension() - 1) { 
            options.add("Move East: Click 3");
        }
        if (this.getLocation().getY() > 0) {
            options.add("Move South: Click 4");
        }
        if (this.getLocation().getX() > 0) {
            options.add("Move West: Click 1");
        }
    
        String allOptions = String.join("\n", options);
        return allOptions;
    }
    

    public void takeTurn() {
        System.out.println(printOptions());
        Scanner userInput = new Scanner(System.in);
        while (!userInput.hasNext());
        try {
            int selection = Integer.parseInt(userInput.nextLine());
            resolveTurn(selection);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input! Please enter a number from the options provided");
            
        }
     
    }

   
  
    
    

}
