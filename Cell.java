public class Cell {
    //TODO make every instance variable private 
    //add getters and setters

    public boolean isHit = false;
    public boolean hasShip = false;
    private boolean isSunken = false;

    public void hit() {
        isHit = true;
    }
    public void placeShip() {
        hasShip = true;
    }
    public void sunk() {
        if(hasShip) {
            isSunken = true;
        }
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