
public class Rook extends AbsPiece{
	public Rook(Color color,int row ,int col){
		super(color,5,(char)9814,row,col);
	}
	@Override
	public boolean CanMove(int row, int col, GameState game) {
		int thiscol=this.getCol();
		int thisrow=this.getRow();
		if(game.getTheGame()[row][col].getColor()==this.getColor()) {
			return false;
		}
		if(thisrow-row==0^thiscol-col==0) {
			if(thisrow-row==0) {
				for(int i= Math.min(thiscol, col)+1;i<Math.max(thiscol, col);i++) {
					if(game.getTheGame()[row][i].getColor()!=Color.Nothing) {
						return false;
					}
				}
				return true;
			}
			else {
				for(int i= Math.min(thisrow, row)+1;i<Math.max(thisrow, row);i++) {
					if(game.getTheGame()[i][col].getColor()!=Color.Nothing) {
						return false;
					}
				}
				return true;
				
			}
		}
		return false;
	}
	@Override
	public AbsPiece copy() {
		Rook ret=new Rook(this.getColor(),this.getRow(),this.getCol());
		ret.setMoved(getMoved());
		return ret;
	}
}
