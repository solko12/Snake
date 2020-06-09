import javax.swing.*;
import java.awt.Image;

public class Enemy extends Snake implements iSnake {


    public Enemy(int posx, int posy, String headIMG, String bodyIMG) {
        super(posx, posy, headIMG, bodyIMG);
    }

    @Override
    public void snakeController() {

    }

    // checks if an element is a part of snake's body
    public boolean checkBody(int xPos,int yPos)
    {
        for(int i=1;i<dots;i++)
            if(x[i]==xPos && y[i]==yPos)
            {
                return true;
            }
        return false;
    }

    public boolean canMove(int direction){
        if(direction==8){
            int X=x[0];
            int Y=y[0]-10;
            if(Y<10||checkBody(X,Y)){
                return false;
            }else return true;
        }
        if(direction==6){
            int X=x[0]+10;
            int Y=y[0];
            if(X>B_HEIGHT||checkBody(X,Y)){
                return false;
            }else return true;
        }
        if(direction==2){
            int X=x[0];
            int Y=y[0]+10;
            if(Y>B_HEIGHT||checkBody(X,Y)){
                return false;
            }else return true;
        }
        if(direction==4){
            int X=x[0]-10;
            int Y=y[0];
            if(X<10||checkBody(X,Y)){
                return false;
            }else return true;
        }
        return false;
    }

    public int AIfindDirection(int xApple,int  yApple){
        if(xApple>x[0]&&dir!=4){
            return 6;
        }
        if(xApple<x[0]&&dir!=6){
            return 4;
        }
        if(xApple==x[0]){//the x axis equal
            if(yApple>y[0]&&dir!=8)return 2;
            else if(yApple>y[0]&&dir==8){
                if(canMove(4))return 4;
                else if(canMove(6))return 6;
                else return -1;
            }
            else if(yApple<y[0]&&dir!=2)return 8;
            else if(yApple<y[0]&&dir==2){
                if(canMove(4))return 4;
                else if(canMove(6))return 6;
                else return -1;
            }
        }

        if(yApple>y[0]&&dir!=2){
            return 8;
        }
        if(yApple==y[0]){
            if(xApple>x[0]&&dir!=4)return 6;
            else if(xApple>x[0]&&dir==4){
                if(canMove(8))return 8;
                else if(canMove(2))return 2;
                else return -1;
            }
            else if(xApple<x[0]&&dir!=6)return 4;
            else if(xApple<x[0]&&dir==6){
                if(canMove(8))return 8;
                else if(canMove(2))return 2;
                else return -1;
            }
        }
        if(yApple<y[0]&&dir!=8){
            return 2;
        }
        return -1;
    }


}
