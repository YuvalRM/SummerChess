
public class Pawn extends AbsPiece{
	public Pawn(Color color,int row,int col){
		super(color,1,(char)9817,row,col);
	}
	@Override

	public boolean CanMove(int row, int col, GameState game) {
		int mul=1;
		if(this.getColor()==Color.Black) {
			mul=-1;
		}
		AbsPiece target=game.getTheGame()[row][col];
		if(this.getCol()==col && target.getColor()==Color.Nothing) {
			if(row-this.getRow()==mul) {
				return true;
			}
			if(row-this.getRow()==2*mul && !this.getMoved()) {
				return true;
			}
		}
		if(Math.abs(this.getCol()-col)==1 && target.getColor()==this.getColor().opposite()&&(row-this.getRow()==mul)) {
			return true;
			
		}
		if(Math.abs(this.getCol()-col)==1 && target.getColor()==Color.Nothing&&(row-this.getRow()==mul)) {
			if(game.getTwoSteps()!=null && game.getTwoSteps().getRow()==this.getRow() && game.getTwoSteps().getCol()==col) {
				return true;
			}
			
		}
		return false;
	}
	@Override
	public AbsPiece copy() {
		Pawn ret=new Pawn(this.getColor(),this.getRow(),this.getCol());
		ret.setMoved(getMoved());
		return ret;
	}
	public void moveTo(int row,int col,GameState game) {
		int crow=this.getRow();
		super.moveTo(row,col,game);
		if(Math.abs(row-crow)==2) {
			game.setTwoSteps((Pawn)this.copy());
		}

	}
	public void promotion(String promo, GameState game) {
		AbsPiece np=null;
		if(promo.equals("Q")) {
			np=new Queen(this.getColor(),this.getRow(),this.getCol()) ;
		}
		else if(promo.equals("N")) {
			np=new Knight(this.getColor(),this.getRow(),this.getCol()) ;
		}
		else if(promo.equals("R")) {
			np=new Rook(this.getColor(),this.getRow(),this.getCol()) ;
		}
		else if(promo.equals("B")) {
			np=new Bishop(this.getColor(),this.getRow(),this.getCol()) ;
		}
		game.getTheGame()[this.getRow()][this.getCol()]=np;
		game.discardPiece(this);
	}
}