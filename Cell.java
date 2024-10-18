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

    public boolean isShipHit() {
        return hasShip && isHit;
    }

    public boolean isEmpty() {
        return !isHit && !hasShip;
    }

    public boolean isMissed() {
        return !isHit && hasShip;
    }

    public void cleanCell() {
        hasShip = false;
        isHit = false;
        isSunken = false;
    }
    public String getStatusSign() {
        String sign = "";

        if(isSunken) {

            sign = "XX";

        } else if(isHit && hasShip) {

            sign = "++";
            
        } else if(isHit) {

            sign = "**";

        } else {
            sign = "[]";
        }

        return sign;
    }
}