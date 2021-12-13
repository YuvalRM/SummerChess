
public class Bishop extends AbsPiece {

	public Bishop(Color color,int row,int col) {
		super(color, 3,(char)9815,row,col);
		// TODO Auto-generated constructor stub
	}
	@Override

	public boolean CanMove(int row, int col, GameState game) {
		int thiscol=this.getCol();
		int thisrow=this.getRow();
		if(game.getTheGame()[row][col].getColor()==this.getColor()) {
			return false;
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
		Bishop ret=new Bishop(this.getColor(),this.getRow(),this.getCol());
		ret.setMoved(getMoved());
		return ret;
	}

}
