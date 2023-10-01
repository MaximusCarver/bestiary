import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Player {
    private Map<String, Weapon> weapons;
    private ArrayList<Fish> fish;
    private Map<String, Beast> tames;
    private String name;
    private Set<String> placesUnlocked;
    private int roomNumber;
    private Set<String> playerOptions;
    private ArrayList<String> regionsUnlocked;
    private ArrayList<Bait> bait;
    private Map<String, BugNet> bugNets;
    private Map<String, FishingRod> fishingRods;
    private int money;
    private Set<String> placesVisited;
    private int health;
    private int maxHealth;
    private Weapon currentWeapon;
    private boolean shouldReturnHome;
    private int defense;
    private Set<String> bossesFought;
    private ArrayList<String> heartContainers;
    private int healingPotions;
    private ArrayList<String> keyItems;
    private Map<String, Beast> beastParty;
    private int storedMoney;
    private Map<String, Beast> storedBeasts;
    private ArrayList<BeastBait> beastBaits;
    private int maxPartySize;
    private boolean hasHouse;
    private Map<String, Armor> armor;

    private Constants CONST = new Constants();

    public Player() throws IOException {
        File file = new File("bestiary.txt");

        weapons = new TreeMap<String, Weapon>();
        fish = new ArrayList<Fish>();
        tames = new TreeMap<String, Beast>();
        name = new String();
        placesUnlocked = new TreeSet<>();
        roomNumber = 0;
        playerOptions = new TreeSet<String>();
        playerOptions.add("Explore");
        playerOptions.add("Shop");
        playerOptions.add("Quit to Menu");
        regionsUnlocked = new ArrayList<String>();
        bait = new ArrayList<Bait>();
        bugNets = new TreeMap<String, BugNet>();
        fishingRods = new TreeMap<String, FishingRod>();
        money = 0;
        placesVisited = new TreeSet<>();
        health = 10;
        maxHealth = 10;
        currentWeapon = null;
        shouldReturnHome = false;
        defense = 0;
        bossesFought = new TreeSet<String>();
        heartContainers = new ArrayList<String>();
        healingPotions = 0;
        keyItems = new ArrayList<String>();
        beastParty = new TreeMap<String, Beast>();
        storedMoney = 0;
        storedBeasts = new TreeMap<String, Beast>();
        beastBaits = new ArrayList<BeastBait>();
        maxPartySize = 1;
        hasHouse = false;
        armor = new TreeMap<String, Armor>();

        if(file.exists()) {
            Scanner fileReader = new Scanner(file);

            String line = fileReader.nextLine();
            StringTokenizer tokenizer = new StringTokenizer(line, ",");
            while(tokenizer.hasMoreTokens()) {
                String currentToken = tokenizer.nextToken();
                weapons.put(currentToken, CONST.getWeaponMap().get(currentToken));
            }

            line = fileReader.nextLine();
            tokenizer = new StringTokenizer(line, ",");
            while(tokenizer.hasMoreTokens()) {
                String currentToken = tokenizer.nextToken().toLowerCase();
                fish.add(CONST.getFishMap().get(currentToken));
            }

            line = fileReader.nextLine();
            tokenizer = new StringTokenizer(line, ",");
            while(tokenizer.hasMoreTokens()) {
                String currentToken = tokenizer.nextToken().toLowerCase();
                Enemy CE = CONST.getEnemyMap().get(currentToken);
                tames.put(currentToken, new Beast(CE.getName(), CE.getMaxHealth(), CE.getDamage(), CE.getDefense()));
            }

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
            while(tokenizer.hasMoreTokens()) {
                bait.add(CONST.getBaitMap().get(tokenizer.nextToken()));
            }

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

            line = fileReader.nextLine();
            money = Integer.parseInt(line);

            line = fileReader.nextLine();
            tokenizer = new StringTokenizer(line, ",");
            while(tokenizer.hasMoreTokens()) {
                String currentToken = tokenizer.nextToken();
                placesVisited.add(currentToken);
            }

            line = fileReader.nextLine();
            health = Integer.parseInt(line);

            line = fileReader.nextLine();
            maxHealth = Integer.parseInt(line);

            line = fileReader.nextLine();
            defense = Integer.parseInt(line);

            line = fileReader.nextLine();
            tokenizer = new StringTokenizer(line, ",");
            while(tokenizer.hasMoreTokens()) {
                String currentToken = tokenizer.nextToken();
                bossesFought.add(currentToken);
            }

            line = fileReader.nextLine();
            tokenizer = new StringTokenizer(line, ",");
            while(tokenizer.hasMoreTokens()) {
                String currentToken = tokenizer.nextToken();
                heartContainers.add(currentToken);
            }

            line = fileReader.nextLine();
            healingPotions = Integer.parseInt(line);

            line = fileReader.nextLine();
            tokenizer = new StringTokenizer(line, ",");
            while(tokenizer.hasMoreTokens()) {
                String currentToken = tokenizer.nextToken();
                keyItems.add(currentToken);
            }

            line = fileReader.nextLine();
            tokenizer = new StringTokenizer(line, ",");
            while(tokenizer.hasMoreTokens()) {
                String currentToken = tokenizer.nextToken();
                Enemy CE = CONST.getEnemyMap().get(currentToken);
                beastParty.put(currentToken.toLowerCase(), new Beast(CE.getName(), CE.getMaxHealth(), CE.getDamage(), CE.getDefense()));
            }

            line = fileReader.nextLine();
            healingPotions = Integer.parseInt(line);

            line = fileReader.nextLine();
            tokenizer = new StringTokenizer(line, ",");
            while(tokenizer.hasMoreTokens()) {
                String currentToken = tokenizer.nextToken();
                Enemy CE = CONST.getEnemyMap().get(currentToken);
                storedBeasts.put(currentToken.toLowerCase(), new Beast(CE.getName(), CE.getMaxHealth(), CE.getDamage(), CE.getDefense()));
            }

            line = fileReader.nextLine();
            tokenizer = new StringTokenizer(line, ",");
            while(tokenizer.hasMoreTokens()) {
                beastBaits.add(CONST.getBeastBaitMap().get(tokenizer.nextToken()));
            }

            line = fileReader.nextLine();
            maxPartySize = Integer.parseInt(line);

            line = fileReader.nextLine();
            hasHouse = Boolean.parseBoolean(line);

            line = fileReader.nextLine();
            tokenizer = new StringTokenizer(line, ",");
            while(tokenizer.hasMoreTokens()) {
                String currentToken = tokenizer.nextToken();
                armor.put(currentToken, CONST.getArmorMap().get(currentToken));
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
        for(int i = 0; i < fish.size(); i++) {
            line += fish.get(i).getName().toLowerCase();
            if(i != (fish.size() - 1))
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
        for(int i = 0; i < bait.size(); i++) {
            line += bait.get(i).getName().toLowerCase();
            if(i != (bait.size() - 1))
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

        line = "";
        line += "" + money + "";
        saveFile.println(line);

        line = "";
        iter = placesVisited.iterator();
        while(iter.hasNext()) {
            line += iter.next();
            if(iter.hasNext())
                line += ",";
        }
        saveFile.println(line);

        line = "";
        line += "" + health + "";
        saveFile.println(line);

        line = "";
        line += "" + maxHealth + "";
        saveFile.println(line);

        line = "";
        line += "" + defense + "";
        saveFile.println(line);

        line = "";
        iter = bossesFought.iterator();
        while(iter.hasNext()) {
            line += iter.next();
            if(iter.hasNext())
                line += ",";
        }
        saveFile.println(line);

        line = "";
        for(int i = 0; i < heartContainers.size(); i++) {
            line += heartContainers.get(i);
            if(i != (heartContainers.size() - 1))
                line += ",";
        }
        saveFile.println(line);

        line = "";
        line += "" + healingPotions + "";
        saveFile.println(line);

        line = "";
        for(int i = 0; i < keyItems.size(); i++) {
            line += keyItems.get(i).toLowerCase();
            if(i != (keyItems.size() - 1))
                line += ",";
        }
        saveFile.println(line);

        line = "";
        iter = beastParty.keySet().iterator();
        while(iter.hasNext()) {
            line += iter.next();
            if(iter.hasNext())
                line += ",";
        }
        saveFile.println(line);

        line = "";
        line += "" + storedMoney + "";
        saveFile.println(line);

        line = "";
        iter = storedBeasts.keySet().iterator();
        while(iter.hasNext()) {
            line += iter.next();
            if(iter.hasNext())
                line += ",";
        }
        saveFile.println(line);

        line = "";
        for(int i = 0; i < beastBaits.size(); i++) {
            line += beastBaits.get(i).getName().toLowerCase();
            if(i != (beastBaits.size() - 1))
                line += ",";
        }
        saveFile.println(line);

        saveFile.println("" + maxPartySize + "");

        saveFile.println("" + hasHouse + "");

        line = "";
        iter = armor.keySet().iterator();
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

    public ArrayList<Fish> getFish() {
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

    public int getMoney() {
        return money;
    }

    public Set<String> getPlayerOptions() {
        return playerOptions;
    }

    public ArrayList<String> getRegionsUnlocked() {
        return regionsUnlocked;
    }

    public ArrayList<Bait> getBait() {
        return bait;
    }

    public Map<String, BugNet> getBugNets() {
        return bugNets;
    }

    public Map<String, FishingRod> getFishingRods() {
        return fishingRods;
    }

    public Set<String> getPlacesVisited() {
        return placesVisited;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }

    public boolean isShouldReturnHome() {
        return shouldReturnHome;
    }

    public int getDefense() {
        return defense;
    }

    public Set<String> getBossesFought() {
        return bossesFought;
    }

    public ArrayList<String> getHeartContainers() {
        return heartContainers;
    }

    public int getHealingPotions() {
        return healingPotions;
    }

    public ArrayList<String> getKeyItems() {
        return keyItems;
    }

    public Map<String, Beast> getBeastParty() {
        return beastParty;
    }

    public int getStoredMoney() {
        return storedMoney;
    }

    public Map<String, Beast> getStoredBeasts() {
        return storedBeasts;
    }

    public ArrayList<BeastBait> getBeastBaits() {
        return beastBaits;
    }

    public int getMaxPartySize() {
        return maxPartySize;
    }

    public boolean isHasHouse() {
        return hasHouse;
    }

    public void setWeapons(Map<String, Weapon> weapons) {
        this.weapons = weapons;
    }

    public void setFish(ArrayList<Fish> fish) {
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

    public void setBait(ArrayList<Bait> bait) {
        this.bait = bait;
    }

    public void setBugNets(Map<String, BugNet> bugNets) {
        this.bugNets = bugNets;
    }

    public void setFishingRods(Map<String, FishingRod> fishingRods) {
        this.fishingRods = fishingRods;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setCurrentWeapon(Weapon currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    public void setShouldReturnHome(boolean shouldReturnHome) {
        this.shouldReturnHome = shouldReturnHome;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setHealingPotions(int healingPotions) {
        this.healingPotions = healingPotions;
    }

    public void setStoredMoney(int storedMoney) {
        this.storedMoney = storedMoney;
    }

    public void setMaxPartySize(int maxPartySize) {
        this.maxPartySize = maxPartySize;
    }

    public void setHasHouse(boolean hasHouse) {
        this.hasHouse = hasHouse;
    }
}
