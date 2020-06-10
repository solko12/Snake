import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Snake {

    Image head;
    Image body;
    int dots = 3;
    int pts = 0;

    int DOT_SIZE = 10;
    int B_WIDTH = 300;
    int B_HEIGHT = 300;
    int ALL_DOTS = 900;
    int[] x = new int[ALL_DOTS];
    int[] y = new int[ALL_DOTS];

    int dir = 6;

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

        if (dir == 4) {
            x[0] -= DOT_SIZE;
        }

        if (dir == 6) {
            x[0] += DOT_SIZE;
        }

        if (dir == 8) {
            y[0] -= DOT_SIZE;
        }

        if (dir == 2) {
            y[0] += DOT_SIZE;
        }

    }
    // checks if an element is a part of snake's body
    public boolean checkBody(int xPos,int yPos) {
        for(int i=1;i<dots;i++) {
            if (x[i] == xPos && y[i] == yPos) {
                return true;
            }
        }
        return false;
    }

}
