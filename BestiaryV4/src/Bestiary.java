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

//            player.getPlayerOptions().add("Visit Room");

            System.out.println("You went to room " + player.getRoomNumber() + " and placed your stuff inside. Then you headed back outside.");
        }

        String decision = "";
        while(!decision.equals("Quit to Menu")) {

            player.setHealth(player.getMaxHealth());
            player.setShouldReturnHome(false);

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
                System.out.println("");
                System.out.println("You encountered a villager on your way to Rutherford.");
                System.out.println("");
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
                System.out.println("");
                System.out.println("You encountered a villager on your way to Rutherford.");
                System.out.println("");
                System.out.println("(Villager): It's nice to meet you.");
            }
            else if(secondRandom == 3 && !player.getPlacesUnlocked().contains("Mysterious Shrine")) {
                System.out.println("");
                System.out.println("You encountered a villager on your way to Rutherford.");
                System.out.println("");

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
            case "Shop":
                rutherfordShop();
                break;
//            case "Visit Room":
//                rutherfordVisitRoom();
//                break;
        }
    }

    public void rutherfordExplore() throws IOException {
        int random = (int)(Math.random() * 2) + 1;
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
                    return;
                } else {
                    System.out.println("You've returned to Rutherford.");
                    return;
                }
            } else {
                rutherfordIntermediateExplore();
            }
        }

        else if(random == 2) {
            if(!player.getPlayerOptions().contains("Catch Bait")) {
                player.getPlayerOptions().add("Catch Bait");
                System.out.println("You came across a wide open clearing in the forest.");
                System.out.println("There's plenty of insects scurrying across the underbrush.");
                System.out.println("I'm sure these would make for perfect fishing bait!");
                catchBait();
            } else {
                rutherfordIntermediateExplore();
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
                player.getBait().add(CONST.getBaitMap().get("earthworm"));
            } else if(baitRoll <= 5) {
                System.out.println("You caught a Grasshopper!");
                player.getBait().add(CONST.getBaitMap().get("grasshopper"));
            } else if(baitRoll <= 7) {
                System.out.println("You caught a Snail!");
                player.getBait().add(CONST.getBaitMap().get("snail"));
            } else if(baitRoll <= 9) {
                System.out.println("You caught a Butterfly!");
                player.getBait().add(CONST.getBaitMap().get("butterfly"));
            } else if(baitRoll == 10) {
                System.out.println("You caught a rare Golden Earthworm!");
                player.getBait().add(CONST.getBaitMap().get("golden earthworm"));
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
            return;
        }

        System.out.println("Your Bait: ");
        System.out.println("------------------");
        for(int i = 0; i < player.getBait().size(); i++) {
            System.out.println(player.getBait().get(i).getName());
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

        Bait currentBait = player.getBait().get(0);

        if(random <= currentBait.getWoodenRodNothingBP(currentRod)) {
            System.out.println("And you caught... nothing.");
        } else if(random <= currentBait.getWoodenRodTinCanBP(currentRod)) {
            System.out.println("You caught a tin can!");
            System.out.println("Ew, it's not even worth trying to sell. You tossed it away.");
        } else if(random <= currentBait.getWoodenRodBassBP(currentRod)) {
            int fishRandom =  (int)(Math.random() * 3) + 1;
            if(fishRandom == 1) {
                System.out.println("You caught a Bass!");
                player.getFish().add(CONST.getFishMap().get("bass"));
            } else if(fishRandom == 2) {
                System.out.println("You caught a Minnow!");
                player.getFish().add(CONST.getFishMap().get("minnow"));
            } else if(fishRandom == 3) {
                System.out.println("You caught a Carp!");
                player.getFish().add(CONST.getFishMap().get("carp"));
            }

        } else if(random <= currentBait.getWoodenRodSalmonBP(currentRod)) {
            int fishRandom =  (int)(Math.random() * 3) + 1;
            if(fishRandom == 1) {
                System.out.println("You caught a Salmon!");
                player.getFish().add(CONST.getFishMap().get("salmon"));
            } else if(fishRandom == 2) {
                System.out.println("You caught a Perch!");
                player.getFish().add(CONST.getFishMap().get("perch"));
            } else if(fishRandom == 3) {
                System.out.println("You caught a Bluegill!");
                player.getFish().add(CONST.getFishMap().get("bluegill"));
            }
        } else if(random <= currentBait.getWoodenRodTunaBP(currentRod)) {
            int fishRandom =  (int)(Math.random() * 2) + 1;
            if(fishRandom == 1) {
                System.out.println("You caught a Tuna!");
                player.getFish().add(CONST.getFishMap().get("tuna"));
            } else if(fishRandom == 2) {
                System.out.println("You caught a Rainbow Trout!");
                player.getFish().add(CONST.getFishMap().get("rainbow trout"));
            }
        }


        player.getBait().remove(0);

        catchFish(currentRod);
    }



    public void rutherfordShop() {
        if(!player.getPlacesUnlocked().contains("Shop")) {
            System.out.println("(Seamus): Welcome to the town shop, the premier gear and weapons shop of Rutherford...");
            System.out.println("well, the only gear and weapons shop of Rutherford *(chuckles heartily)*");

            System.out.println("We carry fishing bait, fishing rods, bug nets, weapons, and even a few other trinkets.");
            System.out.println("As the days go by, and you keep on exploring the dangerous land *(laughs)*, ");
            System.out.println("we'll acquire some more interesting stuff. Keep on checking regularly!");
            player.getPlacesUnlocked().add("Shop");
            System.out.println("");
        }

        System.out.println("What can I do for ya? (buy, sell, quit)");
        String decision = scanner.nextLine().toLowerCase();

        while(!decision.equals("buy") && !decision.equals("sell") && !decision.equals("quit")) {
            System.out.println("What can I do for ya? (buy, sell, quit)");
            decision = scanner.nextLine().toLowerCase();
        }

        if(decision.equals("quit")) {
            return;
        }

        switch(decision) {
            case "buy":
                buy();
                break;
            case "sell":
                sell();
                break;
        }

        rutherfordShop();

    }

    public void buy() {

        System.out.println("Shop Items:");
        System.out.println("----------------------------------");
        System.out.println("Beginner Bait (5): 5 Rubidium");
        System.out.println("Falconer Bait (5) 10 Rubidium");
        if(!player.getWeapons().containsKey("tree branch")) {
            System.out.println("Tree Branch: 15 Rubidium");
        }
        if(!player.getWeapons().containsKey("copper sword") && player.getWeapons().containsKey("tree branch")) {
            System.out.println("Copper Sword: 50 Rubidium");
        }
        if(player.getBossesFought().contains("mossback tortoise") || player.getBossesFought().contains("ent")) {
            System.out.println("Healing Potion (2): 100 Rubidium");
        }
        System.out.println("----------------------------------");

        String decision = "";
        while(!decision.equals("beginner bait") && !decision.equals("falconer bait") && !decision.equals("tree branch") && !decision.equals("copper sword") && !decision.equals("quit")) {
            System.out.println("What would you like to purchase? (or quit)");
            decision = scanner.nextLine().toLowerCase();
        }

        if(decision.equals("quit")) {
            return;
        }

        if(decision.equals("beginner bait")) {
            if(player.getMoney() < 5) {
                System.out.println("You don't have enough money for that me lad.");
                buy();
                return;
            }

            System.out.println("It's fine bait, laddy. It'll let you catch fish far more easily.");
            System.out.println("You purchased 5 Beginner Bait!");
            for(int i = 0; i < 5; i++) {
                player.getBait().add(CONST.getBaitMap().get("beginner bait"));
            }
            player.setMoney(player.getMoney() - 5);
        } else if(decision.equals("falconer bait")) {
            if(player.getMoney() < 10) {
                System.out.println("You don't have enough money for that me lad.");
                buy();
                return;
            }

            System.out.println("That's the best bait I have, my lad. Now go catch some fish!");
            System.out.println("You purchased 5 Falconer Bait!");
            for(int i = 0; i < 5; i++) {
                player.getBait().add(CONST.getBaitMap().get("falconer bait"));
            }
            player.setMoney(player.getMoney() - 10);
        } else if(decision.equals("tree branch")) {
            if(player.getWeapons().containsKey("tree branch")) {
                System.out.println("You already bought that lad, remember?");
                buy();
                return;
            } else if(player.getMoney() < 15) {
                System.out.println("You don't have enough money for that me lad.");
                buy();
                return;
            }

            System.out.println("I found this here tree branch lying next to one of the hay stacks and figure I might as well try to sell it to you.");
            System.out.println("I'm sure you can find some uses for it. Hey, maybe it can be used to fight monster? Although I'm not sure how long you'd last with this... hah hah hah");
            System.out.println("");
            System.out.println("You got a Tree Branch!");
            player.getWeapons().put("tree branch", CONST.getWeaponMap().get("tree branch"));
            player.setMoney(player.getMoney() - 15);
        } else if(decision.equals("copper sword")) {
            if(!player.getWeapons().containsKey("tree branch")) {
                buy();
                return;
            } else if(player.getWeapons().containsKey("copper sword")) {
                System.out.println("You already bought that lad, remember?");
                buy();
                return;
            } else if(player.getMoney() < 50) {
                System.out.println("You don't have enough money for that me lad.");
                buy();
                return;
            }

            System.out.println("This here Copper Sword will let you slay monsters far easier, lad. Use it with care.");
            System.out.println("");
            System.out.println("You got a Copper Sword!");
            player.getWeapons().put("copper sword", CONST.getWeaponMap().get("copper sword"));
            player.setMoney(player.getMoney() - 50);
        } else if(decision.equals("healing potion")) {
            if(!player.getBossesFought().contains("ent") && !player.getBossesFought().contains("mossback tortoise")) {
                buy();
                return;
            } else if(player.getMoney() < 100) {
                System.out.println("You don't have enough money for that me lad.");
                buy();
                return;
            }

            System.out.println("These Healing Potions will be very valuable on your journeys laddie. Use them wisely.");
            System.out.println("You got 2 Healing Potions!");
            player.setHealingPotions(player.getHealingPotions() + 2);
            System.out.println("Total Healing Potions: " + player.getHealingPotions());
            player.setMoney(player.getMoney() - 100);
        }
    }

    public void sell() {
        if(player.getFish().size() == 0) {
            System.out.println("You're all out of fish. Now get out there and catch me some more!");
            return;
        }

        System.out.println("");
        System.out.println("Your Catches:");
        System.out.println("-----------------");
        for(int i = 0; i < player.getFish().size(); i++) {
            System.out.println(player.getFish().get(i).getName());
        }
        System.out.println("-----------------");

        String decision = "";
        Fish chosenFish = null;
        while(chosenFish == null && !decision.equals("quit")) {
            System.out.println("What would you like to sell? (or type quit)");
            decision = scanner.nextLine();

            for(int i = 0; i < player.getFish().size(); i++) {
                if(player.getFish().get(i).getName().toLowerCase().equals(decision.toLowerCase())) {
                    chosenFish = player.getFish().get(i);
                }
            }
        }

        if(decision.equals("quit")) {
            return;
        }

        System.out.println("What a lovely fish!");
        System.out.println("How does " + chosenFish.getValue() + " rubidium sound for your " + chosenFish.getName() + "? (yes, no)");

        decision = scanner.nextLine();
        while(!decision.equals("yes") && !decision.equals("no")) {
            System.out.println("Take your time, take your time. But make sure to do it quickly heh heh!");
            decision = scanner.nextLine();
        }

        if(decision.equals("yes")) {
            for(int i = 0; i < player.getFish().size(); i++) {
                if(player.getFish().get(i).getName().equals(chosenFish.getName())) {
                    player.setMoney(player.getMoney() + chosenFish.getValue());
                    player.getFish().remove(i);
                    break;
                }
            }

            System.out.println("Sold!");
            System.out.println("Your Money: " + player.getMoney());
        } else {
            System.out.println("Don't worry about it.");
        }

        sell();
    }


    public void rutherfordIntermediateExplore() {
        int random = (int)(Math.random() * 2) + 1;

        System.out.println("You've arrived at the edge of the dark forest.");
        if(player.getPlacesUnlocked().contains("Mysterious Shrine")) {
            System.out.println("Would you like to go exploring or travel to the Mysterious Shrine? (explore, shrine, quit)");
            String decision = scanner.nextLine();
            while(!decision.equals("explore") && !decision.equals("shrine") && !decision.equals("quit")) {
                System.out.println("Would you like to go exploring or travel to the Mysterious Shrine? (explore, shrine, quit)");
                decision = scanner.nextLine();
            }

            if(decision.equals("quit")) {
                return;
            } else if(decision.toLowerCase().equals("mysterious shrine")) {
                mysteriousShrine();
                return;
            }
        }

        if(random == 1) {
            rutherfordDungeon1();
        } else if(random == 2) {
            rutherfordDungeon2();
        }
    }

    public void rutherfordDungeon1() {
        if(player.getWeapons().size() == 0) {
            System.out.println("You don't have any weapons. It would be dangerous to go exploring now.");
            return;
        }

        System.out.println("Your Weapons:");
        System.out.println("-------------------");
        Iterator iter = player.getWeapons().keySet().iterator();
        while(iter.hasNext()) {
            System.out.println(player.getWeapons().get(iter.next()).getName());
        }
        System.out.println("-------------------");

        String decision = "";
        while(!player.getWeapons().containsKey(decision)) {
            System.out.println("Choose your Weapon");
            decision = scanner.nextLine().toLowerCase();
        }

        player.setCurrentWeapon(player.getWeapons().get(decision));


        System.out.println("Dark brown trees lined the way, creating an eerie atmosphere.");

        int dungeonLength = (int) (Math.random() * (12 - 7)) + 8;
        int step = 0;

        while(step < dungeonLength) {
            decision = "decision";
            while(!decision.equals("") && !decision.equals("quit")) {
                System.out.println("Press enter to continue on (or quit)");
                decision = scanner.nextLine().toLowerCase();
            }

            if(decision.equals("quit")) {
                System.out.println("Returning to Rutherford...");
                return;
            }

            step++;

            switch(step) {
                case 1:
                    System.out.println("You stepped forward and heard the crunch of dead twigs beneath your feet.");
                    break;
                case 2:
                    int randomText = (int) (Math.random() * 2) + 1;
                    if(randomText == 1)
                        System.out.println("You continued onward.");
                    else
                        System.out.println("The wind gusted through the leaves of the trees above.");
                    break;
                case 3:
                    randomText = (int) (Math.random() * 2) + 1;
                    if(randomText == 1)
                        System.out.println("You decided to take another step forward");
                    else
                        System.out.println("You heard a howling in the distance. Probably just Wolves and not some strange magical creature, right? Right?");
                    break;
                case 4:
                    int random = (int)(Math.random() * 2) + 1;
                    if(random == 1) {
                        System.out.println("Hey, a snail! You could use this for bait...");
                        System.out.println("You got a snail!");
                        player.getBait().add(CONST.getBaitMap().get("snail"));
                    } else {
                        System.out.println("You moved onwards");
                    }
                    break;
                case 5:
                    random = (int)(Math.random() * 2) + 1;
                    if(random == 2) {
                        rutherfordEasyRandomEncounter();
                    } else {
                        System.out.println("Is something following you? It sure seems like it...");
                    }
                    break;
                case 6:
                    random = (int)(Math.random() * 2) + 1;
                    if(random == 1) {
                        rutherfordEasyRandomEncounter();
                    } else {
                        System.out.println("A tree fell right in front of you. You stepped over it and moved onwards.");
                    }
                    break;
                case 7:
                    System.out.println("You took another step forward.");
                    break;
                case 8:
                    if(dungeonLength == 8 && !player.getBossesFought().contains("mossback tortoise")) {
                        System.out.println("A gargantuan, moss covered tortoise lay sleeping at the end of the forest.");
                        System.out.println("It heard the crunch of twigs beneath your feet and woke up, slowly getting to its feet.");
                        System.out.println("***BOSS BATTLE***");
                        Enemy CE = CONST.getBossMap().get("mossback tortoise");
                        Enemy mossbackTortoise = new Enemy(CE.getName(), CE.getMaxHealth(), CE.getDamage(), CE.getDefense(), CE.getMoneyDrop(), CE.isTameable());
                        mossbackTortoiseBossBattle(mossbackTortoise);
                    } else if(dungeonLength == 8) {
                        System.out.println("You've reached the end of the forest. You saw a small");
                        System.out.println("wooden chest lying on the ground and opened it up.");
                        System.out.println("You got 10 Rubidium!");
                        player.setMoney(player.getMoney() + 10);
                    } else {
                        System.out.println("You saw a small butterfly fluttering by.");
                        System.out.println("You got a butterfly!");
                        player.getBait().add(CONST.getBaitMap().get("butterfly"));
                    }
                    break;
                case 9:
                    if(dungeonLength == 9 && !player.getBossesFought().contains("mossback tortoise")) {
                        System.out.println("A gargantuan, moss covered tortoise lay sleeping at the end of the forest.");
                        System.out.println("It heard the crunch of twigs beneath your feet and woke up, slowly getting to its feet.");
                        System.out.println("***BOSS BATTLE***");
                        Enemy CE = CONST.getBossMap().get("mossback tortoise");
                        Enemy mossbackTortoise = new Enemy(CE.getName(), CE.getMaxHealth(), CE.getDamage(), CE.getDefense(), CE.getMoneyDrop(), CE.isTameable());
                        mossbackTortoiseBossBattle(mossbackTortoise);
                    } else if(dungeonLength == 9) {
                        System.out.println("You've reached the end of the forest. You saw a small");
                        System.out.println("wooden chest lying on the ground and opened it up.");
                        System.out.println("You got 10 Rubidium!");
                        player.setMoney(player.getMoney() + 10);
                    } else {
                        System.out.println("You took another step forward");
                    }
                    break;
                case 10:
                    if(dungeonLength == 10 && !player.getBossesFought().contains("mossback tortoise")) {
                        System.out.println("A gargantuan, moss covered tortoise lay sleeping at the end of the forest.");
                        System.out.println("It heard the crunch of twigs beneath your feet and woke up, slowly getting to its feet.");
                        System.out.println("***BOSS BATTLE***");
                        Enemy CE = CONST.getBossMap().get("mossback tortoise");
                        Enemy mossbackTortoise = new Enemy(CE.getName(), CE.getMaxHealth(), CE.getDamage(), CE.getDefense(), CE.getMoneyDrop(), CE.isTameable());
                        mossbackTortoiseBossBattle(mossbackTortoise);
                    } else if (dungeonLength == 10) {
                        System.out.println("You've reached the end of the forest. You saw a small");
                        System.out.println("wooden chest lying on the ground and opened it up.");
                        System.out.println("You got 10 Rubidium!");
                        player.setMoney(player.getMoney() + 10);
                    } else {
                        rutherfordEasyRandomEncounter();
                    }
                    break;
                case 11:
                    if(dungeonLength == 11 && !player.getBossesFought().contains("mossback tortoise")) {
                        System.out.println("A gargantuan, moss covered tortoise lay sleeping at the end of the forest.");
                        System.out.println("It heard the crunch of twigs beneath your feet and woke up, slowly getting to its feet.");
                        System.out.println("***BOSS BATTLE***");
                        Enemy CE = CONST.getBossMap().get("mossback tortoise");
                        Enemy mossbackTortoise = new Enemy(CE.getName(), CE.getMaxHealth(), CE.getDamage(), CE.getDefense(), CE.getMoneyDrop(), CE.isTameable());
                        mossbackTortoiseBossBattle(mossbackTortoise);
                    } else if (dungeonLength == 11) {
                        System.out.println("You've reached the end of the forest. You saw a small");
                        System.out.println("wooden chest lying on the ground and opened it up.");
                        System.out.println("You got 10 Rubidium!");
                        player.setMoney(player.getMoney() + 10);
                    } else {
                        System.out.println("How convenient! You found a small health potion and healed yourself right up.");
                        player.setHealth(player.getMaxHealth());
                    }
                    break;
                case 12:
                    if(dungeonLength == 12 && !player.getBossesFought().contains("mossback tortoise")) {
                        System.out.println("A gargantuan, moss covered tortoise lay sleeping at the end of the forest.");
                        System.out.println("It heard the crunch of twigs beneath your feet and woke up, slowly getting to its feet.");
                        System.out.println("***BOSS BATTLE***");
                        Enemy CE = CONST.getBossMap().get("mossback tortoise");
                        Enemy mossbackTortoise = new Enemy(CE.getName(), CE.getMaxHealth(), CE.getDamage(), CE.getDefense(), CE.getMoneyDrop(), CE.isTameable());
                        mossbackTortoiseBossBattle(mossbackTortoise);
                    } else {
                        System.out.println("You've reached the end of the forest. You saw a small");
                        System.out.println("wooden chest lying on the ground and opened it up.");
                        System.out.println("You got 10 Rubidium!");
                        player.setMoney(player.getMoney() + 10);
                    }
                    break;
            }

            if(player.isShouldReturnHome() == true) {
                return;
            }
        }
    }

    public void rutherfordEasyRandomEncounter() {
        int random = (int)(Math.random() * 3) + 1;

        if(random == 1) {
            System.out.println("You encountered a Boar!" );
            Enemy CE = CONST.getEnemyMap().get("boar");
            Enemy boar = new Enemy(CE.getName(), CE.getMaxHealth(), CE.getDamage(), CE.getDefense(), CE.getMoneyDrop(), CE.isTameable());
            randomEncounter(boar);
        } else if(random == 2) {
            System.out.println("You encountered a Bear!");
            Enemy CE = CONST.getEnemyMap().get("bear");
            Enemy bear = new Enemy(CE.getName(), CE.getMaxHealth(), CE.getDamage(), CE.getDefense(), CE.getMoneyDrop(), CE.isTameable());
            randomEncounter(bear);
        } else if(random == 3) {
            System.out.println("You encountered a Wolf!");
            Enemy CE = CONST.getEnemyMap().get("wolf");
            Enemy wolf = new Enemy(CE.getName(), CE.getMaxHealth(), CE.getDamage(), CE.getDefense(), CE.getMoneyDrop(), CE.isTameable());
            randomEncounter(wolf);
        }
    }

    public void randomEncounter(Enemy enemy) {
        System.out.println("Your Health: " + player.getHealth());
        System.out.println(enemy.getName() + "'s Health: " + enemy.getHealth());

        System.out.println("What would you like to do? (fight, flee)");
        String decision = scanner.nextLine().toLowerCase();
        while(!decision.equals("fight") && !decision.equals("flee")) {
            System.out.println("What would you like to do? (attack, flee)");
            decision = scanner.nextLine().toLowerCase();
        }

        if(decision.equals("flee")) {
            int fleeRandom = (int) (Math.random() * 4) + 1;
            if(fleeRandom == 3) {
                System.out.println("Could not flee!!!");
            } else {
                System.out.println("Running back home...");
                System.out.println("You returned to Rutherford safely.");
                player.setShouldReturnHome(true);
                return;
            }
        }


        if(decision.equals("fight")) {
            int damage = player.getCurrentWeapon().getDamage() - enemy.getDefense();
            if(damage <= 0) {
                damage = 1;
            } else if(damage >= enemy.getHealth()) {
                damage = enemy.getHealth();
            }

            System.out.println("You dealt " + damage + " damage to the " + enemy.getName());
            enemy.setHealth(enemy.getHealth() - damage);
        }

        if(enemy.getHealth() <= 0) {
            System.out.println("You defeated the " + enemy.getName() + "!");
            if(enemy.getMoneyDrop() >= 1) {
                System.out.println("You got " + enemy.getMoneyDrop() + " Rubidium");
                player.setMoney(player.getMoney() + enemy.getMoneyDrop());
            }
            return;
        }

        int damageToPlayer = enemy.getDamage() - player.getDefense();
        if(damageToPlayer < 0) {
            damageToPlayer = 0;
        }
        if(damageToPlayer > 0) {
            System.out.println("The " + enemy.getName() + " dealt " + damageToPlayer + " damage to you.");
            player.setHealth(player.getHealth() - damageToPlayer);
        }

        if(player.getHealth() <= 0) {
            System.out.println("You were decimated by the " + enemy.getName());
            System.out.println("You dropped half of your money.");
            player.setMoney(player.getMoney() / 2);
            System.out.println("Returning to Rutherford...");
            player.setShouldReturnHome(true);
            return;
        }

        randomEncounter(enemy);
    }

    public void mossbackTortoiseBossBattle(Enemy boss) {
        System.out.println("Your Health: " + player.getHealth());
        System.out.println("Mossback Tortoise's Health: " + boss.getHealth());

        System.out.println("What would you like to do? (fight, flee)");
        String decision = scanner.nextLine().toLowerCase();
        while(!decision.equals("fight") && !decision.equals("flee")) {
            System.out.println("What would you like to do? (attack, flee)");
            decision = scanner.nextLine().toLowerCase();
        }

        if(decision.equals("flee")) {
            int fleeRandom = (int) (Math.random() * 2) + 1;
            if(fleeRandom == 2) {
                System.out.println("Could not flee!!!");
            } else {
                System.out.println("Running back home...");
                System.out.println("You returned to Rutherford safely.");
                player.setShouldReturnHome(true);
                return;
            }
        }

        if(decision.equals("fight")) {
            int damage = player.getCurrentWeapon().getDamage() - boss.getDefense();
            if(damage <= 0) {
                damage = 1;
            } else if(damage >= boss.getHealth()) {
                damage = boss.getHealth();
            }

            System.out.println("You dealt " + damage + " damage to the " + boss.getName());
            boss.setHealth(boss.getHealth() - damage);
            System.out.println("The Great Mossback Tortoise roared in anger (well... as much as a tortoise can anyways)");
        }

        if(boss.getHealth() <= 0) {
            System.out.println("***Triumphant Music Plays***");
            System.out.println("You defeated the Great Mossback Tortoise!");
            System.out.println("");
            System.out.println("You got the Mossy Stone Sword!");
            player.getWeapons().put("mossy stone sword", CONST.getWeaponMap().get("mossy stone sword"));
            System.out.println("");
            System.out.println("Returning home after your hard fought victory...");
            System.out.println("You've arrived at Rutherford.");

            player.getBossesFought().add("mossback tortoise");
            return;
        }

        int didMiss = (int) (Math.random() * 6) + 1;
        if(didMiss != 5) {
            int damageToPlayer = boss.getDamage() - player.getDefense();
            if (damageToPlayer < 0) {
                damageToPlayer = 0;
            }
            if (damageToPlayer > 0) {
                System.out.println("The Great " + boss.getName() + " dealt " + damageToPlayer + " damage to you.");
                player.setHealth(player.getHealth() - damageToPlayer);
            }
        } else {
            System.out.println("The Great Mossback Tortoise slipped, and his attack missed.");
        }

        if(player.getHealth() <= 0) {
            System.out.println("The Gargantuan Mossback Tortoise rushed forward with a sudden swiftness and stomped all over you.");
            System.out.println("You died.");
            System.out.println("You dropped half of your money.");
            player.setMoney(player.getMoney() / 2);
            System.out.println("Returning to Rutherford...");
            player.setShouldReturnHome(true);
            return;
        }

        mossbackTortoiseBossBattle(boss);
    }

    public void rutherfordDungeon2() {
        if (player.getWeapons().size() == 0) {
            System.out.println("You don't have any weapons. It would be dangerous to go exploring now.");
            return;
        }

        System.out.println("Your Weapons:");
        System.out.println("-------------------");
        Iterator iter = player.getWeapons().keySet().iterator();
        while (iter.hasNext()) {
            System.out.println(player.getWeapons().get(iter.next()).getName());
        }
        System.out.println("-------------------");

        String decision = "";
        while (!player.getWeapons().containsKey(decision)) {
            System.out.println("Choose your Weapon");
            decision = scanner.nextLine().toLowerCase();
        }

        player.setCurrentWeapon(player.getWeapons().get(decision));


        System.out.println("Giant reddish-brown trees dominate the sky-above, stretching far higher than any forest you've ever seen.");

        int dungeonLength = 6;
        int step = 0;

        while (step < dungeonLength) {
            decision = "decision";
            while (!decision.equals("") && !decision.equals("quit")) {
                System.out.println("Press enter to continue on (or quit)");
                decision = scanner.nextLine().toLowerCase();
            }

            if (decision.equals("quit")) {
                System.out.println("Returning to Rutherford...");
                return;
            }

            step++;

            switch (step) {
                case 1:
                    System.out.println("Something whooshed out from behind one of the giant trees!");
                    rutherfordEasyRandomEncounter();
                    break;
                case 2:
                    int randomText = (int) (Math.random() * 2) + 1;
                    if (randomText == 1)
                        System.out.println("You slipped on the giant leaves below and heard a screech coming from your left.");
                    else
                        System.out.println("You have the feeling of being watched...");
                    rutherfordEasyRandomEncounter();
                    break;
                case 3:
                    randomText = (int) (Math.random() * 2) + 1;
                    if (randomText == 1) {
                        System.out.println("Thank goodness! You found a healing potion and healed yourself right up.");
                        player.setHealth(player.getMaxHealth());
                    }
                    else
                        System.out.println("You continued on your way.");
                    break;
                case 4:
                    int random = (int) (Math.random() * 2) + 1;
                    if (random == 1) {
                        System.out.println("The leaves whooshed around you.");
                    } else {
                        System.out.println("A dark shadow flitted out from behind the trees.");
                    }
                    rutherfordEasyRandomEncounter();
                    break;
                case 5:
                    random = (int) (Math.random() * 2) + 1;
                    if (random == 2) {
                        System.out.println("Something charged at you!");
                    } else {
                        System.out.println("You got knocked backwards by something big.");
                    }
                    rutherfordEasyRandomEncounter();
                    break;
                case 6:
                    if(!player.getBossesFought().contains("ent")) {
                        System.out.println("You saw a lone tree in the clearing up ahead. Its branches were shaking almost rhythmically.");
                        System.out.println("Suddenly, as you approached it, wooden fingers sprouted at the end of each branch");
                        System.out.println("and the creature stood up slowly, angered by your presence.");
                        System.out.println("***BOSS BATTLE***");
                        Enemy CE = CONST.getBossMap().get("ent");
                        Enemy ent = new Enemy(CE.getName(), CE.getMaxHealth(), CE.getDamage(), CE.getDefense(), CE.getMoneyDrop(), CE.isTameable());
                        entBossBattle(ent);
                    } else {
                        System.out.println("You came to the end of the forest and noticed a small, brown chest lying in the center of a clearing.");
                        System.out.println("You opened the chest.");
                        System.out.println("");
                        System.out.println("You got 15 Rubidium!");
                        player.setMoney(player.getMoney() + 15);
                    }

                    break;
            }

            if(player.isShouldReturnHome()) {
                return;
            }
        }
    }

    public void entBossBattle(Enemy boss) {
        System.out.println("Your Health: " + player.getHealth());
        System.out.println("Ent's Health: " + boss.getHealth());

        System.out.println("What would you like to do? (fight, flee)");
        String decision = scanner.nextLine().toLowerCase();
        while(!decision.equals("fight") && !decision.equals("flee")) {
            System.out.println("What would you like to do? (attack, flee)");
            decision = scanner.nextLine().toLowerCase();
        }

        if(decision.equals("flee")) {
            int fleeRandom = (int) (Math.random() * 2) + 1;
            if(fleeRandom == 2) {
                System.out.println("Could not flee!!!");
            } else {
                System.out.println("Running back home...");
                System.out.println("You returned to Rutherford safely.");
                player.setShouldReturnHome(true);
                return;
            }
        }

        if(decision.equals("fight")) {
            int damage = player.getCurrentWeapon().getDamage() - boss.getDefense();
            if(damage <= 0) {
                damage = 1;
            } else if(damage >= boss.getHealth()) {
                damage = boss.getHealth();
            }

            System.out.println("You dealt " + damage + " damage to the " + boss.getName());
            boss.setHealth(boss.getHealth() - damage);
            System.out.println("The Ent smashed the ground in rage, shaking the forest.");
        }

        if(boss.getHealth() <= 0) {
            System.out.println("***Triumphant Music Plays***");
            System.out.println("You defeated the Ent!");
            System.out.println("");
            System.out.println("You got a heart container!");
            System.out.println("Maximum Health increased from " + player.getMaxHealth() + " to " + (player.getMaxHealth() + 5));
            player.setMaxHealth(player.getMaxHealth() + 5);
            player.setHealth(player.getMaxHealth());
            player.getHeartContainers().add("entHeartContainer");
            System.out.println("");
            System.out.println("Returning home after your hard fought victory...");
            System.out.println("You've arrived at Rutherford.");

            player.getBossesFought().add("ent");
            return;
        }

        int didMiss = (int) (Math.random() * 6) + 1;
        if(didMiss != 5) {
            int damageToPlayer = boss.getDamage() - player.getDefense();
            if (damageToPlayer < 0) {
                damageToPlayer = 0;
            }
            if (damageToPlayer > 0) {
                System.out.println("The " + boss.getName() + " dealt " + damageToPlayer + " damage to you.");
                player.setHealth(player.getHealth() - damageToPlayer);
            }
        } else {
            System.out.println("You dodged the Ent's swing.");
        }

        if(player.getHealth() <= 0) {
            System.out.println("The Ent slammed its long branches into you.");
            System.out.println("You died.");
            System.out.println("You dropped half of your money.");
            player.setMoney(player.getMoney() / 2);
            System.out.println("Returning to Rutherford...");
            player.setShouldReturnHome(true);
            return;
        }

        entBossBattle(boss);
    }

    public void mysteriousShrine() {
        if (player.getWeapons().size() == 0) {
            System.out.println("You don't have any weapons. It would be dangerous to go exploring now.");
            return;
        }

        System.out.println("Your Weapons:");
        System.out.println("-------------------");
        Iterator iter = player.getWeapons().keySet().iterator();
        while (iter.hasNext()) {
            System.out.println(player.getWeapons().get(iter.next()).getName());
        }
        System.out.println("-------------------");

        String decision = "";
        while (!player.getWeapons().containsKey(decision)) {
            System.out.println("Choose your Weapon");
            decision = scanner.nextLine().toLowerCase();
        }

        player.setCurrentWeapon(player.getWeapons().get(decision));

        if (!player.getPlacesVisited().contains("mysterious shrine")) {
            System.out.println("You followed the path to the Mysterious Shrine that the Villager showed you.");
            System.out.println("You came to a strangely shaped pale white building, the roof vaguely reminiscent of the branches of a dying tree.");
            System.out.println("The door lock was rusted and looked close to falling off. You slammed into the door with your shoulder, ");
            System.out.println("and burst into the entrance of the mysterious shrine.");
        }

        if (player.getPlacesVisited().contains("mysterious shrine")) {
            System.out.println("You entered the Mysterious Shrine.");
        }

        int dungeonLength = 20;
        int step = 0;

        while (step < dungeonLength) {
            decision = "decision";
            while (!decision.equals("") && !decision.equals("quit")) {
                System.out.println("Press enter to continue on (or quit)");
                decision = scanner.nextLine().toLowerCase();
            }

            if (decision.equals("quit")) {
                System.out.println("Returning to Rutherford...");
                return;
            }

            step++;

            switch(step) {
                case 1:
                    System.out.println("The floorboards creaked eerily beneath your feet.");
                    break;
                case 2:
                    int randomText = (int) (Math.random() * 2) + 1;
                    if (randomText == 1)
                        System.out.println("You stepped on ahead... uneasily.");
                    else
                        System.out.println("You have the feeling of being watched...");
                    break;
                case 3:
                    System.out.println("Screech! Screech! A colony of small fruit bats flapped by noisily.");
                    break;
                case 4:
                    System.out.println("The room came to a strange ending. A worn flight of stairs lay in the center of the room.");
                    System.out.println("You began walking down the stairs.");
                    break;
                case 5:
                    randomText = (int) (Math.random() * 3) + 1;
                    if (randomText == 1)
                        System.out.println("You continued on down the stairs.");
                    else if(randomText == 2) {
                        System.out.println("Chink! You stepped on something hard and heard a metallic clinking. You lifted your foot off the ground and saw some Rubidium just laying on the floor.");
                        System.out.println("You got 10 Rubidium!");
                        player.setMoney(player.getMoney() + 10);
                    } else {
                        System.out.println("You saw what appeared to be a skeleton at the bottom of the stairs. It suddenly leapt up and charged towards you.");
                        Enemy CE = CONST.getEnemyMap().get("skeleton");
                        Enemy skeleton = new Enemy(CE.getName(), CE.getMaxHealth(), CE.getDamage(), CE.getDefense(), CE.getMoneyDrop(), CE.isTameable());
                        randomEncounter(skeleton);
                    }
                    break;
                case 6:
                    System.out.println("You rushed around the end of the stairs and continued onwards.");
                    break;
                case 7:
                    randomText = (int) (Math.random() * 10) + 1;
                    if(randomText == 6) {
                        System.out.println("You saw a sack full of Rubidium.");
                        System.out.println("You got 50 Rubidium!");
                        player.setMoney(player.getMoney() + 50);
                    } else
                        System.out.println("You took another step forward.");
                    break;
                case 8:
                    randomText = (int) (Math.random() * 4) + 1;
                    if(randomText == 3) {
                        System.out.println("An arrow whizzed by your head, lodging into the wooden pillar behind you.");
                        System.out.println("A skeleton jumped out at you wearing... archer gear?");
                        Enemy CE = CONST.getEnemyMap().get("skeleton archer");
                        Enemy skeletonArcher = new Enemy(CE.getName(), CE.getMaxHealth(), CE.getDamage(), CE.getDefense(), CE.getMoneyDrop(), CE.isTameable());
                        randomEncounter(skeletonArcher);
                    } else
                        System.out.println("You continued onwards.");
                    break;
                case 9:
                    System.out.println("A snail was sliding across the wooden flooring. But something looked different about it. It was difficult to tell in the darkness, but it appeared to have a grayish hue to it.");
                    System.out.println("You got a Shrine Snail!");
                    player.getBait().add(CONST.getBaitMap().get("shrine snail"));
                    break;
                case 10:
                    randomText = (int) (Math.random() * 2) + 1;
                    if(randomText == 1) {
                        System.out.println("A Skeleton Archer leapt out at you!");
                        Enemy CE = CONST.getEnemyMap().get("skeleton archer");
                        Enemy skeletonArcher = new Enemy(CE.getName(), CE.getMaxHealth(), CE.getDamage(), CE.getDefense(), CE.getMoneyDrop(), CE.isTameable());
                        randomEncounter(skeletonArcher);
                    } else {
                        System.out.println("You heard a clattering noise and saw a Skeleton coming towards you.");
                        Enemy CE = CONST.getEnemyMap().get("skeleton");
                        Enemy skeleton = new Enemy(CE.getName(), CE.getMaxHealth(), CE.getDamage(), CE.getDefense(), CE.getMoneyDrop(), CE.isTameable());
                        randomEncounter(skeleton);
                    }
                    break;
                case 11:
//                    Add Bestiary functionality
//                    System.out.println("");
                    break;
                case 12:
                    randomText = (int) (Math.random() * 2) + 1;
                    if(randomText == 1) {
                        System.out.println("Slam! You stepped on a rotting board of wood and went tumbling into the ground, landing on the floor below.");
                        if(player.getHealth() > 2) {
                            System.out.println("You took 2 damage from the fall.");
                            player.setHealth(player.getHealth() - 2);
                            System.out.println("Health: " + player.getHealth());
                        }
                    } else
                        System.out.println("You headed down another rotting flight of stairs and ended up on the level below.");
                    break;
                case 13:
                    System.out.println("You've gone down pretty far. I wonder how much more is left...");
                    break;
                case 14:
                    randomText = (int) (Math.random() * 3) + 1;
                    if(randomText == 1) {
                        System.out.println("You encountered a Skeleton!");
                        Enemy CE = CONST.getEnemyMap().get("skeleton");
                        Enemy skeleton = new Enemy(CE.getName(), CE.getMaxHealth(), CE.getDamage(), CE.getDefense(), CE.getMoneyDrop(), CE.isTameable());
                        randomEncounter(skeleton);
                    } else if(randomText == 2) {
                        System.out.println("You encountered a Skeleton Archer!");
                        Enemy CE = CONST.getEnemyMap().get("skeleton archer");
                        Enemy skeletonArcher = new Enemy(CE.getName(), CE.getMaxHealth(), CE.getDamage(), CE.getDefense(), CE.getMoneyDrop(), CE.isTameable());
                        randomEncounter(skeletonArcher);
                    } else
                        System.out.println("You stumbled by, tripping over a rotting wood beam in the darkness.");
                    break;
                case 15:
                    System.out.println("A formidable looking skeleton clad in thick armor and carrying a heavy sword wandered out from a doorway to your left.");
                    System.out.println("You drew your weapon in anticipation and charged forward.");
                    Enemy CE = CONST.getEnemyMap().get("skeleton warrior");
                    Enemy skeletonWarrior = new Enemy(CE.getName(), CE.getMaxHealth(), CE.getDamage(), CE.getDefense(), CE.getMoneyDrop(), CE.isTameable());
                    randomEncounter(skeletonWarrior);
                    break;
                case 16:
                    System.out.println("You hurried down the (hopefully) final flight of stairs.");
                    break;
                case 17:
                    System.out.println("You noticed an odd light shining out from the end of the hallway. Maybe it's not too late to turn back?");
                    break;
                case 18:
                    randomText = (int) (Math.random() * 3) + 1;
                    if(randomText == 1) {
                        System.out.println("You encountered a Skeleton!");
                        CE = CONST.getEnemyMap().get("skeleton");
                        Enemy skeleton = new Enemy(CE.getName(), CE.getMaxHealth(), CE.getDamage(), CE.getDefense(), CE.getMoneyDrop(), CE.isTameable());
                        randomEncounter(skeleton);
                    } else if(randomText == 2) {
                        System.out.println("You encountered a Skeleton Archer!");
                        CE = CONST.getEnemyMap().get("skeleton archer");
                        Enemy skeletonArcher = new Enemy(CE.getName(), CE.getMaxHealth(), CE.getDamage(), CE.getDefense(), CE.getMoneyDrop(), CE.isTameable());
                        randomEncounter(skeletonArcher);
                    } else {
                        System.out.println("You encountered a Skeleton Warrior!");
                        CE = CONST.getEnemyMap().get("skeleton warrior");
                        skeletonWarrior = new Enemy(CE.getName(), CE.getMaxHealth(), CE.getDamage(), CE.getDefense(), CE.getMoneyDrop(), CE.isTameable());
                        randomEncounter(skeletonWarrior);
                    }
                    break;
                case 19:
                    System.out.println("You shuddered as you felt a dark presence in the eerie room up ahead.");
                    break;
                case 20:
                    System.out.println("You entered the doorway and saw a evil looking monster clad in black armor hunched over a skeleton, murmuring dark incantations in an effort to bring it back to life.");
                    System.out.println("A strange haze hung over the Necromancer, warping the surrounding air.");
                    System.out.println("The Necromancer turned and faced you, screeching chillingly as it conjured a dark spell.");
                    System.out.println("********");
                    System.out.println("  Boss  ");
                    System.out.println("********");
                    CE = CONST.getEnemyMap().get("necromancer");
                    Enemy necromancer = new Enemy(CE.getName(), CE.getMaxHealth(), CE.getDamage(), CE.getDefense(), CE.getMoneyDrop(), CE.isTameable());
                    necromancerBossBattle(necromancer);
            }

            if(player.isShouldReturnHome()) {
                return;
            }
        }
    }

    public void necromancerBossBattle(Enemy boss) {
        System.out.println("Your Health: " + player.getHealth());
        System.out.println("Necromancer's Health: " + boss.getHealth());

        System.out.println("What would you like to do? (fight, flee)");
        String decision = scanner.nextLine().toLowerCase();
        while (!decision.equals("fight") && !decision.equals("flee")) {
            System.out.println("What would you like to do? (attack, flee)");
            decision = scanner.nextLine().toLowerCase();
        }

        if (decision.equals("flee")) {
            int fleeRandom = (int) (Math.random() * 4) + 1;
            if (fleeRandom == 3) {
                System.out.println("Could not flee!!!");
            } else {
                System.out.println("Running back home...");
                System.out.println("You returned to Rutherford safely.");
                player.setShouldReturnHome(true);
                return;
            }
        }

        if (decision.equals("fight")) {
            int damage = player.getCurrentWeapon().getDamage() - boss.getDefense();
            if (damage <= 0) {
                damage = 1;
            } else if (damage >= boss.getHealth()) {
                damage = boss.getHealth();
            }

            System.out.println("You dealt " + damage + " damage to the " + boss.getName());
            boss.setHealth(boss.getHealth() - damage);
        }

        if (boss.getHealth() <= 0) {
            System.out.println("The Necromancer shrieked eerily and tumbled to the ground.");
            System.out.println("***Triumphant Music Plays***");
            System.out.println("You defeated the Necromancer!");
            System.out.println("");
            System.out.println("You got a heart container!");
            System.out.println("Maximum Health increased from " + player.getMaxHealth() + " to " + (player.getMaxHealth() + 5));
            player.setMaxHealth(player.getMaxHealth() + 5);
            player.setHealth(player.getMaxHealth());
            player.getHeartContainers().add("entHeartContainer");
            System.out.println("");
            System.out.println("Returning home after your hard fought victory...");
            System.out.println("You've arrived at Rutherford.");

            player.getBossesFought().add("ent");
            return;
        }

        int didMiss = (int) (Math.random() * 10) + 1;
        if(didMiss != 5) {
            int damageToPlayer = boss.getDamage() - player.getDefense();
            if (damageToPlayer < 0) {
                damageToPlayer = 0;
            }
            if (damageToPlayer > 0) {
                System.out.println("The " + boss.getName() + " cast a dark spell and dealt " + damageToPlayer + " damage to you.");
                player.setHealth(player.getHealth() - damageToPlayer);
            }
        } else {
            System.out.println("You dove to the ground and narrowly dodged the Necromancer's spell.");
        }

        if(player.getHealth() <= 0) {
            System.out.println("The Necromancer chanted an incantation and blasted it right at you");
            System.out.println("You died.");
            System.out.println("You dropped half of your money.");
            player.setMoney(player.getMoney() / 2);
            System.out.println("Returning to Rutherford...");
            player.setShouldReturnHome(true);
            return;
        }

        necromancerBossBattle(boss);

    }

}

