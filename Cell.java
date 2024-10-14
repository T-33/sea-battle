public class Cell {

    public boolean isHit = false;
    public boolean hasShip = false;
    public boolean isSunken = false;

    public void hit() {
        isHit = true;
    }
    public void placeShip() {
        hasShip = true;
    }
    public void cleanCell() {
        hasShip = false;
        isHit = false;
        isSunken = false;
    }
    public String getStatusSign() {
        String sign = "";

        if(isHit && hasShip) {

            sign = "--";
            
        } else if(isHit) {

            sign = "**";

        } else if(hasShip) {

            sign = "XX";

        } else if(isSunken) {

            sign = "{x}";

        } else {
            sign = "[]";
        }

        return sign;
    }
}