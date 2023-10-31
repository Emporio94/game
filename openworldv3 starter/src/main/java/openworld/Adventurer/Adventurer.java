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

    public String printOptions() {

        ArrayList<String> options = new ArrayList<>();

        if (this.getLocation().getY() < world.getyDimension()) {
            options.add("Move North: Click 1");
        }
        if (this.getLocation().getX() < world.getxDimension() ) { 
            options.add("Move East: Click 2");
        }
        if (this.getLocation().getY() > world.getyDimension()) {
            options.add("Move South: Click 3");
        }
        if (this.getLocation().getX() > world.getxDimension()) {
            options.add("Move West: Click 4");
        }

        String allOptions = String.join("\n", options);
        return allOptions;
    }

    public void takeTurn() {
        System.out.println(printOptions());
        Scanner userInput = new Scanner(System.in);
        while (!userInput.hasNext());
        int selection=(Integer.parseInt(userInput.nextLine()));
        //resolveTurn((Integer.parseInt(userInput.nextLine())));
     
    }

   
  
    
    

}