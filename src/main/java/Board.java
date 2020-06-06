

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
    private int DOT_SIZE = 10;
    private boolean inGame = true;
    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private Timer timer;

    private Snake player;
    private EnemySnake enemy;
    private Apple apple;
    private Frog frog;

    public Board() {
        player = new Snake(50,50,"src/resources/head.png","src/resources/dot.png");
        enemy = new EnemySnake(30,30, "src/resources/head.png","src/resources/dot.png");
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

            gameOver(g);
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
            timer.stop();
        }
    }

    public void enemyMove() {

        for (int z = enemy.dots; z > 0; z--) {
            enemy.x[z] = enemy.x[(z - 1)];
            enemy.y[z] = enemy.y[(z - 1)];
        }

        boolean enemyLeftDirection = false;
        boolean enemyRightDirection = true;
        boolean enemyUpDirection = false;
        boolean enemyDownDirection = false;

        if (enemyLeftDirection) {
            enemy.x[0] -= DOT_SIZE;
        }

        if (enemyRightDirection) {
            enemy.x[0] += DOT_SIZE;
        }

        if (enemyUpDirection) {
            enemy.y[0] -= DOT_SIZE;
        }

        if (enemyDownDirection) {
            enemy.y[0] += DOT_SIZE;
        }
    }

    public void playerMove() {

        for (int z = player.dots; z > 0; z--) {
            player.x[z] = player.x[(z - 1)];
            player.y[z] = player.y[(z - 1)];
        }

        if (leftDirection) {
            player.x[0] -= DOT_SIZE;
        }

        if (rightDirection) {
            player.x[0] += DOT_SIZE;
        }

        if (upDirection) {
            player.y[0] -= DOT_SIZE;
        }

        if (downDirection) {
            player.y[0] += DOT_SIZE;
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame) {
            checkApple();
            checkCollision();
            checkEnemyCollision();
            playerMove();
            enemyMove();
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
        }
    }
}