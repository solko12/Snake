import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PlayerControler extends KeyAdapter {

    private Player player;
    public PlayerControler(Player player){
        this.player = player;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if ((key == KeyEvent.VK_LEFT) && (player.dir !=6)) {
            player.dir = 4;
        }

        if ((key == KeyEvent.VK_RIGHT) && (player.dir !=4)) {
            player.dir = 6;
        }

        if ((key == KeyEvent.VK_UP) && (player.dir!=2)) {
            player.dir = 8;
        }

        if ((key == KeyEvent.VK_DOWN) && (player.dir!=8)) {
            player.dir = 2;
        }
    }
}
