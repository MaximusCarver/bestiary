import java.util.Map;
import java.util.TreeMap;

public class Constants {
    private Map<String, BugNet> bugNetMap;
    private Map<String, FishingRod> fishingRodMap;
    private Map<String, Bait> baitMap;
    private Map<String, Fish> fishMap;

    public Constants() {
        bugNetMap = new TreeMap<String, BugNet>();
        bugNetMap.put("bug net", new BugNet("Bug Net", 4));

        fishingRodMap = new TreeMap<String, FishingRod>();
        fishingRodMap.put("wooden rod", new FishingRod("Wooden Rod"));

        baitMap = new TreeMap<String, Bait>();
        baitMap.put("earthworm", new Bait("Earthworm",40,60,80,90,100));
        baitMap.put("grasshopper", new Bait("Grasshopper",30, 50, 80, 90, 100));
        baitMap.put("snail", new Bait("Snail",20, 40, 70, 90, 100));
        baitMap.put("butterfly", new Bait("Butterfly",20, 40, 70, 90, 100));
        baitMap.put("golden earthworm", new Bait("Golden Earthworm",0, 0, 40, 70, 100));

        fishMap = new TreeMap<String, Fish>();
        fishMap.put("bass", new Fish("Bass", 2, "Common"));
        fishMap.put("salmon", new Fish("Salmon", 3, "Uncommon"));
        fishMap.put("tuna", new Fish("Tuna",5,"Rare"));
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
}
