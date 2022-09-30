import java.awt.*;

public class Score {
    //hard-coded values for each score event, change later
    private int score = 0;
    private int pipValue = 10;
    private int powerPipValue = 50;
    private int ghostValue = 100;
    private int levelValue = 500;
    Game game;

    public Score(Game game) {
        this.game = game;
    }


    //method for adding each respective value to the score
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


    //dynamically updates score as it is called in driver's paint method
    public void drawScore(Graphics2D g2d) {
        String scoreTrack = "Score: " + score;
        g2d.setColor(Color.white);
        g2d.setFont(new Font("Helvetica", Font.BOLD, 30));
        g2d.drawString(scoreTrack, game.getWindowWidth() - (10*game.getTileSize()/4), game.getWindowHeight() - (game.getTileSize()));
    }
}
