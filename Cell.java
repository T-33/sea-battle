public class Cell {

    public boolean isHit = false;
    public boolean hasShip = false;

    public void hit() {
        this.isHit = true;
    }
    public void placeShip() {
        this.hasShip = true;
    }
    public String getStatusSign() {
        String sign = "";

        if(this.isHit && this.hasShip) {

            sign = "--";
            
        } else if(this.isHit) {

            sign = "**";

        } else if(this.hasShip) {

            sign = "XX";

        } else {
            sign = "[]";
        }

        return sign;
    }
}