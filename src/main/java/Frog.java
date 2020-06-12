import java.util.concurrent.TimeUnit;

public class Frog extends NPC {
    Player player;
    Enemy enemy;
    public Frog(String img,Player player, Enemy enemy) {

        super(img);
        this.player = player;
        this.enemy = enemy;
    }

    public void locateFrog() {
        int r = (int) (Math.random() * RAND_POS);
        x = ((r * DOT_SIZE));
        x=150;

        r = (int) (Math.random() * RAND_POS);
        y = ((r * DOT_SIZE));
        y=150;
    }
    public void moveFrog() {
        int r = (int) (Math.random()*2)-2;
        x +=((r * DOT_SIZE));

        r = (int) (Math.random()*2)-1;
        y += ((r * DOT_SIZE));
    }

    public void checkFrog() {
        if ((player.x[0] == x) && (player.y[0] == y)) {
            player.dots++;
            player.pts++;
            locateFrog();
        } else if((enemy.x[0] == x) && (enemy.y[0] == y)) {
            enemy.dots++;
            enemy.pts++;
            locateFrog();
        }
    }
}
