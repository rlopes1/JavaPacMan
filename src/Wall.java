import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class Wall {
    private int offset = 30;
    private int width = 1280;
    private int height = 720;
    Area border;

    //creating the border around the map
    //may scrap, figuring out the array method of creating level
    public Area createBorder() {
        //Draw Borders
        Shape b1 = new RoundRectangle2D.Float(offset, 0, width - (2*offset), offset, offset, offset);
        Shape b2 = new RoundRectangle2D.Float(offset, height - offset, width - (2*offset), offset, offset, offset);
        Shape b3 = new RoundRectangle2D.Float(0, 0, offset, height, offset, offset);
        Shape b4 = new RoundRectangle2D.Float(width - offset, 0, offset, height, offset, offset);
        border = new Area(b1);
        border.add(new Area(b2));
        border.add(new Area(b3));
        border.add(new Area(b4));
        return border;
    }
    public void paint(Graphics2D g) {
        g.setColor(Color.blue);
        g.draw(border);
    }
}
