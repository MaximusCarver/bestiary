public class BugNet {
    private String name;
    private int catchRate;

    public BugNet(String name, int catchRate) {
        this.name = name;
        this.catchRate = catchRate;
    }

    public String getName() {
        return name;
    }

    public int getCatchRate() {
        return catchRate;
    }
}
