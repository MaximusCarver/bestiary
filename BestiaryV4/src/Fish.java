public class Fish {
    private String name;
    private int value;
    private String rarity;

    public Fish(String name, int value, String rarity) {
        this.name = name;
        this.value = value;
        this.rarity = rarity;
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
}
