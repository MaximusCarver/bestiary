import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class Bestiary {
    File file;
    Player player;
    Scanner scanner = new Scanner(System.in);
    Constants CONST = new Constants();

    public Bestiary() throws IOException {
        player = new Player();
        file = new File("bestiary.txt");

        if(!file.exists()) {
            file.createNewFile();
            newGame();
        } else {
            continueGame();
        }
    }

    public void newGame() throws IOException {
        System.out.println("Welcome to the mystical land of Midgar, the wild and wonderful kingdom of the beasts.");
        System.out.println("You've arrived at the town of Rutherford, a small settlement in the wilderness.");
        System.out.println("Treasure beyond even a king's comprehension lies hidden around the land, but so do");
        System.out.println("strange creatures, out in both day and night.");
        System.out.println("");
        System.out.println("What is your name, hero?");
        player.setName(scanner.nextLine());
        System.out.println("Nice to meet you. I'm sure you'd like to visit our village and talk to some of the folk,");
        System.out.println("so I'll leave you to that.");

        visitRutherford();
    }

    public void continueGame() throws IOException {
        System.out.println("Welcome back " + player.getName() + "!");

        System.out.println("Ready to continue your adventure?");
        String decision = scanner.nextLine();
        while(decision.toLowerCase().equals("yes") != true && decision.toLowerCase().equals("no") != true) {
            System.out.println("Ready to continue your adventure?");
            decision = scanner.nextLine();
        }

        if(decision.toLowerCase().equals("no")) {
            System.out.println("Thanks for playing!");
            return;
        } else {
            visitRutherford();
        }
    }

    public void visitRutherford() throws IOException {
        if(!player.getPlacesUnlocked().contains("Rutherford")) {
            player.getPlacesUnlocked().add("Rutherford");
            System.out.println("You looked around the small village of Rutherford. Stacks of hay were piled up on the ground.");
            System.out.println("Some people were bent near the hay stacks, raking them into even piles.");
            System.out.println("Others traveled across the town, some entering the herbal medicine store and others");
            System.out.println("heading to the small foodstand nearby. A bowl of freshly harvested potatoes sat on top of the foodstand.");


            System.out.println("Press enter to continue: ");
            String pressEnter = scanner.nextLine();
            while(!pressEnter.equals("")) {
                System.out.println("Press enter to continue: ");
                pressEnter = scanner.nextLine();
            }


            System.out.println("You headed into an old looking inn on the left hand side.");
            while(player.getRoomNumber() < 1 || player.getRoomNumber() > 10 || player.getRoomNumber() == 4 || player.getRoomNumber() == 7) {
                try {
                    System.out.println("(Clerk): What room would you like?");
                    player.setRoomNumber(scanner.nextInt());
                    if (player.getRoomNumber() < 1) {
                        System.out.println("We don't have any rooms less than 1.");
                    } else if (player.getRoomNumber() == 4 || player.getRoomNumber() == 7) {
                        System.out.println("Sorry, that room is taken.");
                    } else if (player.getRoomNumber() > 10) {
                        System.out.println("The highest room number is 10.");
                    }
                }
                catch (InputMismatchException e) {
                    System.out.println("Come on pal, you know the rooms only have numbers.");
                    scanner.nextLine();
                }
            }

            scanner.nextLine();

            player.getPlayerOptions().add("Visit Room");

            System.out.println("You went to room " + player.getRoomNumber() + " and placed your stuff inside. Then you headed back outside.");
        }

        String decision = "";
        while(!decision.equals("Quit to Menu")) {

            villagerChat();

            System.out.println("What would you like to do?");
            System.out.println("-------------------");
            Iterator iter = player.getPlayerOptions().iterator();
            while(iter.hasNext()) {
                System.out.println(iter.next());
            }
            System.out.println("-------------------");

            decision = scanner.nextLine();
            while(!player.getPlayerOptions().contains(decision)) {
                System.out.println("What would you like to do?");
                decision = scanner.nextLine();
            }

            if(decision.equals("Quit to Menu")) {
                player.save();
                return;
            }

            rutherfordAction(decision);
        }
    }

    public void villagerChat() {
        int random = (int)(Math.random() * 3) + 1;

        if(random == 2) {
            int secondRandom = (int)(Math.random() * 3) + 1;
            if(secondRandom == 1) {
                System.out.println("(Villager): I've heard a lot about you. There have been some rumours");
                System.out.println("that you're willing to hunt down some of the monsters plaguing this land.");
                System.out.println("Can we rely on you?");
                String decision = scanner.nextLine();
                if (decision.toLowerCase().equals("yes"))
                    System.out.println("(Villager): I'm glad to hear that.");
                else
                    System.out.println("(Villager): Hmmm. Another empty promise.");
            }

            else if(secondRandom == 2) {
                System.out.println("(Villager): It's nice to meet you.");
            }
            else if(secondRandom == 3) {
                System.out.println("(Villager): I know about a very strange location deep within the forest");
                System.out.println("surrounding Rutherford. Are you curious to learn more?");
                String decision = scanner.nextLine();
                if(decision.toLowerCase().equals("yes")) {
                    System.out.println("I've never been that deep within the forest myself,");
                    System.out.println("but a friend of mine once told me about a mysterious shrine");
                    System.out.println("at the center of the forest. Little is known about it except that");
                    System.out.println("it can only be opened at night.");
                    System.out.println("Here, let me give you a map to it.");
                    System.out.println("...");
                    System.out.println("You've learned the way to the Mysterious Shrine!");
                    player.getPlacesUnlocked().add("Mysterious Shrine");
                    System.out.println("(Villager): Don't do anything stupid.");
                } else {
                    System.out.println("It's probably better for you not to know anyways. I'll see you later.");
                }
            }
        }
    }

    public void rutherfordAction(String decision) throws IOException {
        switch(decision) {
            case "Explore":
                rutherfordExplore();
                break;
            case "Catch Bait":
                catchBait();
                break;
            case "Fish":
                chooseRod();
                break;
//            case "Shop":
//                rutherfordShop();
//                break;
//            case "Visit Room":
//                rutherfordVisitRoom();
//                break;
        }
    }

    public void rutherfordExplore() throws IOException {
        int random = (int)(Math.random() * 3) + 1;
        if(random == 1) {
            if(!player.getPlayerOptions().contains("Fish")) {
                player.getPlayerOptions().add("Fish");
                System.out.println("You've discovered a small lake!");
                System.out.println("Would you like to go fishing?");
                String decision = scanner.nextLine();
                while(!decision.equals("yes") && !decision.equals("no")) {
                    System.out.println("Would you like to go fishing?");
                    decision = scanner.nextLine();
                }

                if(decision.equals("yes") && player.getBait().size() > 0) {
                    chooseRod();
                    return;
                } else if(decision.equals("yes") && player.getBait().size() == 0) {
                    System.out.println("Sorry, you don't have any bait. You'll have to return home.");
                    visitRutherford();
                    return;
                } else {
                    System.out.println("You've returned to Rutherford.");
                    visitRutherford();
                    return;
                }
            }
        }

        else if(random == 2) {
            if(!player.getPlayerOptions().contains("Catch Bait")) {
                player.getPlayerOptions().add("Catch Bait");
                System.out.println("You came across a wide open clearing in the forest.");
                System.out.println("There's plenty of insects scurrying across the underbrush.");
                System.out.println("I'm sure these would make for perfect fishing bait!");
                catchBait();
            }
        }


    }

    public void catchBait() {

        if(!player.getBugNets().containsKey("bug net")) {
            System.out.println("You saw a grey bug net sitting in the middle of the clearing");
            System.out.println("and picked it up.");
            System.out.println("...");
            player.getBugNets().put("bug net", CONST.getBugNetMap().get("bug net"));
            System.out.println("You got a Bug Nut!");
            System.out.println("Now you can catch some bait! Although I do feel bad for the little guys...");
        }

        System.out.println("What Bug Net would you like to use? (or type quit to go home)");
        System.out.println("Your Bug Nets: ");
        System.out.println("-------------------");
        Iterator iter = player.getBugNets().keySet().iterator();
        while(iter.hasNext()) {
            System.out.println(player.getBugNets().get(iter.next()).getName());
        }
        System.out.println("-------------------");

        String decision = scanner.nextLine();
        while(!player.getBugNets().containsKey(decision.toLowerCase()) && !decision.equals("quit")) {
            System.out.println("What Bug Net would you like to use?");
            decision = scanner.nextLine();
        }

        if(decision.equals("quit")) {
            return;
        }

        BugNet currentBugNet = player.getBugNets().get(decision.toLowerCase());

        catchBugs(currentBugNet);
    }

    public void catchBugs(BugNet currentBugNet) {
        System.out.println("Press enter to swing (or quit to go to selection page):");
        String decision = scanner.nextLine();

        while(!decision.equals("") && !decision.equals("quit")) {
            System.out.println("Press enter to swing (or quit to go to selection page):");
            decision = scanner.nextLine();
        }

        if(decision.equals("quit")) {
            catchBait();
            return;
        }


        int swingOrMiss = (int)(Math.random() * currentBugNet.getCatchRate()) + 1;
        if(swingOrMiss == 1) {
            int baitRoll = (int)(Math.random() * 10) + 1;
            if(baitRoll <= 3) {
                System.out.println("You caught an Earthworm!");
                player.getBait().put("earthworm", CONST.getBaitMap().get("earthworm"));
            } else if(baitRoll <= 5) {
                System.out.println("You caught a Grasshopper!");
                player.getBait().put("grasshopper", CONST.getBaitMap().get("grasshopper"));
            } else if(baitRoll <= 7) {
                System.out.println("You caught a Snail!");
                player.getBait().put("snail", CONST.getBaitMap().get("snail"));
            } else if(baitRoll <= 9) {
                System.out.println("You caught a Butterfly!");
                player.getBait().put("butterfly", CONST.getBaitMap().get("butterfly"));
            } else if(baitRoll == 10) {
                System.out.println("You caught a rare Golden Earthworm!");
                player.getBait().put("golden earthworm", CONST.getBaitMap().get("golden earthworm"));
            }
        } else {
            System.out.println("Swing... and a miss.");
        }

        catchBugs(currentBugNet);
    }


    public void chooseRod() {
        if(player.getBait().size() == 0) {
            System.out.println("Sorry, you're all out of bait. You'll have to return home.");
            return;
        }

        if(!player.getFishingRods().containsKey("wooden rod")) {
            System.out.println("You came across a small lake with a thin wooden rod leaning on the edge.");
            System.out.println("You went over to the fishing rod and picked it up.");
            System.out.println("...");
            player.getFishingRods().put("wooden rod", CONST.getFishingRodMap().get("wooden rod"));
            System.out.println("You got a Wooden Rod!");
            System.out.println("Now you can catch some fish!");
        }

        System.out.println("What Fishing Rod would you like to use? (or type quit to go home)");
        System.out.println("Your Fishing Rods: ");
        System.out.println("-------------------");
        Iterator iter = player.getFishingRods().keySet().iterator();
        while(iter.hasNext()) {
            System.out.println(player.getFishingRods().get(iter.next()).getName());
        }
        System.out.println("-------------------");

        String decision = scanner.nextLine();
        while(!player.getFishingRods().containsKey(decision.toLowerCase()) && !decision.equals("quit")) {
            System.out.println("What Fishing Rod would you like to use?");
            decision = scanner.nextLine();
        }

        if(decision.equals("quit")) {
            return;
        }

        FishingRod currentRod = player.getFishingRods().get(decision.toLowerCase());

        catchFish(currentRod);
    }

    public void catchFish(FishingRod currentRod) {
        if(player.getBait().size() == 0) {
            chooseRod();
        }

        System.out.println("Your Bait: ");
        System.out.println("------------------");
        Iterator iter = player.getBait().keySet().iterator();
        while(iter.hasNext()) {
            System.out.println(player.getBait().get(iter.next()).getName());
        }
        System.out.println("------------------");


        System.out.println("Press enter to cast your line (or quit to go to selection page):");
        String decision = scanner.nextLine();

        while(!decision.equals("") && !decision.equals("quit")) {
            System.out.println("Press enter to cast your line (or quit to go to selection page):");
            decision = scanner.nextLine();
        }

        if(decision.equals("quit")) {
            chooseRod();
            return;
        }

        int random = (int)(Math.random() * 100) + 1;

        iter = player.getBait().keySet().iterator();
        Bait currentBait = player.getBait().get(iter.next());

        if(random <= currentBait.getWoodenRodNothingBP(currentRod)) {
            System.out.println("And you caught... nothing.");
        } else if(random <= currentBait.getWoodenRodTinCanBP(currentRod)) {
            System.out.println("You caught a tin can!");
            System.out.println("Ew, it's not even worth trying to sell. You tossed it away.");
        } else if(random <= currentBait.getWoodenRodBassBP(currentRod)) {
            System.out.println("You caught a Bass!");
            player.getFish().put("bass", CONST.getFishMap().get("bass"));
        } else if(random <= currentBait.getWoodenRodSalmonBP(currentRod)) {
            System.out.println("You caught a Salmon!");
            player.getFish().put("salmon", CONST.getFishMap().get("salmon"));
        } else if(random <= currentBait.getWoodenRodTunaBP(currentRod)) {
            System.out.println("You caught a Tuna!");
            player.getFish().put("tuna", CONST.getFishMap().get("tuna"));
        }


        player.getBait().remove(currentBait.getName().toLowerCase());

        catchFish(currentRod);
    }


}

