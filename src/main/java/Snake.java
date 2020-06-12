import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Snake {
    // image of snake's head
    Image head;
    // image of snake's body part
    Image body;
    // number of snake's parts
    int dots = 3;
    // points
    int pts = 0;
    int lastPts = 0;
    // size of dot in pixels
    int DOT_SIZE = 10;
    // map size
    int B_WIDTH = 300;
    int B_HEIGHT = 300;
    // points on the map
    int ALL_DOTS = 900;
    // arrays which contains coords of snake's body parts
    int[] x = new int[ALL_DOTS];
    int[] y = new int[ALL_DOTS];
    // dierction of the snake 8-up, 6-right, 4- left, 2- down
    int dir = 6;

    public Snake(int posx, int posy, String headIMG, String bodyIMG ) {
        // initializing position
        for (int z = 0; z < dots; z++) {
            x[z] = posx - z * 10;
            y[z] = posy;
        }
        // initializing images
        ImageIcon img = new ImageIcon(headIMG);
        head = img.getImage();
        ImageIcon img2 = new ImageIcon(bodyIMG);
        body = img2.getImage();
    }
    // this function checks collisions with walls
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

    // this functon move snake depending on the direction
    public void move() {
        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }
        // left
        if (dir == 4) {
            x[0] -= DOT_SIZE;
        }
        // right
        if (dir == 6) {
            x[0] += DOT_SIZE;
        }
        // up
        if (dir == 8) {
            y[0] -= DOT_SIZE;
        }
        // down
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
