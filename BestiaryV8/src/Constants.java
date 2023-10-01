import java.util.Map;
import java.util.TreeMap;

public class Constants {
    private Map<String, BugNet> bugNetMap;
    private Map<String, FishingRod> fishingRodMap;
    private Map<String, Bait> baitMap;
    private Map<String, Fish> fishMap;
    private Map<String, Weapon> weaponMap;
    private Map<String, Enemy> enemyMap;
    private Map<String, Enemy> bossMap;
    private Map<String, BeastBait> beastBaitMap;
    private Map<String, Armor> armorMap;

    public Constants() {
        bugNetMap = new TreeMap<String, BugNet>();
        bugNetMap.put("bug net", new BugNet("Bug Net", 4));

        fishingRodMap = new TreeMap<String, FishingRod>();
        fishingRodMap.put("wooden rod", new FishingRod("Wooden Rod"));

        weaponMap = new TreeMap<String, Weapon>();
        weaponMap.put("tree branch", new Weapon("Tree Branch", 1));
        weaponMap.put("copper sword", new Weapon("Copper Sword", 3));
        weaponMap.put("mossy stone sword", new Weapon("Mossy Stone Sword", 4));

        baitMap = new TreeMap<String, Bait>();
        baitMap.put("earthworm", new Bait("Earthworm",40,60,80,90,100, "Common", "Common insect seen throughout the forests around Rutherford. Easy to catch and makes for an efficient bait."));
        baitMap.put("grasshopper", new Bait("Grasshopper",30, 50, 80, 90, 100, "Common", "Slightly more effective than the Earthworm and similarly common."));
        baitMap.put("snail", new Bait("Snail",20, 40, 70, 90, 100, "Uncommon", "Shelled mollusk that seems to attract fish for unknown reasons. Better than the Earthworm and Grasshopper."));
        baitMap.put("butterfly", new Bait("Butterfly",20, 40, 70, 90, 100, "Uncommon", "Do you really have the heart to use this creature as bait? It's so calming to watch fly..."));
        baitMap.put("golden earthworm", new Bait("Golden Earthworm",0, 0, 40, 70, 100, "Legendary", "Incredibly rare variant of the common Earthworm. The best natural bait around Rutherford."));
        baitMap.put("beginner bait", new Bait("Beginner Bait",15,30,60,85,100, "Rare", ""));
        baitMap.put("falconer bait", new Bait("Falconer Bait", 0, 0, 30, 60, 100, "Epic", ""));
        baitMap.put("shrine snail", new Bait("Shrine Snail", 5, 10, 40, 70, 100, "Epic", "Odd variant of the common Snail only found within the Mysterious Shrine. Nobody knows why. Perhaps because of the wonderful atmosphere?"));

        fishMap = new TreeMap<String, Fish>();
        fishMap.put("bass", new Fish("Bass", 2, "Common", "The most common fish found in the lakes around Rutherford."));
        fishMap.put("minnow", new Fish("Minnow", 2, "Common", "Small fish that fetches a small price in shops."));
        fishMap.put("carp", new Fish("Carp", 2, "Common", "Similarly widespread as the Bass around Rutherford."));

        fishMap.put("salmon", new Fish("Salmon", 3, "Uncommon", "Uncommon fish that fetches a decent price from shops. Crucial staple of the diet of some far reaching tribes."));
        fishMap.put("perch", new Fish("Perch", 3, "Uncommon", "Less commonly found than the Bass and Carp, the Perch comes in a surprising variety of muted colors."));
        fishMap.put("bluegill", new Fish("Bluegill", 3, "Uncommon", "Bright colored fish named for its striped blue side."));

        fishMap.put("tuna", new Fish("Tuna",5,"Rare", "Rare fish that shopkeeps highly value. Generally found in the Ocean, but have been recently discovered in Rutherford's Lakes."));
        fishMap.put("rainbow trout", new Fish("Rainbow Trout",5,"Rare", "The rarest fish found inm Rutherford's lakes. Named for its brilliant spectrum of colors on its side, this catch is a sight to behold."));


        enemyMap = new TreeMap<String, Enemy>();
        enemyMap.put("boar", new Enemy("Boar", 3, 1, 0, 1,true,"Common mammal found roaming the forests of Rutherford. Easy to defeat for even the most inexperienced of warriors."));
        enemyMap.put("bear", new Enemy("Bear", 5, 2, 2, 2,true, "Challenging beast that takes patience to defeat. Its relatively high health makes it one of the more deadly beasts in Rutherford's forests."));
        enemyMap.put("wolf", new Enemy("Wolf", 2, 5, 0, 2,true, "Weak health but devastating attack, make sure you avoid getting hit by this beast or expect to be heading home immediately."));
        enemyMap.put("skeleton", new Enemy("Skeleton", 5,3,1, 2, false, "Skeleton reanimated by the evil Necromancer. Weak and relatively easy to defeat."));
        enemyMap.put("skeleton archer", new Enemy("Skeleton Archer", 3,7,1, 4, false, "Reanimated Skeleton of a nimble archer. Carries a bow capable of dealing devastating damage. Avoid getting hit at all costs."));
        enemyMap.put("skeleton warrior", new Enemy("Skeleton Warrior", 5,5,3, 10, false, "Reanimated Skeleton of a strong warrior. Bears decent stats in all departments."));



        bossMap = new TreeMap<String, Enemy>();
        bossMap.put("mossback tortoise", new Enemy("Mossback Tortoise", 10, 1, 1, 15, false, "Gargantuan Tortoise covered in thick moss and a couple of small trees. The mythical creature stood as the apex beast in Rutherford's dark forest."));
        bossMap.put("tree grunt", new Enemy("Tree Grunt", 10, 2, 1, 25, false, "A giant... walking tree? Odd creature that appears to be a large tree in the general shape of a humanoid."));
        bossMap.put("necromancer", new Enemy("Necromancer", 20, 5, 1, 100, false, "The most deadly threat in Rutherford's history. Performs dark magic and reanimates the skeletons of fallen warriors to do its bidding. Capable of devastating spell attacks."));


        beastBaitMap = new TreeMap<String, BeastBait>();
        beastBaitMap.put("beast bait", new BeastBait("Beast Bait", 8));



        armorMap = new TreeMap<String, Armor>();
        armorMap.put("copper armor", new Armor("Copper Armor", 3));
    }

    public Map<String, BugNet> getBugNetMap() {
        return bugNetMap;
    }

    public Map<String, Bait> getBaitMap() {
        return baitMap;
    }

    public Map<String, FishingRod> getFishingRodMap() {
        return fishingRodMap;
    }

    public Map<String, Fish> getFishMap() {
        return fishMap;
    }

    public Map<String, Weapon> getWeaponMap() {
        return weaponMap;
    }

    public Map<String, Enemy> getEnemyMap() {
        return enemyMap;
    }

    public Map<String, Enemy> getBossMap() {
        return bossMap;
    }

    public Map<String, BeastBait> getBeastBaitMap() {
        return beastBaitMap;
    }

    public Map<String, Armor> getArmorMap() {
        return armorMap;
    }
}
