
public class King extends AbsPiece{
	public King(Color color,int row,int col){
		super(color,999,(char)9812,row,col);
	}
	@Override

	public boolean CanMove(int row, int col, GameState game) {
		if(Math.abs(this.getRow()-row)<=1 && (Math.abs(this.getCol()-col)<=1)&&game.getTheGame()[row][col].getColor()!=this.getColor()){
			return true;
		}
		return false;
	}
	@Override
	public AbsPiece copy() {
		King ret=new King(this.getColor(),this.getRow(),this.getCol());
		ret.setMoved(getMoved());
		return ret;
	}
}