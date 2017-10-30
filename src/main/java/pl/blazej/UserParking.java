package pl.blazej;

public class UserParking {
    private int whichPlace;
    private boolean isFree;
    private String index;

    public String getIndex() {
        return index;
    }

    public UserParking() {
    }

    public UserParking(String index, int whichPlace, boolean isFree) {
        this.index = index;
        this.whichPlace = whichPlace;
        this.isFree = isFree;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public int getWhichPlace() {
        return whichPlace;
    }

    public void setWhichPlace(int whichPlace) {
        this.whichPlace = whichPlace;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }
}
