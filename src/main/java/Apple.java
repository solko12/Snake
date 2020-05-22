public class Apple extends NPC {

    public Apple(String img)
    {
        super(img);

    }

    public void locateApple() {

        int r = (int) (Math.random() * RAND_POS);
        x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS);
        y = ((r * DOT_SIZE));
    }
}
