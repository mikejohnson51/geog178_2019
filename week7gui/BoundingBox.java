import java.util.ArrayList;

public class BoundingBox {


        double minx, maxx, miny, maxy;
        double p1x, p2x,p1y,p2y;

        public BoundingBox (Point p1, Point p2) {
            this.minx = Math.min(p1.getX(), p2.getX());
            this.maxx = Math.max(p1.getX(), p2.getX());
            this.miny = Math.min(p1.getY(), p2.getY());
            this.maxy = Math.max(p1.getY(), p2.getY());
            this.p1x = p1.getX();
            this.p2x = p2.getX();
            this.p1y = p1.getY();
            this.p2y = p2.getY();
        }



    public boolean isInside (Point p) {


            boolean inBox = false;

            if (p.getX() > this.minx && p.getX() < this.maxx && p.getY() > this.miny && p.getY() < this.maxy) {
                    inBox = true;
                }
                return inBox;

            }

           public int affected = 0;
           public int safe = 0;
        public void isInside (ArrayList<Point> a) {
            for (int i = 0; i < a.size(); i++) {
                if (a.get(i).getX() > this.minx && a.get(i).getX() < this.maxx && a.get(i).getY() > this.miny && a.get(i).getY() < this.maxy) {
                 affected++;
                 }
            else safe++;
            }

         }

        public String toString(){
            return ("("+this.p1x+","+this.p1y+") ("+this.p2x+","+this.p2y+")");
    }

}
