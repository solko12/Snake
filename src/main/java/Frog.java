public class Frog extends NPC {

    public Frog(String img) {
        super(img);
    }

    public void locateFrog() {
        int r = (int) (Math.random() * RAND_POS);
        x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS);
        y = ((r * DOT_SIZE));
    }
}
