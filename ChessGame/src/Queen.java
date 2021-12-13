
public class Queen extends AbsPiece{
	public Queen(Color color,int row,int col){
		super(color,9,(char)9813,row,col);
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
		if(Math.abs(thisrow-row)==Math.abs(thiscol-col)) {
			int rsign=(int) Math.signum(thisrow-row);
			int csign=(int) Math.signum(thiscol-col);
			thiscol-=csign;
			thisrow-=rsign;
			while(thiscol!=col&&thisrow!=row) {
				if(game.getTheGame()[thisrow][thiscol].getColor()!=Color.Nothing) {
					return false;
				}
				thiscol-=csign;
				thisrow-=rsign;
			}
			return true;
		}
		return false;
	}
	@Override
	public AbsPiece copy() {
		Queen ret=new Queen(this.getColor(),this.getRow(),this.getCol());
		ret.setMoved(getMoved());
		return ret;
	}
}