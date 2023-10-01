public class Fish {
    private String name;
    private int value;
    private String rarity;
    private String description;

    public Fish(String name, int value, String rarity, String description) {
        this.name = name;
        this.value = value;
        this.rarity = rarity;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public String getRarity() {
        return rarity;
    }

    public String getDescription() {
        return description;
    }
}
