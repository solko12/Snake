

public class Frog extends NPC {
    Player player;
    Enemy enemy;
    public Frog(String img,Player player, Enemy enemy) {

        super(img);
        this.player = player;
        this.enemy = enemy;
    }
    // initialize random position of the frog
    public void locateFrog() {
        int r = (int) (Math.random() * RAND_POS);
        x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS);
        y = ((r * DOT_SIZE));

    }
    // algorithm that move frog to the random position 1 step away
    public void moveFrog() {

        int r =1 - (int) (Math.random()*3);
        int xMove = r* DOT_SIZE;
        // checking collisions with the walls
        if((x+xMove)>=mapSize||(x+xMove)<=0)
        {
            xMove=-xMove;
        }
        x +=xMove;

        r =1 - (int) (Math.random()*3);
        int yMove = r* DOT_SIZE;

        // checking collisions with the walls
        if((x+yMove)>=mapSize||(y+yMove)<=0)
        {
            yMove=-yMove;
        }
        y +=yMove;
    }
    // this function checks if a player or enemy catch a frog then it add point and relocate frog
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
