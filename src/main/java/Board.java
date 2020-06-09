

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

    private final int B_WIDTH = 300;
    private final int B_HEIGHT = 300;
    private final int DELAY = 140;
    private boolean inGame = true;
    private Timer timer;
    private boolean enemyLose = false;
    private Player player;
    private Enemy enemy;
    private Apple apple;
    private Frog frog;

    public Board() {
        player = new Player(50,50,"src/resources/head.png","src/resources/dot.png");
        enemy = new Enemy(30,30,"src/resources/head.png","src/resources/dot.png");
        apple = new Apple("src/resources/apple.png");
        initBoard();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        initGame();
    }

    private void initGame() {

        //frog.locateFrog();
        apple.locateApple();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        if (inGame) {

            g.drawImage(apple.body, apple.x, apple.y, this);


            for (int z = 0; z < enemy.dots; z++) {
                if (z == 0) {
                    g.drawImage(enemy.head, enemy.x[z], enemy.y[z], this);
                } else {
                    g.drawImage(enemy.body, enemy.x[z], enemy.y[z], this);
                }
            }

            for (int z = 0; z < player.dots; z++) {
                if (z == 0) {
                    g.drawImage(player.head, player.x[z], player.y[z], this);
                } else {
                    g.drawImage(player.body, player.x[z], player.y[z], this);
                }
            }

            Toolkit.getDefaultToolkit().sync();

        } else {
            if(enemyLose) {
                youWin(g);
            }else{
                gameOver(g);
            }

        }
    }

    private void gameOver(Graphics g) {

        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }

    private void youWin(Graphics g) {

        String msg = "You Win!!";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }

    private void checkApple() {

        if ((player.x[0] == apple.x) && (player.y[0] == apple.y)) {

            player.dots++;
            apple.locateApple();
        }
    }

    private void checkCollision() {
        inGame = player.checkCollision();
        if (!inGame) {
            timer.stop();
        }
    }

    private void checkEnemyCollision(){
        inGame = enemy.checkCollision();
        if(!inGame){
            enemyLose = true;
            timer.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame) {
            checkApple();
            checkCollision();
            player.move();
            checkEnemyCollision();
            enemy.move();
        }

        repaint();
    }

    public class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (player.horizontalAxis !=1)) {
                player.horizontalAxis = -1;
                player.verticalAxis = 0;
            }

            if ((key == KeyEvent.VK_RIGHT) && (player.horizontalAxis !=-1)) {
                player.horizontalAxis = 1;
                player.verticalAxis = 0;
            }

            if ((key == KeyEvent.VK_UP) && (player.verticalAxis!=1)) {
                player.verticalAxis = -1;
                player.horizontalAxis = 0;
            }

            if ((key == KeyEvent.VK_DOWN) && (player.verticalAxis!=-1)) {
                player.verticalAxis = 1;
                player.horizontalAxis = 0;
            }
        }
    }


}