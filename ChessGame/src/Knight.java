
public class Knight extends AbsPiece{
	public Knight(Color color,int row,int col){
		super(color,3,(char)9816,row,col);
	}
	@Override

	public boolean CanMove(int row,int col,GameState game) {
		if((Math.abs(this.getRow()-row)==2 && (Math.abs(this.getCol()-col)==1))||(Math.abs(this.getRow()-row)==1 && (Math.abs(this.getCol()-col)==2))&&game.getTheGame()[row][col].getColor()!=this.getColor()){
			return true;
		}
		return false;
	}
	@Override
	public AbsPiece copy() {
		Knight ret=new Knight(this.getColor(),this.getRow(),this.getCol());
		ret.setMoved(getMoved());
		return ret;
	}

}
