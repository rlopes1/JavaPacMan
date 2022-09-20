import java.awt.*;

public class Score {
    private int score = 0;
    private int pipValue = 10;
    private int powerPipValue = 50;
    private int ghostValue = 100;
    private int levelValue = 500;

    public void pipEaten() {
        score += pipValue;
    }
    public void powerPipEaten() {
        score += powerPipValue;
    }
    public void ghostEaten() {
        score += ghostValue;
    }
    public void levelBeaten() {
        score += levelValue;
    }

    public int getScore() {
        return score;
    }

    public void drawScore(Graphics2D g2d) {
        String scoreTrack = "Score: " + score;
        g2d.setColor(Color.white);
        g2d.setFont(new Font("Helvetica", Font.BOLD, 30));
        g2d.drawString(scoreTrack, 1310, 680);
    }
}
