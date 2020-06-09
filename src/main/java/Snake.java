import javax.swing.*;
import java.awt.*;

public class Snake {

    Image head;
    Image body;
    int dots = 3;

    int DOT_SIZE = 10;
    int B_WIDTH = 300;
    int B_HEIGHT = 300;
    int ALL_DOTS = 900;
    int[] x = new int[ALL_DOTS];
    int[] y = new int[ALL_DOTS];


    int horizontalAxis = 1;
    int verticalAxis = 0;

    public Snake(int posx, int posy, String headIMG, String bodyIMG ) {
        for (int z = 0; z < dots; z++) {
            x[z] = posx - z * 10;
            y[z] = posy;
        }
        ImageIcon img = new ImageIcon(headIMG);
        head = img.getImage();
        ImageIcon img2 = new ImageIcon(bodyIMG);
        body = img2.getImage();
    }

    public boolean checkCollision() {
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

    public void move() {
        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        x[0] += horizontalAxis * DOT_SIZE;

        y[0] += verticalAxis * DOT_SIZE;


    }
}
