public class Enemy {
    private String name;
    private int health;
    private int maxHealth;
    private int damage;
    private int defense;
    private boolean tameable;
    private int moneyDrop;
    private String description;

    public Enemy(String name, int maxHealth, int damage, int defense, int moneyDrop, boolean tameable, String description) {
        this.name = name;
        this.health = maxHealth;
        this.maxHealth = maxHealth;
        this.damage = damage;
        this.defense = defense;
        this.moneyDrop = moneyDrop;
        this.tameable = tameable;
        this.description = description;
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

    public boolean isTameable() {
        return tameable;
    }

    public int getMoneyDrop() {
        return moneyDrop;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getDescription() {
        return description;
    }
}
