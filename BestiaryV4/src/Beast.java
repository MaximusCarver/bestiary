public class Beast {
    private String name;
    private int health;
    private int maxHealth;
    private int damage;
    private int defense;

    public Beast(String name, int maxHealth, int damage, int defense) {
        this.name = name;
        this.health = maxHealth;
        this.maxHealth = maxHealth;
        this.damage = damage;
        this.defense = defense;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getDamage() {
        return damage;
    }

    public int getDefense() {
        return defense;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
