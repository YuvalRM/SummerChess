
public class NoPiece extends AbsPiece{
	public NoPiece() {
		super(Color.Nothing,0,'#',-1,-1);
	}

	@Override
	public boolean CanMove(int i, int j, GameState game) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public AbsPiece copy() {
		return this;
	}

}
