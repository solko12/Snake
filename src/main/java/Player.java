import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Player extends Snake implements iSnake {


    public Player(int posx, int posy, String headIMG, String bodyIMG) {
        super(posx, posy, headIMG, bodyIMG);
    }

    @Override
    public void snakeController() {

    }


}
