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
        baitMap.put("earthworm", new Bait("Earthworm",40,60,80,90,100));
        baitMap.put("grasshopper", new Bait("Grasshopper",30, 50, 80, 90, 100));
        baitMap.put("snail", new Bait("Snail",20, 40, 70, 90, 100));
        baitMap.put("butterfly", new Bait("Butterfly",20, 40, 70, 90, 100));
        baitMap.put("golden earthworm", new Bait("Golden Earthworm",0, 0, 40, 70, 100));
        baitMap.put("beginner bait", new Bait("Beginner Bait",15,30,60,85,100));
        baitMap.put("falconer bait", new Bait("Falconer Bait", 0, 0, 30, 60, 100));
        baitMap.put("shrine snail", new Bait("Shrine Snail", 5, 10, 40, 70, 100));

        fishMap = new TreeMap<String, Fish>();
        fishMap.put("bass", new Fish("Bass", 2, "Common"));
        fishMap.put("minnow", new Fish("Minnow", 2, "Common"));
        fishMap.put("carp", new Fish("Carp", 2, "Common"));

        fishMap.put("salmon", new Fish("Salmon", 3, "Uncommon"));
        fishMap.put("perch", new Fish("Perch", 3, "Uncommon"));
        fishMap.put("bluegill", new Fish("Bluegill", 3, "Uncommon"));

        fishMap.put("tuna", new Fish("Tuna",5,"Rare"));
        fishMap.put("rainbow trout", new Fish("Rainbow Trout",5,"Rare"));


        enemyMap = new TreeMap<String, Enemy>();
        enemyMap.put("boar", new Enemy("Boar", 3, 1, 0, 1,true));
        enemyMap.put("bear", new Enemy("Bear", 5, 2, 2, 2,true));
        enemyMap.put("wolf", new Enemy("Wolf", 2, 5, 0, 2,true));
        enemyMap.put("skeleton", new Enemy("Skeleton", 5,3,1, 2, false));
        enemyMap.put("skeleton archer", new Enemy("Skeleton Archer", 3,7,1, 4, false));
        enemyMap.put("skeleton warrior", new Enemy("Skeleton Warrior", 5,5,3, 10, false));



        bossMap = new TreeMap<String, Enemy>();
        bossMap.put("mossback tortoise", new Enemy("Mossback Tortoise", 10, 1, 1, 15, false));
        bossMap.put("ent", new Enemy("Ent", 10, 2, 1, 25, false));
        bossMap.put("necromancer", new Enemy("Necromancer", 20, 6, 1, 100, false));


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
