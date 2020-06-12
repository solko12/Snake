import javax.swing.*;
import java.awt.Image;
public class NPC {
    Image body;
    int x;
    int y;
    int RAND_POS = 29;
    int DOT_SIZE = 10;
    int mapSize = 300;

    public NPC(String image) {
        // image initializing
        ImageIcon img = new ImageIcon(image);
        body = img.getImage();
    }

}
