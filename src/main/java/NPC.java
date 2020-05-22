import javax.swing.*;
import java.awt.Image;
public class NPC {
    Image body;
    int x;
    int y;
    int RAND_POS = 29;
    int DOT_SIZE = 10;

    public NPC(String image)
    {
        // przypisanie obrazka
        ImageIcon img = new ImageIcon(image);
        body = img.getImage();
    }

}
