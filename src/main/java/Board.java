import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

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
        apple = new Apple("src/resources/apple.png", player, enemy);
        initBoard();
    }

    private void initBoard() {
        addKeyListener(new PlayerControler(player));
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(B_WIDTH+200, B_HEIGHT));

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
        for(int i=0; i<=B_WIDTH;i++){
            g.drawImage(new ImageIcon("src/resources/frameDot.png").getImage(), i, B_HEIGHT+5, this);
        }
        for(int i=0; i<=B_HEIGHT;i++){
            g.drawImage(new ImageIcon("src/resources/frameDot.png").getImage(), B_WIDTH+5, i, this);
        }
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
            g.setFont(new Font("TimesRoman", Font.PLAIN, 15));

            g.setColor(Color.red);

            g.drawString("Player Points: "+player.pts, B_WIDTH+15, 15);
            g.drawString("Enemy Points: "+enemy.pts, B_WIDTH+15,30);

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

    private void checkCollision() {
        if(!(player.checkCollision())) {
            inGame=false;
        } else if(enemy.checkBody(player.x[0],player.y[0])) {
            inGame = false;
        }
        if (!inGame) {
            timer.stop();
        }
    }

    private void checkEnemyCollision(){
        if(!(enemy.checkCollision())) {
            inGame=false;
        } else if(player.checkBody(enemy.x[0],enemy.y[0])) {
            inGame = false;
        }
        if(!inGame){
            enemyLose = true;
            timer.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            // Thread for apple
            Thread thread1 = new Thread(()->{
                apple.checkApple();
            });
            // Thread for player
            Thread thread2 = new Thread(()->{
                checkCollision();
                player.move();
            });
            // Thread for enemy snake
            Thread thread3 = new Thread(()->{
                checkEnemyCollision();
                int newDir = enemy.AIfindDirection(apple.x,apple.y);
                enemy.dir=newDir;
                if(newDir != -1) {
                    enemy.dir = newDir;
                } else {

                    enemyLose=true;
                    inGame=false;
                }
                enemy.move();
            });
            thread1.start();
            thread2.start();
            thread3.start();
            try {
                thread1.join();
                thread2.join();
                thread3.join();
            }catch (InterruptedException exception){
                System.out.println(exception.getMessage());
            }
        }

        repaint();
    }
}