import javax.swing.*;
import java.awt.Image;
public class Snake {
    Image head;
    Image body;
    int dots = 3;
    private final int ALL_DOTS = 900;
    final int x[] = new int[ALL_DOTS];
    final int y[] = new int[ALL_DOTS];
    private final int B_WIDTH = 300;
    private final int B_HEIGHT = 300;


    public Snake(int posx, int posy, String headIMG, String bodyIMG )
    {
        for (int z = 0; z < dots; z++) {
            x[z] = posx - z * 10;
            y[z] = posy;
        }
        ImageIcon img = new ImageIcon(headIMG);
        head = img.getImage();
        ImageIcon img2 = new ImageIcon(bodyIMG);
        body = img2.getImage();
    }

    public boolean checkCollision()
    {
        for (int z = dots; z > 0; z--) {

            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                return false;
            }
        }

        if (y[0] >= B_HEIGHT) {
            return false;
        }

        if (y[0] < 0) {
            return false;
        }

        if (x[0] >= B_WIDTH) {
            return false;
        }

        if (x[0] < 0) {
            return false;
        }
        return true;
    }
}
