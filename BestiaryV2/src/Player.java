import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Player {
    private Map<String, Weapon> weapons;
    private Map<String, Fish> fish;
    private Map<String, Beast> tames;
    private String name;
    private Set<String> placesUnlocked;
    private int roomNumber;
    private Set<String> playerOptions;
    private ArrayList<String> regionsUnlocked;
    private Map<String, Bait> bait;
    private Map<String, BugNet> bugNets;
    private Map<String, FishingRod> fishingRods;

    private Constants CONST = new Constants();

    public Player() throws IOException {
        File file = new File("bestiary.txt");

        weapons = new TreeMap<String, Weapon>();
        fish = new TreeMap<String, Fish>();
        tames = new TreeMap<String, Beast>();
        name = new String();
        placesUnlocked = new TreeSet();
        roomNumber = 0;
        playerOptions = new TreeSet<String>();
        playerOptions.add("Explore");
        playerOptions.add("Shop");
        playerOptions.add("Quit to Menu");
        regionsUnlocked = new ArrayList();
        bait = new TreeMap<String, Bait>();
        bugNets = new TreeMap<String, BugNet>();
        fishingRods = new TreeMap<String, FishingRod>();

        if(file.exists()) {
            Scanner fileReader = new Scanner(file);

            String line = fileReader.nextLine();
            StringTokenizer tokenizer = new StringTokenizer(line, ",");
//            while(tokenizer.hasMoreTokens()) {
//                weapons.put(tokenizer.nextToken());
//            }

            line = fileReader.nextLine();
            tokenizer = new StringTokenizer(line, ",");
            while(tokenizer.hasMoreTokens()) {
                String currentToken = tokenizer.nextToken();
                fish.put(currentToken, CONST.getFishMap().get(currentToken));
            }

            line = fileReader.nextLine();
            tokenizer = new StringTokenizer(line, ",");
//            while(tokenizer.hasMoreTokens()) {
//                tames.add(tokenizer.nextToken());
//            }

            line = fileReader.nextLine();
            name = line;

            line = fileReader.nextLine();
            tokenizer = new StringTokenizer(line, ",");
            while(tokenizer.hasMoreTokens()) {
                placesUnlocked.add(tokenizer.nextToken());
            }

            line = fileReader.nextLine();
            roomNumber = Integer.parseInt(line);

            line = fileReader.nextLine();
            playerOptions.remove("Explore");
            playerOptions.remove("Shop");
            playerOptions.remove("Quit to Menu");
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
//            while(tokenizer.hasMoreTokens()) {
//                bait.add(tokenizer.nextToken());
//            }

            line = fileReader.nextLine();
            tokenizer = new StringTokenizer(line, ",");
            while(tokenizer.hasMoreTokens()) {
                String currentToken = tokenizer.nextToken();
                bugNets.put(currentToken, CONST.getBugNetMap().get(currentToken));
            }

            line = fileReader.nextLine();
            tokenizer = new StringTokenizer(line, ",");
            while(tokenizer.hasMoreTokens()) {
                String currentToken = tokenizer.nextToken();
                fishingRods.put(currentToken, CONST.getFishingRodMap().get(currentToken));
            }
        }
    }

    public void save() throws IOException {
        System.out.println("Now saving...");
        System.out.println("Thanks for playing!");
        PrintWriter saveFile = new PrintWriter("bestiary.txt");

        String line = "";
        Iterator iter = weapons.keySet().iterator();
        while(iter.hasNext()) {
            line += iter.next();
            if(iter.hasNext())
                line += ",";
        }
        saveFile.println(line);


        line = "";
        iter = fish.keySet().iterator();
        while(iter.hasNext()) {
            line += iter.next();
            if(iter.hasNext())
                line += ",";
        }
        saveFile.println(line);

        line = "";
        iter = tames.keySet().iterator();
        while(iter.hasNext()) {
            line += iter.next();
            if(iter.hasNext())
                line += ",";
        }
        saveFile.println(line);

        saveFile.println(name);

        line = "";
        iter = placesUnlocked.iterator();
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
        iter = bait.keySet().iterator();
        while(iter.hasNext()) {
            line += iter.next();
            if(iter.hasNext())
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

        line = "";
        iter = fishingRods.keySet().iterator();
        while(iter.hasNext()) {
            line += iter.next();
            if(iter.hasNext())
                line += ",";
        }
        saveFile.println(line);

        saveFile.close();
    }

    public Map<String, Weapon> getWeapons() {
        return weapons;
    }

    public Map<String, Fish> getFish() {
        return fish;
    }

    public Map<String, Beast> getTames() {
        return tames;
    }

    public String getName() {
        return name;
    }

    public Set<String> getPlacesUnlocked() {
        return placesUnlocked;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public Set<String> getPlayerOptions() {
        return playerOptions;
    }

    public ArrayList<String> getRegionsUnlocked() {
        return regionsUnlocked;
    }

    public Map<String, Bait> getBait() {
        return bait;
    }

    public Map<String, BugNet> getBugNets() {
        return bugNets;
    }

    public Map<String, FishingRod> getFishingRods() {
        return fishingRods;
    }

    public void setWeapons(Map<String, Weapon> weapons) {
        this.weapons = weapons;
    }

    public void setFish(Map<String, Fish> fish) {
        this.fish = fish;
    }

    public void setTames(Map<String, Beast> tames) {
        this.tames = tames;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlacesUnlocked(Set<String> placesUnlocked) {
        this.placesUnlocked = placesUnlocked;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setPlayerOptions(Set<String> playerOptions) {
        this.playerOptions = playerOptions;
    }

    public void setRegionsUnlocked(ArrayList<String> regionsUnlocked) {
        this.regionsUnlocked = regionsUnlocked;
    }

    public void setBait(Map<String, Bait> bait) {
        this.bait = bait;
    }

    public void setBugNets(Map<String, BugNet> bugNets) {
        this.bugNets = bugNets;
    }

    public void setFishingRods(Map<String, FishingRod> fishingRods) {
        this.fishingRods = fishingRods;
    }
}
