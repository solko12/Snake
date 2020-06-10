public class Apple extends NPC {

    Player player;
    Enemy enemy;

    public Apple(String img, Player player, Enemy enemy) {
        super(img);
        this.player = player;
        this.enemy = enemy;
    }

    public void locateApple() {

        int r = (int) (Math.random() * RAND_POS);
        x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS);
        y = ((r * DOT_SIZE));
    }

    public void checkApple() {
        if ((player.x[0] == x) && (player.y[0] == y)) {
            player.dots++;
            locateApple();
        } else if((enemy.x[0] == x) && (enemy.y[0] == y)) {
            enemy.dots++;
            locateApple();
        }
    }
}
