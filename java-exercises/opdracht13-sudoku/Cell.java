public class Cell {
	private int xPos;
	private int yPos;
	private int val;
	private boolean isEmpty;

	// setters
    public void setXpos(int _xPos) {
        this.xPos = _xPos;
    }
    public void setYpos(int _yPos) {
        this.yPos = _yPos;
    }
    public void setVal(int _val) {
        this.val = _val;
    }

    // getters
    public int getVal(){
    	return this.val;
    }
    public int getXpos(){
		return this.xPos;
    }
    public int getYpos(){
    	return this.yPos;
    }
    public boolean getIsEmpty(){
    	return this.val == 0 ? true : false;
    }


}