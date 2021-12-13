
public abstract class AbsPiece {
	private Color color;
	private int value;
	private char symb;
	private int row;
	private int col;
	private boolean moved=false;
	
	public AbsPiece(Color color,int value,char symb,int row,int col) {
		this.color=color;
		this.value=value;
		this.symb=symb;
		this.row=row;
		this.col=col;
		if(color==Color.Black) {
			this.symb+=6;
		}
		
	}
	public Color getColor() {
		return color;
	}
	public int getValue() {
		return this.value;
	}
	public String toString() {
		return this.symb+"";
	}
	public abstract boolean CanMove(int row,int col,GameState game) ;
	public int getCol() {
		return col;
	}
	public int getRow() {
		return row;
	}

	public boolean getMoved() {
		return moved;
	}
	protected void setMoved(boolean moved) {
		this.moved=moved;
	}
	public void moveTo(int row,int col,GameState game) {
		AbsPiece [][] theBoard=game.getTheGame();
		theBoard[this.row][this.col]=new NoPiece();
		AbsPiece eaten=theBoard[row][col];
		
		theBoard[row][col]=this;
		this.col=col;
		this.row=row;
		this.moved=true;
		
		game.discardPiece(eaten);
		int mul=1;
		if(game.getTurn()==Color.Black) {
			mul=-1;
		}
		if(game.getTwoSteps()!=null && game.getTwoSteps().getRow()==row-mul && game.getTwoSteps().getCol()==col &&this instanceof Pawn) {
			theBoard[game.getTwoSteps().getRow()][game.getTwoSteps().getCol()]=new NoPiece();
			game.discardPiece(theBoard[game.getTwoSteps().getRow()][game.getTwoSteps().getCol()]);
		}
		game.setTwoSteps(null);
	}
	public abstract AbsPiece copy();
    @Override
    public boolean equals(Object o) {
 
        // If the object is compared with itself then return true 
        if (o == this) {
            return true;
        }
 
        if (!(o instanceof AbsPiece)) {
            return false;
        }
        AbsPiece c = (AbsPiece) o;
         
        return this.col==c.col &&this.row==c.row;
    }


}