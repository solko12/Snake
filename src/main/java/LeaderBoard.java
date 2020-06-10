import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import org.json.*;

public class LeaderBoard {
    Image dot;
    int initX;
    int initY;
    int frameWidth;
    int frameHeight;
    Board board;
    boolean jobDone = false;
    String messege = "";
    Deque<HashMap<String, Integer>> listOfRecords = new LinkedList<HashMap<String, Integer>>();

    public LeaderBoard(int initX, int initY, int frameWidth, int frameHeight, Board board) {
        this.dot = new ImageIcon("src/resources/frameDot.png").getImage();;
        this.initX = initX;
        this.initY = initY;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        loadResults();
    }

    void drawLeaderBoard(Graphics graphics){
        if(jobDone) {
            for (int i = initX; i < frameWidth; i++) {
                graphics.drawImage(dot, i, initY, board);
            }
            graphics.setColor(Color.ORANGE);
            graphics.setFont(new Font("TimesRoman", Font.PLAIN, 23));
            Iterator<HashMap<String, Integer>> listIterator = listOfRecords.iterator();
            graphics.drawString("LEADERBOARD", initX+5, initY+25);
            graphics.setFont(new Font("TimesRoman", Font.PLAIN, 15));
            graphics.drawString("Author", initX+25, initY+40);
            graphics.drawString("Record", initX+145, initY+40);
            for (int i = initY + 55; i < frameHeight; i += 15) {
                graphics.drawImage(dot, initX + 15, i, board);
                if(listIterator.hasNext()){
                    String splited = listIterator.next().toString().split("\\{")[1];
                    String author = splited.split("=")[0];
                    String result = splited.split("=")[1].split("\\}")[0];
                    graphics.drawString(author, initX + 25, i + 5);
                    graphics.drawString(result, initX + 145, i + 5);
                }
            }
        }else{
            graphics.setColor(Color.RED);
            graphics.setFont(new Font("TimesRoman", Font.PLAIN, 15));
            graphics.drawString(messege, initX+15, initY+15);
        }
    }

    private void loadResults(){
        try {
            Path fileName = Path.of("src/resources/results.json");
            String actual = Files.readString(fileName);
            JSONObject jsonObject = new JSONObject(actual);
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            for (Object obj:jsonArray) {
                if(obj instanceof JSONObject){
                    listOfRecords.addFirst(new HashMap<String, Integer>(){{
                            put(((JSONObject) obj).getString("author"),((JSONObject) obj).getInt("result"));
                        }});
                }
            }
            jobDone = true;
        }catch (IOException e){
            messege = e.getMessage();
        }
    }
}
