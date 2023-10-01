public class Bait {
    private String name;
    private int woodenRodNothingBP;
    private int woodenRodTinCanBP;
    private int woodenRodBassBP;
    private int woodenRodSalmonBP;
    private int woodenRodTunaBP;
    private String rarity;
    private String description;

    public Bait(String name, int woodenRodNothingBP, int woodenRodTinCanBP, int woodenRodBassBP, int woodenRodSalmonBP, int woodenRodTunaBP, String rarity, String description) {
        this.name = name;
        this.woodenRodNothingBP = woodenRodNothingBP;
        this.woodenRodTinCanBP = woodenRodTinCanBP;
        this.woodenRodBassBP = woodenRodBassBP;
        this.woodenRodSalmonBP = woodenRodSalmonBP;
        this.woodenRodTunaBP = woodenRodTunaBP;
        this.rarity = rarity;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public int getWoodenRodNothingBP(FishingRod fishingRod) {
        if(fishingRod.getName().toLowerCase().equals("wooden rod")) {
            return woodenRodNothingBP;
        }

        return -1;
    }

    public int getWoodenRodTinCanBP(FishingRod fishingRod) {
        if(fishingRod.getName().toLowerCase().equals("wooden rod")) {
            return woodenRodTinCanBP;
        }

        return -1;
    }

    public int getWoodenRodBassBP(FishingRod fishingRod) {
        if(fishingRod.getName().toLowerCase().equals("wooden rod")) {
            return woodenRodBassBP;
        }

        return -1;
    }

    public int getWoodenRodSalmonBP(FishingRod fishingRod) {
        if(fishingRod.getName().toLowerCase().equals("wooden rod")) {
            return woodenRodSalmonBP;
        }

        return -1;
    }

    public int getWoodenRodTunaBP(FishingRod fishingRod) {
        if(fishingRod.getName().toLowerCase().equals("wooden rod")) {
            return woodenRodTunaBP;
        }

        return -1;
    }

    public String getRarity() {
        return rarity;
    }

    public String getDescription() {
        return description;
    }
}
