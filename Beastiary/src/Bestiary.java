import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Bestiary {
    File file;
    Set weaponSet;
    ArrayList fishList;
    Set tameSet;
    String playerName;
    Scanner scanner = new Scanner(System.in);
    Set placesVisited;
    int roomNumber;
    Set playerOptions;
    ArrayList regionsUnlocked;
    ArrayList baitList;
    Map<String, BugNet> bugNets;


    Map bugNetMap;

    public Bestiary() throws IOException {
        file = new File("bestiary.txt");

        weaponSet = new <String>TreeSet();
        fishList = new <String>ArrayList();
        tameSet = new <String>TreeSet();
        playerName = new String();
        placesVisited = new TreeSet();
        roomNumber = 0;
        playerOptions = new TreeSet<String>();
        playerOptions.add("Explore");
        playerOptions.add("Shop");
        playerOptions.add("Quit to Menu");
        regionsUnlocked = new ArrayList();
        baitList = new ArrayList<String>();
        bugNets = new TreeMap<String, BugNet>();

        bugNetMap = new TreeMap<String, BugNet>();
        bugNetMap.put("bug net", new BugNet("Bug Net", 4));

        if(file.exists()) {
            Scanner fileReader = new Scanner(file);

            String line = fileReader.nextLine();
            StringTokenizer tokenizer = new StringTokenizer(line, ",");
            while(tokenizer.hasMoreTokens()) {
                weaponSet.add(tokenizer.nextToken());
            }

            line = fileReader.nextLine();
            tokenizer = new StringTokenizer(line, ",");
            while(tokenizer.hasMoreTokens()) {
                fishList.add(tokenizer.nextToken());
            }

            line = fileReader.nextLine();
            tokenizer = new StringTokenizer(line, ",");
            while(tokenizer.hasMoreTokens()) {
                tameSet.add(tokenizer.nextToken());
            }

            line = fileReader.nextLine();
            playerName = line;

            line = fileReader.nextLine();
            tokenizer = new StringTokenizer(line, ",");
            while(tokenizer.hasMoreTokens()) {
                placesVisited.add(tokenizer.nextToken());
            }

            line = fileReader.nextLine();
            roomNumber = Integer.parseInt(line);

            line = fileReader.nextLine();
            playerOptions.remove("Explore");
            playerOptions.remove("Shop");
            tokenizer = new StringTokenizer(line, ",");
            while(tokenizer.hasMoreTokens()) {
                playerOptions.add(tokenizer.nextToken());
            }

            line = fileReader.nextLine();
            tokenizer = new StringTokenizer(line, ",");
            while(tokenizer.hasMoreTokens()) {
                regionsUnlocked.add(tokenizer.nextToken());
            }

            line = fileReader.nextLine();
            tokenizer = new StringTokenizer(line, ",");
            while(tokenizer.hasMoreTokens()) {
                baitList.add(tokenizer.nextToken());
            }

            line = fileReader.nextLine();
            tokenizer = new StringTokenizer(line, ",");
            while(tokenizer.hasMoreTokens()) {
                bugNets.put(tokenizer.nextToken(), (BugNet)bugNetMap.get(tokenizer.nextToken()));
            }

            continueGame();
        } else {
            file.createNewFile();
            newGame();
        }
    }

    public void newGame() throws IOException {
        System.out.println("Welcome to the mystical land of Midgar, the wild and wonderful kingdom of the beasts.");
        System.out.println("You've arrived at the town of Rutherford, a small settlement in the wilderness.");
        System.out.println("Treasure beyond even a king's comprehension lies hidden around the land, but so do");
        System.out.println("strange creatures, out in both day and night.");
        System.out.println("");
        System.out.println("What is your name, hero?");
        playerName = scanner.nextLine();
        System.out.println("Nice to meet you. I'm sure you'd like to visit our village and talk to some of the folk,");
        System.out.println("so I'll leave you to that.");

        visitRutherford();
    }

    public void continueGame() throws IOException {
        System.out.println("Welcome back " + playerName + "!");

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
        if(!placesVisited.contains("Rutherford")) {
            placesVisited.add("Rutherford");
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
            while(roomNumber < 1 || roomNumber > 10 || roomNumber == 4 || roomNumber == 7) {
                try {
                    System.out.println("(Clerk): What room would you like?");
                    roomNumber = scanner.nextInt();
                    if (roomNumber < 1) {
                        System.out.println("We don't have any rooms less than 1.");
                    } else if (roomNumber == 4 || roomNumber == 7) {
                        System.out.println("Sorry, that room is taken.");
                    } else if (roomNumber > 10) {
                        System.out.println("The highest room number is 10.");
                    }
                }
                catch (InputMismatchException e) {
                    System.out.println("Come on pal, you know the rooms only have numbers.");
                    scanner.nextLine();
                }
            }

            playerOptions.add("Visit Room");

            System.out.println("You went to room " + roomNumber + " and placed your stuff inside. Then you headed back outside.");
        }

        villagerChat();

        System.out.println("What would you like to do?");
        System.out.println("-------------------");
        Iterator iter = playerOptions.iterator();
        while(iter.hasNext()) {
            System.out.println(iter.next());
        }
        System.out.println("-------------------");

        String decision = scanner.nextLine();
        while(!playerOptions.contains(decision)) {
            System.out.println("What would you like to do?");
            decision = scanner.nextLine();
        }

        rutherfordAction(decision);
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
                    regionsUnlocked.add("Mysterious Shrine");
                    System.out.println("(Villager): Don't do anything stupid.");
                } else {
                    System.out.println("It's probably better for you not to know anyways. I'll see you later.");
                }
            }
        }
    }

    public void rutherfordAction(String decision) throws IOException {
        switch(decision) {
            case "Quit to Menu":
                quitGame();
                break;
            case "Explore":
                rutherfordExplore();
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
            if(!playerOptions.contains("Fish")) {
                playerOptions.add("Fish");
                System.out.println("You've discovered a small lake!");
                System.out.println("Would you like to go fishing?");
                String decision = scanner.nextLine();
                while(!decision.equals("yes") && !decision.equals("no")) {
                    System.out.println("Would you like to go fishing?");
                    decision = scanner.nextLine();
                }

                if(decision.equals("yes") && baitList.size() > 0) {
//                    rutherfordFishing();
//                    return;
                } else if(decision.equals("yes") && baitList.size() == 0) {
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
            if(!playerOptions.contains("Catch Bait")) {
                System.out.println("You came across a wide open clearing in the forest.");
                System.out.println("There's plenty of insects scurrying across the underbrush.");
                System.out.println("I'm sure these would make for perfect fishing bait!");
                catchBait();
            }
        }


    }

    public void catchBait() {

        if(!bugNets.containsKey("bug net")) {
            System.out.println("You saw a grey bug net sitting in the middle of the clearing");
            System.out.println("and picked it up.");
            System.out.println("...");
            bugNets.put("bug net", (BugNet)bugNetMap.get("bug net"));
            System.out.println("You got a Bug Nut!");
            System.out.println("Now you can catch some bait! Although I do feel bad for the little guys...");
        }

        System.out.println("What Bug Net would you like to use? (or type quit to go home)");
        System.out.println("Your Bug Nets: ");
        System.out.println("-------------------");
        Iterator iter = bugNets.keySet().iterator();
        while(iter.hasNext()) {
            System.out.println(bugNets.get(iter.next()).name);
        }
        System.out.println("-------------------");

        String decision = scanner.nextLine();
        while(!bugNets.containsKey(decision.toLowerCase()) && !decision.equals("quit")) {
            System.out.println("What Bug Net would you like to use?");
            decision = scanner.nextLine();
        }

        if(decision.equals("quit")) {
            return;
        }

        BugNet currentBugNet = bugNets.get(decision.toLowerCase());

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


        int swingOrMiss = (int)(Math.random() * currentBugNet.catchRate) + 1;
        if(swingOrMiss == 1) {
            int baitRoll = (int)(Math.random() * 100) + 1;
        }
    }









    public void quitGame() throws IOException {
        System.out.println("Now saving...");
        System.out.println("Thanks for playing!");
        PrintWriter saveFile = new PrintWriter("bestiary.txt");

        String line = "";
        Iterator iter = weaponSet.iterator();
        while(iter.hasNext()) {
            line += iter.next();
            if(iter.hasNext())
                line += ",";
        }
        saveFile.println(line);


        line = "";
        for(int i = 0; i < fishList.size(); i++) {
            line += fishList.get(i);
            if(i != (fishList.size() - 1))
                line += ",";
        }
        saveFile.println(line);

        line = "";
        iter = tameSet.iterator();
        while(iter.hasNext()) {
            line += iter.next();
            if(iter.hasNext())
                line += ",";
        }
        saveFile.println(line);

        saveFile.println(playerName);

        line = "";
        iter = placesVisited.iterator();
        while(iter.hasNext()) {
            line += iter.next();
            if(iter.hasNext())
                line += ",";
        }
        saveFile.println(line);

        saveFile.println("" + roomNumber + "");

        line = "";
        iter = playerOptions.iterator();
        while(iter.hasNext()) {
            line += iter.next();
            if(iter.hasNext())
                line += ",";
        }
        saveFile.println(line);

        line = "";
        for(int i = 0; i < regionsUnlocked.size(); i++) {
            line += regionsUnlocked.get(i);
            if(i != (regionsUnlocked.size() - 1))
                line += ",";
        }
        saveFile.println(line);

        line = "";
        for(int i = 0; i < baitList.size(); i++) {
            line += baitList.get(i);
            if(i != (baitList.size() - 1))
                line += ",";
        }
        saveFile.println(line);

        line = "";
        iter = bugNets.keySet().iterator();
        while(iter.hasNext()) {
            line += iter.next();
            if(iter.hasNext())
                line += ",";
        }
        saveFile.println(line);

        saveFile.close();
    }

}
