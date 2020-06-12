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
        int r =1 - (int) (Math.random()*3);
        int xMove = r* DOT_SIZE;
        if((x+xMove)>=mapSize||(x+xMove)<=0)
        {
            xMove=-xMove;
        }
        x +=xMove;

        r =1 - (int) (Math.random()*3);
        int yMove = r* DOT_SIZE;
        System.out.println(r);
        if((x+yMove)>=mapSize||(y+yMove)<=0)
        {
            yMove=-yMove;
        }
        y +=yMove;
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
